package counter;

import lombok.extern.java.Log;
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

/**
 * 假如一个文件counterfile1.txt，规范的格式是3个字段，“\t”作为分隔符，其中有2条异常数据，一条数据是只有2个字段，一条数据是有4个字段。其内容如下所示：
 */
public class CustomCounter {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(CustomCounter.class);
        job.setMapperClass(CounterMapper.class);
        job.setReducerClass(CounterReducer.class);

//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(Text.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        Path inpath = new Path("D:\\WangHao\\input\\counterfile1.txt");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);
        System.exit(job.waitForCompletion(true)?0:1);
    }
    public static class CounterMapper extends Mapper<LongWritable, Text, Text, Text>{
        public static enum LOG_PROCESSOR_COUNTER{
            //枚举对象BAD_RECORDS_LONG来统计长数据，枚举对象BAD_RECORDS_SHORT来统计短数据
            BAD_RECORDS_LONG,BAD_RECORDS_SHORT
        }
        //Map阶段定义计数器
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] s = line.split("\t");
            System.out.println(s.length);
            if (s.length > 3){
                //动态定义计数器
                context.getCounter("ErrorCounter","toolong").increment(1);
                //枚举类声明计数器
                context.getCounter(LOG_PROCESSOR_COUNTER.BAD_RECORDS_LONG).increment(1);
            }else if (s.length < 3){
                //动态定义计数器
                context.getCounter("ErrorCounter","tooshort").increment(1);
                //枚举类定义计数器
                context.getCounter(LOG_PROCESSOR_COUNTER.BAD_RECORDS_SHORT).increment(1);
            }else{
                context.write(value,new Text("-"));
            }
            System.out.println(context.getCounter(LOG_PROCESSOR_COUNTER.BAD_RECORDS_SHORT).getValue());
        }
    }
    public static class CounterReducer extends Reducer<Text,Text,Text,Text>{
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            context.write(key,new Text("**"));
        }
    }
}
