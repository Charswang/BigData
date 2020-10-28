package case4_WebLogKpi.PV;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class PvDriver {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(PvDriver.class);
        job.setMapperClass(PvMapper.class);
        job.setReducerClass(PvReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        /*//开启Map输出端压缩
        conf.setBoolean("mapreduce.map.output.compress",true);
        //设置Map输出压缩方式
        conf.setClass("mapreduce.map.output.compress.codec", BZip2Codec.class, CompressionCodec.class);*/

        Path inpath = new Path("D:\\WangHao\\casefiles\\case4input");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\casefiles\\case4output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);

        System.exit(job.waitForCompletion(true)?0:1);
    }
}
