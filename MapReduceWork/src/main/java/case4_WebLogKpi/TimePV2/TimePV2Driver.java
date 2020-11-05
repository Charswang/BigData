package case4_WebLogKpi.TimePV2;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 筛选用户，每个用户时间段访问量统计
 * 1、这里只做出来了，按时间段划分，每个用户在某个时间段的访问量。
 * 2、按照访问时间段进行设置分区。，但是还差一步，差一步按照倒序排序，找出在该时间段访问量前5名
 */
public class TimePV2Driver {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(TimePV2Driver.class);
        job.setMapperClass(TimePV2Mapper.class);
        job.setReducerClass(TimePV2Reducer.class);

        job.setOutputKeyClass(PV2Bean.class);
        job.setOutputValueClass(IntWritable.class);

        //设置了辅助排序，好像没啥用。
//        job.setGroupingComparatorClass(PV2Comparator.class);

        //按照访问时间段进行设置分区
        job.setPartitionerClass(PV2Partitioner.class);
        job.setNumReduceTasks(4);

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
