package case4_WebLogKpi.TimePV1;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 每个小时时间段用户访问量统计
 */
public class TimePVDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TimePVDriver.class);
        job.setMapperClass(TimePVMapper.class);
        job.setReducerClass(TimePVReducer.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(LongWritable.class);

        Path inpath = new Path("D:\\WangHao\\casefiles\\case4input\\web1.txt");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\casefiles\\case4output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);
        System.exit(job.waitForCompletion(true)?0:1);
    }
}
