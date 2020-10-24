package customOutputFormat;

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

public class FilterOutputFormatTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(FilterOutputFormatTest.class);
        job.setMapperClass(FilterOutputFormatMapper.class);
        job.setReducerClass(FilterOutputFormatReducer.class);

        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //将自定义的OutputFormat设置到job中的组件中去
        job.setOutputFormatClass(FilterOutputFormat.class);

        Path inpath = new Path("E:\\input\\webInfo.txt");
        FileInputFormat.addInputPath(job,inpath);
        //虽然在自定义的FilterOutputFormat中设置了输出路径，但是还会有表示是否成功的SUCCESS的文件会产出。
        //虽然自定义了outputformat，但是由于自定义的FilterOutputFormat是继承自FileOutputFormat，
        //而FileOutputFormat要输出一个SUCCESS文件，所以还得指定一个输出目录
        Path outpath = new Path("E:\\output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);
        System.exit(job.waitForCompletion(true)?0:1);
    }
    public static class FilterOutputFormatMapper extends Mapper<LongWritable, Text, Text, NullWritable>{
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            context.write(value,NullWritable.get());
        }
    }
    public static class FilterOutputFormatReducer extends Reducer<Text, NullWritable, Text, NullWritable>{
        Text k = new Text();
        @Override
        protected void reduce(Text key, Iterable<NullWritable> values, Context context) throws IOException, InterruptedException {
            //给每一个网站信息后面加上回车换行，使在自定义OutpuFormat中输出流带着换行，否则文件中会出现一行。
            String s = key.toString();
            s = s + "\r\n";
            k.set(s);
            //避免有重复的网址信息，所以要循环一下
            for (NullWritable value : values){
                context.write(k,value);
            }
        }
    }
}
