package case6_WeatherDataTransform;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

/**
 * 驱动类
 *
 * 数据说明如下：a.txt；
 * 数据切分方式：一个或多个空格；
 * 数据所在位置：/user/test/input/a.txt；
 *
 * 2005 01   01  16     -6      -28      10157   260    31        8            0     -9999
 * 年	月	日	小时	温度	湿度	气压	风向	风速	天气情况 	1h降雨量  6h降雨量
 *
 * sky.txt；[天气情况变为其对应的云属]
 * 数据切分方式：逗号；
 * 数据所在位置：data/sky.txt或者/user/test/input/sky.txt。
 *
 * 1        积云
 * 天气情况	cumulus
 *
 * 清洗规则：
 * 1、将分隔符转化为逗号；
 * 2、清除不合法数据：字段长度不足，风向不在[0,360]的，风速为负的，气压为负的，天气情况不在[0,10]，湿度不在[0,100]，温度不在[-40,50]的数据；
 * 3、将a.txt与sky.txt的数据以天气情况进行join操作，把天气情况变为其对应的云属；
 * 4、对进入同一个分区的数据排序； 排序规则： （1）同年同月同天为key； （2）按每日温度升序； （3）若温度相同则按风速升序； （4）风速相同则按压强降序。
 * 5、设置数据来源文件路径及清洗后的数据存储路径： 数据来源路径为： /user/test/input/a.txt （HDFS）； 清洗后的数据存放于：/user/test/output (HDFS)。
 *
 * 数据清洗后如下：
 * 2005,01,01,16,-6,-28,10157,260,31,卷云,0,-9999
 */
public class WeatherTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Configuration configuration = new Configuration();
        Job job = Job.getInstance(configuration);
        job.setJarByClass(WeatherTest.class);
        job.setMapperClass(WeatherMap.class);
        job.setMapOutputKeyClass(Weather.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setReducerClass(WeatherReduce.class);
        job.setOutputKeyClass(Weather.class);
        job.setOutputValueClass(NullWritable.class);
        //job.setNumReduceTasks(3);
        //job.setPartitionerClass(Auto.class);
        Path inPath = new Path("D:\\WangHao\\casefiles\\case6input\\a.txt");
        Path out = new Path("D:\\WangHao\\casefiles\\case6output");
        if (out.getFileSystem(configuration).exists(out)){
            out.getFileSystem(configuration).delete(out,true);
        }
        FileInputFormat.addInputPath(job, inPath);
        FileOutputFormat.setOutputPath(job, out);
        job.waitForCompletion(true);
    }
}
