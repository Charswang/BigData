package flowTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 对手机流量进行分析统计
 */
public class FlowCount {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        //获取Job对象
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJobName("FolwCount");
        //设置Jar的路径
        job.setJarByClass(FlowCount.class);
        //关联mapper和reducer
        job.setMapperClass(FlowCountMapper.class);
        job.setReducerClass(FlowCountReducer.class);
        //设置mapper输出的key和value类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(FlowCount.class);
        //设置reducer输出的key和value类型 / 设置最终输出的key和value类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(FlowCount.class);
        //设置输入输出路径
        Path inputPath = new Path(args[0]);
        FileInputFormat.addInputPath(job,inputPath);

        Path outputPath = new Path(args[1]);
        if (outputPath.getFileSystem(conf).exists(outputPath)){
            outputPath.getFileSystem(conf).delete(outputPath,true);
        }
        FileOutputFormat.setOutputPath(job,outputPath);
        //提交Job
        job.waitForCompletion(true);
    }

    //静态内部类
    //Mapper
    public static class FlowCountMapper extends Mapper<LongWritable, Text, Text, FlowBean> {
        //因为对每行数据都会调用map函数，所以把创建对象语句提出来，减少多次的对象创建
        private Text k = new Text();
        private FlowBean flowBean = new FlowBean();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //1、获取一行数据
            String line = value.toString();
            //2、切割数据
            String[] fields = line.split("\t");
            //3、封装对象
            k.set(fields[0]); //封装手机号作为key
            //封装上行流量和下行流量，因为避免文件中前面的其他字段又缺少，所以在这里以fields.length - 3 / fields.length - 2为索引
            flowBean.setUpFlow(Long.parseLong(fields[fields.length - 3]));
            flowBean.setDownFlow(Long.parseLong(fields[fields.length - 2]));
            //这里就不对sumFlow进行设置了，因为如果有100条数据，在map阶段对sum设置，需要执行100次
            //而在reduce中进行汇总的话，由于可能会出现重复的手机号，所以可以减少执行次数，减少时间，虽然数据少的时候效果并不显著。

            //4、写出
            context.write(k,flowBean);

        }
    }

    //Reducer
    public static class FlowCountReducer extends Reducer<Text,FlowBean,Text,FlowBean>{

        private FlowBean flowBean = new FlowBean();
        @Override
        protected void reduce(Text key, Iterable<FlowBean> values, Context context) throws IOException, InterruptedException {
            long sum_upFlow = 0;
            long sum_downFlow = 0;
            for (FlowBean flowBean:values){
                sum_upFlow += flowBean.getUpFlow();
                sum_downFlow += flowBean.getDownFlow();
            }
            flowBean.setUpFlow(sum_upFlow);
            flowBean.setDownFlow(sum_downFlow);
            flowBean.setSumFlow(sum_upFlow + sum_downFlow);

            context.write(key,flowBean);
        }
    }
}

