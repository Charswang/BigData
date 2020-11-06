package ReverseIndex;

import com.google.inject.internal.util.$Strings;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import javax.jnlp.IntegrationService;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 统计多个文件中单词，分别在每个文件中所出现的次数
 *
 * eg输出: Mapreduce		file1.txt->1	file2.txt->1	file3.txt->2
 * eg表示：Mapreduce在file1.txt中出现1次，在file2.txt中出现1次，在file3.txt中出现2次
 */
public class ReverseIndexWork2 {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(ReverseIndexWork2.class);
        job.setMapperClass(ReverseIndex2Mapper.class);
        job.setReducerClass(ReverseIndex2Reducer.class);

//        job.setMapOutputKeyClass(Text.class);
//        job.setMapOutputValueClass(Text.class);
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
    public static class ReverseIndex2Mapper extends Mapper<LongWritable,Text,Text,Text>{
        Text k = new Text();
        Text v = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] split = value.toString().split("\t");

            FileSplit fileSplit = (FileSplit) context.getInputSplit();
            String fileName = fileSplit.getPath().getName();

            for (String str : split){
                k.set(str);
                v.set(fileName + "->1");
                context.write(k,v);
            }
        }
    }
    public static class ReverseIndex2Reducer extends Reducer<Text,Text,Text,Text>{
        Text v = new Text();
        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int sum = 0;
            Map<String,Integer> treeMap = new TreeMap<String,Integer>();
            for (Text val : values){

                //获取文件名称
                String fileName = val.toString().split("->")[0];

                //map中不存在键，则添加上键
                if (!treeMap.keySet().contains(fileName)){

                    //在这里出了错！！一定要注意这里，如果map中没有文件名，那么一定要重置sum=0，不然，sum会在上一个文件的单词统计数量上继续累加。
                    sum = 0; //！！！！！！！！！！
                    treeMap.put(fileName,0);
                }
                sum += Integer.parseInt(val.toString().split("->")[1]);

                //重新设置单词在该文件中出现的次数
                treeMap.put(fileName,sum);
            }

            //循环map，将文件名称与相对应的单词统计数量，加到String中去，以便转换成Text，进行写出。
            String v_str = "";
            for (String str : treeMap.keySet()){
                v_str = v_str + "\t" + str + "->" + treeMap.get(str);
            }

            //设置value
            v.set(v_str);

            context.write(key,v);
        }
    }
}
