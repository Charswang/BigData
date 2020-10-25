package keyValueFormatTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.KeyValueLineRecordReader;
import org.apache.hadoop.mapreduce.lib.input.KeyValueTextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class KeyValueFormat {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();

        //设置KeyValueTextInputFormat的分隔符
        conf.set(KeyValueLineRecordReader.KEY_VALUE_SEPERATOR," ");

        //获取job对象
        Job job = Job.getInstance(conf);
        //设置Jar的位置
        job.setJarByClass(KeyValueFormat.class);
        //关联Mapper和Reducer类
        job.setMapperClass(KeyValueFormatMapper.class);
        job.setReducerClass(KeyValueFormatReducer.class);
        //设置map输出类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        //设置最终输出类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //设置输入格式
        job.setInputFormatClass(KeyValueTextInputFormat.class);

        //设置数据输入输出路径
        Path inputPath = new Path("/data/input/keyValueFormatFile1.txt"); //对这个文件中的首个单词统计个数。KeyValueInputFormat写入的话，每行的首个单词就是key
        FileInputFormat.addInputPath(job,inputPath);
        Path outputPath = new Path("/data/output");
        FileOutputFormat.setOutputPath(job,outputPath);

        boolean bool = job.waitForCompletion(true);
        System.exit(bool ? 0 : 1);
    }

    public static class KeyValueFormatMapper extends Mapper<Text, Text, Text, IntWritable> {
        IntWritable v = new IntWritable(1);
        @Override
        protected void map(Text key, Text value, Context context) throws IOException, InterruptedException {
            context.write(key,v);

        }
    }
    public static class KeyValueFormatReducer extends Reducer<Text, IntWritable, Text, IntWritable>{
        IntWritable v = new IntWritable();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values){
                sum += value.get();
            }
            v.set(sum);
            context.write(key,v);
        }
    }
}
