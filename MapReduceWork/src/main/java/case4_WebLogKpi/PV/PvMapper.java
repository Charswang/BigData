package case4_WebLogKpi.PV;

import case4_WebLogKpi.KPI;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class PvMapper extends Mapper<LongWritable, Text, Text, IntWritable> {
    IntWritable v = new IntWritable(1);
    Text k = new Text();
    KPI kpi = new KPI();
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
//        kpi = KPI.parser(value.toString());
        kpi = KPI.filterPv(value.toString()); //过滤路径进行统计。
        if (kpi.getIs_validate()){
            k.set(kpi.getRequest_url());
            context.write(k,v);
        }
    }
}
