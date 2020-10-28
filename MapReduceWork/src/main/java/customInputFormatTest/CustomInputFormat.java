package customInputFormatTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.*;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;


import java.io.IOException;

/**
 * 实现小文件合并，合并的最终文件为SequenceFile文件。
 * (SequenceFile 文件是 hadoop 用来储存二进制形式的 key-value 对的文件格式)
 * SequenceFile 里面储存着多个文件，储存的形式为文件路径 + 名称为 key，文件内容为 value
 *
 * 1、自定义一个类，继承FileInputFormat
 *      重写isSplitable()方法，返回false，表示输入的单个文件不可被切割（分片）
 *      重写createRecordReader(),创建自定义RecordReader对象，并初始化。
 * 2、改写RecordReader,实现一次读取一个完整文件，并封装成KV
 *      获取文件路径信息+名称，设置key
 *      采用IO流的形式，一次读取一个文件输出到value，因为设置了不可分割，所以所有信息都被封装到了value中
 * 3、设置Driver类
 *      设置输入的inputFormat
 *      设置输出的outputFormat
 */
public class CustomInputFormat {
    public static class WholeInputFormat extends FileInputFormat<Text, BytesWritable>{
        @Override
        //将文件设置为不可分片。
        protected boolean isSplitable(JobContext context, Path filename) {
            return false;
        }

        //重写createRecordReader方法返回自定义过的RecordReader
        public RecordReader<Text, BytesWritable> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            WholeRecordReader wholeRecordReader = new WholeRecordReader();
            //初始化RecordReader(输入分片,Context)
            wholeRecordReader.initialize(inputSplit,taskAttemptContext);
            return wholeRecordReader;
        }
    }
    public static class WholeRecordReader extends RecordReader<Text, BytesWritable>{

        private Configuration conf;
        private FileSplit fileSplit;
        private Boolean isProgress = false; //标记文件是否被处理。
        private BytesWritable bytesWritable = new BytesWritable();
        private Text text = new Text();
        public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            fileSplit = (FileSplit) inputSplit; //转换为文件输入分片
            conf = taskAttemptContext.getConfiguration(); //配置信息
        }

        /**
         * 这个方法中，每个文件在不同的mapper(MapTesk)中，在第一个mapper(MapTesk)运行完之后，isProgress变成了true，所以没有办法再继续执行循环
         * 所以再次以isProgress=false身份进来的时候，说明已经换了一个mapper(MapTesk)来运行了。
         * @return
         * @throws IOException
         * @throws InterruptedException
         */
        public boolean nextKeyValue() throws IOException, InterruptedException {
            if (!isProgress){
                //设置缓冲区,以便将二进制输入流存入缓冲区内
                byte[] bytes = new byte[(int)fileSplit.getLength()];
                //通过分片信息得出，文件所在路径
                Path path = fileSplit.getPath();
                //获取文件系统
                FileSystem fs = path.getFileSystem(conf);
                //通过文件系统得到相对应文件的输入流。
                FSDataInputStream open = fs.open(path);
                //按分片长度，将文件流信息输入到事先设置好的byte数组中。便于后续设置输入键值对的value
                IOUtils.readFully(open,bytes,0,bytes.length);

                //value存储的是byte数组，这一步就需要，使用到bytes缓冲区来设置输入的value
                bytesWritable.set(bytes,0,bytes.length);
                //key存储的是文件的路径
                text.set(path.toString());

                //关闭资源
                IOUtils.closeStream(open);

                //设置该文件已经被操作过
                isProgress = true;

                //继续尝试，看是否还有剩余的文件没有被操作过。
                return true;
            }
            //如果文件都被操作过了，就不再往下进行了。
            return false;
        }

        public Text getCurrentKey() throws IOException, InterruptedException {
            return text;
        }

        public BytesWritable getCurrentValue() throws IOException, InterruptedException {
            return bytesWritable;
        }

        public float getProgress() throws IOException, InterruptedException {
            return 0;
        }

        public void close() throws IOException {

        }
    }
}
