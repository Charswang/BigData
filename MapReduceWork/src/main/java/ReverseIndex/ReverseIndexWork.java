package ReverseIndex;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 统计多个文件中单词，分别在每个文件中所出现的次数
 *
 * eg输出: Mapreduce		file1.txt->1
 *        Mapreduce         file2.txt->1
 *        Mapreduce         file3.txt->2
 * eg表示：Mapreduce在file1.txt中出现1次，在file2.txt中出现1次，在file3.txt中出现2次
 */
public class ReverseIndexWork {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(ReverseIndexWork.class);
        job.setMapperClass(ReverseIndexMapper.class);
        job.setReducerClass(ReverseIndexReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(Text.class);

        Path inpath = new Path("D:\\WangHao\\input\\reverseIndex");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);
        System.exit(job.waitForCompletion(true)?0:1);
    }
    public static class ReverseIndexMapper extends Mapper<LongWritable,Text,Text, IntWritable>{
        IntWritable v = new IntWritable(1);
        Text text = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split("\t");
            //获取切片信息
            InputSplit inputSplit = context.getInputSplit();
            FileSplit fileSplit = (FileSplit) inputSplit;
            //获取切片中文件路径信息
            String fileName = fileSplit.getPath().getName();
            for (String k : split){
                text.set(k + "->" + fileName);
                context.write(text,v);
            }
        }
    }
    public static class ReverseIndexReducer extends Reducer<Text,IntWritable,Text,Text>{
        Text k = new Text();
        Text v = new Text();
        @Override
        protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            for (IntWritable val : values){
                sum += val.get();
            }
            k.set(key.toString().split("->")[0]);
            v.set(key.toString().split("->")[1] + "->" + sum);
            context.write(k,v);
        }
    }
}
