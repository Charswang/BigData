package case2_TopN;

import case1_unique.UniqueWork;
import org.apache.commons.lang.ObjectUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.mortbay.io.bio.StringEndPoint;

import java.io.IOException;
import java.util.TreeMap;

public class TonNWork {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TonNWork.class);
        job.setMapperClass(TopNWorkMapper.class);
        job.setReducerClass(TopNWorkReducer.class);

        job.setMapOutputKeyClass(NullWritable.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(IntWritable.class);

        Path inpath = new Path("D:\\WangHao\\casefiles\\case2input");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\casefiles\\case2output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);

        System.exit(job.waitForCompletion(true)?0:1);
    }
    public static class TopNWorkMapper extends Mapper<LongWritable, Text, NullWritable, IntWritable>{
        TreeMap<Integer,String> treeMap = new TreeMap<Integer, String>();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] s = line.split(" ");
            for (String k:s){
                treeMap.put(Integer.parseInt(k),"");
                if (treeMap.size()>5){
                    treeMap.remove(treeMap.firstKey());
                }
            }

        }

        /**
         * cleanup等Map任务执行完毕之后，进行资源释放。此方法被MapReduce框架仅且执行一次
         * @param context
         * @throws IOException
         * @throws InterruptedException
         */
        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
//            System.out.println(treeMap.size());
            for (Integer i : treeMap.keySet()){
                context.write(NullWritable.get(), new IntWritable(i));
            }

        }
    }
    public static class TopNWorkReducer extends Reducer<NullWritable,IntWritable,  NullWritable, IntWritable>{
        TreeMap<Integer,String> treeMap = new TreeMap<Integer, String>();
        @Override
        protected void reduce(NullWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            for (IntWritable i : values){
                treeMap.put(i.get(),"");
                if (treeMap.size() > 5){
                    treeMap.remove(treeMap.firstKey());
                }
            }
            for (Integer i : treeMap.keySet()){
                context.write(NullWritable.get(),new IntWritable(i));
            }
        }
    }
}
