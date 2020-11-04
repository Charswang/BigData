package case4_WebLogKpi.PVTopTen;

import case4_WebLogKpi.KPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.*;

/**
 * 输出访问量最高的10个页面(按访问量降序输出)
 */
public class PVTopTenTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(PVTopTenTest.class);
        job.setMapperClass(PVTopTenMapper.class);
        job.setReducerClass(PVTopTenReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        Path inpath = new Path("D:\\WangHao\\casefiles\\case4input\\web1.txt");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\casefiles\\case4output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);
        System.exit(job.waitForCompletion(true)?0:1);
    }

    public static class PVTopTenMapper extends Mapper<LongWritable,Text,Text,IntWritable>{
        IntWritable v = new IntWritable(1);
        Text k = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            KPI kpi = KPI.parser(line);
            String request_url = kpi.getRequest_url();

            int index = request_url.indexOf("?");
            if (index > 0){
                request_url = request_url.substring(0,index);
            }
            k.set(request_url);
            context.write(k,v);
        }
    }
    public static class PVTopTenReducer extends Reducer<Text,IntWritable,Text, NullWritable>{
        List<PVTest> ll = new ArrayList<PVTest>();
        PVTest pvTest = new PVTest();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values){
                sum += val.get();
            }
            pvTest.setPv_sum(sum);
            pvTest.setUrl(key.toString());
            ll.add(pvTest);
            /*if (ll.size()>10){
                ll.remove(0);
            }*/
            Text k = new Text("ww");
            context.write(k,NullWritable.get());
        }

        @Override
        protected void cleanup(Context context) throws IOException, InterruptedException {
            Text k = new Text();
            for (PVTest l : ll){
                k.set(l.getUrl() + "\t" + l.getPv_sum());
                context.write(k,NullWritable.get());
            }
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class PVTest implements WritableComparable<PVTest> {
        private String url;
        private int pv_sum;

        @Override
        public int compareTo(PVTest o) {
            if (pv_sum > o.getPv_sum()){
                return 1;
            }else if (pv_sum < o.getPv_sum()){
                return -1;
            }
            return 0;
        }

        @Override
        public void write(DataOutput dataOutput) throws IOException {
            dataOutput.writeUTF(url);
            dataOutput.writeInt(pv_sum);
        }

        @Override
        public void readFields(DataInput dataInput) throws IOException {
            url = dataInput.readUTF();
            pv_sum = dataInput.readInt();
        }
    }
}
