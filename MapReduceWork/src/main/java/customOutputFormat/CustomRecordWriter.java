package customOutputFormat;

import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.RecordWriter;
import org.apache.hadoop.mapreduce.TaskAttemptContext;

import java.io.IOException;

public class CustomRecordWriter extends RecordWriter<Text, NullWritable> {

    FSDataOutputStream fsatguigu;
    FSDataOutputStream fsother;
    public CustomRecordWriter(TaskAttemptContext job) {
        try {
            //获取文件系统
            FileSystem fs = FileSystem.get(job.getConfiguration());

            //创建两个文件的输出流
            fsatguigu = fs.create(new Path("E:\\atguigu.log"));
            fsother = fs.create(new Path("E:\\other.log"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void write(Text text, NullWritable nullWritable) throws IOException, InterruptedException {
        //判断text中是否包含atguigu字符串，如果包含就输出到atguigu.log
        String s = text.toString();
        if (s.contains("atguigu")){
            fsatguigu.write(s.getBytes());
        }else{
            fsother.write(s.getBytes());
        }
    }

    public void close(TaskAttemptContext taskAttemptContext) throws IOException, InterruptedException {
        //关闭输出流
        IOUtils.closeStream(fsatguigu);
        IOUtils.closeStream(fsother);
    }
}
