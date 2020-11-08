package case7_EmploymentTransform;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

public class JsonTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(JsonTest.class);
        job.setMapperClass(JsonMap.class);
        job.setMapOutputKeyClass(Employment.class);
        job.setMapOutputValueClass(NullWritable.class);

        //因为没有Reducer，所以在这里将reduce数量设置为0
        job.setNumReduceTasks(0);

        Path inPath = new Path("D:\\WangHao\\casefiles\\case7input\\a.json");
        Path out = new Path("D:\\WangHao\\casefiles\\case7output");
        if (out.getFileSystem(configuration).exists(out)){
            out.getFileSystem(configuration).delete(out,true);
        }
        FileInputFormat.addInputPath(job, inPath);
        FileOutputFormat.setOutputPath(job, out);
        job.waitForCompletion(true);
    }
}
