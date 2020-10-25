package groupingComparable;

import org.apache.avro.Schema;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 对数据首先按照id升序排序，然后按照价格降序排序，但是要输出每个id中价格最高的。
 */
public class GroupingComparable {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(GroupingComparable.class);
        job.setMapperClass(GroupingComparableMapper.class);
        job.setReducerClass(GroupingComparableReducer.class);

        job.setMapOutputKeyClass(OrderBean.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(OrderBean.class);
        job.setOutputValueClass(NullWritable.class);

        Path inpath = new Path("");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);
        //设置reduce端的分组
        job.setGroupingComparatorClass(OrderGroupingComparator.class);

        job.waitForCompletion(true);
    }
    public static class GroupingComparableMapper extends Mapper<LongWritable, Text,OrderBean, NullWritable>{
        OrderBean orderBean = new OrderBean();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split("\t");
            orderBean.setOrder_id(Integer.parseInt(split[0]));
            orderBean.setPrice(Double.parseDouble(split[2]));
            context.write(orderBean,NullWritable.get());
        }
    }
    public static class GroupingComparableReducer extends Reducer<OrderBean,NullWritable,OrderBean,NullWritable>{
        //分组排序：1、自定义类继承WritableComparator；2、重写compare()方法
        @Override
        protected void reduce(OrderBean key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            //情况1、
            //输出每种相同order_id中价格最高的价格及id
            context.write(key,NullWritable.get());

            //情况2、
            //输出每个分组中所有的订单的信息
            /*for (NullWritable nullWritable: values){
                context.write(key,NullWritable.get());
            }*/

            //情况3、
            //输出相同order_id中价格排名前两位的价格及id
            /*for (int i=0;i<2;i++){
                context.write(key,NullWritable.get());
            }*/
        }
    }
}
