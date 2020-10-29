package MultipleOutputsWork;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.MultipleOutputs;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import wordcount.WordCount;

import java.io.IOException;

public class MultipleOutputsTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(MultipleOutputsTest.class);
        job.setMapperClass(MultipleOutputsMapper.class);
        job.setReducerClass(MultipleOutputsReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

//        MultipleOutputs.addNamedOutput(job, "test",TextOutputFormat.class,Text.class,IntWritable.class);

        Path inpath = new Path("D:\\WangHao\\input\\WordCount.txt");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);

        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }
    public static class MultipleOutputsMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
        IntWritable intWritable = new IntWritable(1);
        Text text = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] s = line.split("\t");
            for (String v : s){
                text.set(v);
                context.write(text,intWritable);
            }
        }
    }
    public static class MultipleOutputsReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
        private MultipleOutputs<Text,IntWritable> mos;

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            //初始化多文件输出类
            mos = new MultipleOutputs<Text, IntWritable>(context);
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            //关闭资源
            mos.close();
        }

        IntWritable v = new IntWritable();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable value : values){
                sum += value.get();
                v.set(sum);
            }
            //使用MultipleOutputs实例.write(key,value,baseOutputPath)来代替context.write(key,value)方法
            mos.write(key,v,key.toString());
        }

    }
}
