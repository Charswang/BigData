package case4_WebLogKpi.TimePV2;

import case4_WebLogKpi.KPI;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class TimePV2Mapper extends Mapper<LongWritable, Text,PV2Bean, IntWritable> {
    PV2Bean pv2Bean = new PV2Bean();
    IntWritable v = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        KPI kpi = KPI.parser(value.toString());

        if (kpi.getIs_validate()){
            pv2Bean.setIp(kpi.getRemote_ip());
            pv2Bean.setTime(kpi.getRequest_time().split("/")[2].split(":")[1]);
            context.write(pv2Bean,v);
        }
    }
}
