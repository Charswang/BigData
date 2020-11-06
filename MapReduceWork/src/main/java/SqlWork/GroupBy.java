package SqlWork;

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

import java.io.IOException;

/**
 * mapreduce实现group by语句
 *
 * sql示例：select customer sum(order_price) from orders group by customer
 *
 * orders中的文本数据：两列：customer, order_price
 *
 * 这里的跟wordcount没区别
 */
public class GroupBy {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(GroupBy.class);
        job.setMapperClass(GroupByMapper.class);
        job.setReducerClass(GroupByReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        Path inpath = new Path("D:\\WangHao\\input\\sqlinput\\orders.txt");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);
        System.exit(job.waitForCompletion(true)?0:1);
    }
    public static class GroupByMapper extends Mapper<LongWritable, Text,Text, IntWritable>{
        Text k = new Text();
        IntWritable v = new IntWritable();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split("\t");

            k.set(split[0]);
            v.set(Integer.parseInt(split[1]));

            context.write(k,v);
        }
    }
    public static class GroupByReducer extends Reducer<Text,IntWritable,Text,IntWritable>{
        IntWritable v = new IntWritable();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values){
                sum += val.get();
            }
            v.set(sum);

            context.write(key,v);
        }
    }
}
