package case4_WebLogKpi.TimePV1;

import case4_WebLogKpi.KPI;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TimePVMapper extends Mapper<LongWritable, Text,Text,LongWritable> {
    Text time = new Text();
    LongWritable v = new LongWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        KPI kpi = KPI.parser(value.toString()); //转换KPI对象
        if (kpi.getIs_validate()){
            //截取时间段中的小时信息
            time.set(kpi.getRequest_time().split("/")[2].split(":")[1] + "点区间：");
            context.write(time,v);
        }
    }
}
