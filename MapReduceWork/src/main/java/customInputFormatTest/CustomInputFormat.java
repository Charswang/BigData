package customInputFormatTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
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
        protected boolean isSplitable(JobContext context, Path filename) {
            return false;
        }

        public RecordReader<Text, BytesWritable> createRecordReader(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            WholeRecordReader wholeRecordReader = new WholeRecordReader();
            wholeRecordReader.initialize(inputSplit,taskAttemptContext);
            return wholeRecordReader;
        }
    }
    public static class WholeRecordReader extends RecordReader<Text, BytesWritable>{

        private Configuration conf;
        private FileSplit fileSplit;
        public void initialize(InputSplit inputSplit, TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
            fileSplit = (FileSplit) inputSplit;
            conf = taskAttemptContext.getConfiguration();
        }

        public boolean nextKeyValue() throws IOException, InterruptedException {
            return false;

        }

        public Text getCurrentKey() throws IOException, InterruptedException {
            return null;
        }

        public BytesWritable getCurrentValue() throws IOException, InterruptedException {
            return null;
        }

        public float getProgress() throws IOException, InterruptedException {
            return 0;
        }

        public void close() throws IOException {

        }
    }
    public static void main(String[] args) {

    }

    public static class CustomInputFormatMapper extends Mapper<Text, BytesWritable, Text, BytesWritable>{
        @Override
        protected void map(Text key, BytesWritable value, Context context) throws IOException, InterruptedException {

        }
    }

    public static class CustomInputFormatReducer extends Reducer<Text, BytesWritable, Text, BytesWritable>{
        @Override
        protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {

        }
    }
}
