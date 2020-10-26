package case3_Serializer;

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

public class SerializerWork {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(SerializerWork.class);
        job.setMapperClass(SerializerWorkMapper.class);
        job.setReducerClass(SerializerWorkReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(SerializerBean.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(SerializerBean.class);


        Path inPath = new Path("D:\\WangHao\\casefiles\\case3input");
        FileInputFormat.addInputPath(job,inPath);
        Path outPath = new Path("D:\\WangHao\\casefiles\\case3output");
        if (outPath.getFileSystem(conf).exists(outPath)){
            outPath.getFileSystem(conf).delete(outPath,true);
        }
        FileOutputFormat.setOutputPath(job,outPath);

        System.exit(job.waitForCompletion(true)?0:1);
    }
    public static class SerializerWorkMapper extends Mapper<LongWritable, Text, Text, SerializerBean>{
        Text k = new Text();
        SerializerBean bean = new SerializerBean();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] strs = line.split("\t");
            k.set(strs[0]);
            bean.setUpFlow(Integer.parseInt(strs[strs.length-3]));
            bean.setDownFlow(Integer.parseInt(strs[strs.length-2]));

//            System.out.println(k);
//            System.out.println(bean);
            context.write(k,bean);
        }
    }
    public static class SerializerWorkReducer extends Reducer<Text, SerializerBean, Text, SerializerBean>{
        @Override
        protected void reduce(Text key, Iterable<SerializerBean> values, Context context) throws IOException, InterruptedException {
            int upFlow = 0;
            int downFlow = 0;
            for (SerializerBean bean : values){
                upFlow += bean.getUpFlow();
                downFlow += bean.getDownFlow();
            }
            SerializerBean serializerBean = new SerializerBean(upFlow,downFlow);
            context.write(key,serializerBean);
        }
    }
}
