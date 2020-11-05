package case4_WebLogKpi.TimePV2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TimePV2Reducer extends Reducer<PV2Bean, IntWritable,PV2Bean,IntWritable> {
    IntWritable v = new IntWritable();
    @Override
    protected void reduce(PV2Bean key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable val : values){
            sum += val.get();
        }
        v.set(sum);
        context.write(key,v);
    }
}
