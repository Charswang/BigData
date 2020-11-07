package case5_TeleDataTransform;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * 20/11/7 by wanghao
 *
 * 电信数据清洗
 * https://www.educoder.net/shixuns/zv4whj7u/challenges
 *
 * a.txt -> 15733218050,15778423030,1542457633,1542457678,450000,530000
 *          呼叫者手机号,接受者手机号,开始时间戳（s）,接受时间戳（s）,呼叫者地址省份编码,	接受者地址省份编码
 * Mysql数据库: -> 数据库名：mydb,用户表：userphone[id,phone,trueName],地址省份表：allregion[id,CodeNum,Address]
 *
 * 清洗规则：
 * 1、处理数据中的时间戳（秒级）将其转化为2017-06-21 07:01:58，年-月-日 时:分:秒 这种格式
 * 2、处理数据中的省份编码，结合mysql的表数据对应，将其转换成省份名称；
 * 3、处理用户手机号，与mysql的表数据对应，关联用户的真实姓名；
 * 4、处理数据中的开始时间与结束时间并计算通信时长(以秒为单位)；
 * 5、设置数据来源文件路径及清洗后的数据存储路径：数据来源路径为： /user/test/input/a.txt (HDFS);清洗后的数据存放于：/user/test/output (HDFS)。
 *
 * 数据清洗后：例子
 * 邓二,张倩,13666666666,15151889601,2018-03-29 10:58:12,2018-03-29 10:58:42,30,黑龙江省,上海市
 */
public class LogMR {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);
        job.setJarByClass(LogMR.class);
        job.setMapperClass(LogMRMapper.class);

        job.setMapOutputKeyClass(PhoneLog.class);
        job.setMapOutputValueClass(NullWritable.class);
        job.setNumReduceTasks(0);

        Path inpath = new Path("D:\\WangHao\\casefiles\\case5input");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\casefiles\\case5output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);
        job.waitForCompletion(true);
    }
    public static class LogMRMapper extends Mapper<LongWritable, Text, PhoneLog, NullWritable>{
        Map<String,String> userMap = new HashMap<String, String>();
        Map<String,String> addressMap = new HashMap<String, String>();

        PhoneLog pl = new PhoneLog();
        Text text = new Text();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            Connection conn = DBHelper.getConnection();
            try {
                Statement statement =conn.createStatement();
                String sql1 = "select * from userphone";
                ResultSet resultSet = statement.executeQuery(sql1);
                while (resultSet.next()){
                    String phone = resultSet.getString(2);
                    String trueName = resultSet.getString(3);
                    userMap.put(phone,trueName);
                }

                String sql2 = "select * from allregion";
                ResultSet resultSet1 = statement.executeQuery(sql2);
                while (resultSet1.next()){
                    String codeNum = resultSet1.getString(2);
                    String address = resultSet1.getString(3);
                    addressMap.put(codeNum,address);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] split = line.split(",");

            if (split.length == 6){
                String userNameA = userMap.get(split[0]);
                String userNameB = userMap.get(split[1]);
                String addressA = addressMap.get(split[4]);
                String addressB = addressMap.get(split[5]);

                long startTimestamp = Long.parseLong(split[2]);
                long endTimestamp = Long.parseLong(split[3]);
                String startTime = sdf.format(startTimestamp*1000); //将秒转换为毫秒.
                String endTime = sdf.format(endTimestamp*1000);

                long timeLen = endTimestamp - startTimestamp;

                pl.setPhoneLog(userNameA,userNameB,split[0],split[1],startTime,endTime,timeLen,addressA,addressB);

                context.write(pl,NullWritable.get());

            }
        }
    }
}
