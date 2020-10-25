package compressMapReduce;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.compress.BZip2Codec;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.GzipCodec;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class CompressReduceOuput {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(CompressMapOutput.class);
        job.setMapperClass(CompressMapOutput.CompressMapOutputMapper.class);
        job.setReducerClass(CompressMapOutput.CompressMapOutputReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);

        //开启Map输出端压缩
        conf.setBoolean("mapreduce.map.output.compress",true);
        //设置Map输出压缩方式为gzip
        conf.setClass("mapreduce.map.output.compress.codec", GzipCodec.class, CompressionCodec.class);

        //开启Reduce端输出压缩
        FileOutputFormat.setCompressOutput(job,true);
        //设置Reduce端输出的压缩方式为bzip2
        FileOutputFormat.setOutputCompressorClass(job,BZip2Codec.class);

        Path inpath = new Path("E:\\input\\wordcount.txt");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("E:\\output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);

        System.exit(job.waitForCompletion(true)?0:1);
    }
    public static class CompressReduceOuputMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
        IntWritable v = new IntWritable(1);
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split("\t");
            for (String k : split){
                context.write(new Text(k),v);
            }
        }
    }
    public static class CompressReduceOuputReducer extends Reducer<Text,IntWritable,Text,IntWritable> {
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable v : values){
                sum += 1;
            }
            context.write(key,new IntWritable(sum));
        }
    }
}
