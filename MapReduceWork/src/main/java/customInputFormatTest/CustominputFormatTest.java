package customInputFormatTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.SequenceFile;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.SequenceFileOutputFormat;

import java.io.IOException;

public class CustominputFormatTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(CustominputFormatTest.class);
        job.setMapperClass(CustomInputFormatMapper.class);
        job.setReducerClass(CustomInputFormatReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(BytesWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(BytesWritable.class);

        //设置自定义输入类
        job.setInputFormatClass(CustomInputFormat.WholeInputFormat.class);
        //设置自定义输出类
        job.setOutputFormatClass(SequenceFileOutputFormat.class);

        Path inpath = new Path("D:\\WangHao\\input");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);

        System.exit(job.waitForCompletion(true)?0:1);
    }

    public static class CustomInputFormatMapper extends Mapper<Text, BytesWritable, Text, BytesWritable>{
        @Override
        protected void map(Text key, BytesWritable value, Context context) throws IOException, InterruptedException {
            context.write(key,value);
        }
    }

    public static class CustomInputFormatReducer extends Reducer<Text, BytesWritable, Text, BytesWritable>{
        @Override
        protected void reduce(Text key, Iterable<BytesWritable> values, Context context) throws IOException, InterruptedException {
            for (BytesWritable bytesWritable : values){
                context.write(key,bytesWritable);
            }
        }
    }
}
