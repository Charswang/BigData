package SqlWork;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 计算学生表中年龄的最大/小值，平均值
 *
 * sql:select avg(age) as avg, max(age) as max, min(age) as min from students
 */
public class Statistical {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(Statistical.class);
        job.setMapperClass(StatisticalMapper.class);
        job.setReducerClass(StatisticalReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Text.class);

        Path inpath = new Path("D:\\WangHao\\input\\sqlinput\\students.txt");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);
        System.exit(job.waitForCompletion(true)?0:1);
    }
    public static class StatisticalMapper extends Mapper<LongWritable, Text, Text, IntWritable>{
        Text k = new Text("sameAgeKey");
        IntWritable v = new IntWritable();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //lisa	fmale	18
            String s = value.toString();
            String[] split = s.split("\t");

            v.set(Integer.parseInt(split[2]));

            context.write(k,v);
        }
    }
    public static class StatisticalReducer extends Reducer<Text, IntWritable, NullWritable, Text>{
        Text v = new Text();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int max = 0;
            int min = Integer.MAX_VALUE;
            int sum = 0;
            int count = 0;

            for (IntWritable val : values){
                if (val.get() > max){
                    max = val.get();
                }
                if (val.get() < min){
                    min = val.get();
                }
                sum += val.get();
                count++;
            }
            double avg = (double) sum / count;

            v.set("age max: " + max + "\n" + "age min: " + min + "\n" + "age avg: " + avg);

            context.write(NullWritable.get(),v);
        }
    }
}
