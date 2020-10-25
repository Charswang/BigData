package flowBeanComparable;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class FlowBeanTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(FlowBeanTest.class);
        job.setMapperClass(FlowBeanTestMapper.class);
        job.setReducerClass(FlowBeanTestReducer.class);

        job.setMapOutputKeyClass(FlowBean.class);
        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowBean.class);

        //设置自定义分区
        job.setPartitionerClass(ProvinceParatitioner.class);
        job.setNumReduceTasks(5);

        Path inPath = new Path("/data/input/phone_data.txt");
        FileInputFormat.addInputPath(job,inPath);
        Path outPath = new Path("/data/output");
        if (outPath.getFileSystem(conf).exists(outPath)){
            outPath.getFileSystem(conf).delete(outPath,true);
        }
        FileOutputFormat.setOutputPath(job,outPath);

        job.waitForCompletion(true);
    }

    public static class FlowBeanTestMapper extends Mapper<LongWritable, Text, FlowBean, Text> {
        FlowBean k = new FlowBean();
        Text v = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //数据：1   13785698457 192.168.1.10    www.haha.com    100 200 0

            //读取数据
            String line = value.toString();
            //分割数据
            String[] split = line.split("\t");
            //封装数据
            v.set(split[1]);
            k.setUpFlow(Long.parseLong(split[split.length-3]));
            k.setDownFlow(Long.parseLong(split[split.length-2]));
            k.setSumFlow(Long.parseLong(split[split.length-1]));

            context.write(k,v);
        }
    }

    public static class FlowBeanTestReducer extends Reducer<FlowBean,Text,Text, FlowBean> {
        @Override
        protected void reduce(FlowBean key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            for(Text value : values){
                context.write(value,key);
            }
        }
    }
}
