package case4_WebLogKpi.TimePV1;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class TimePVReducer extends Reducer<Text, LongWritable,Text,LongWritable> {
    LongWritable v = new LongWritable();
    @Override
    protected void reduce(Text key, Iterable<LongWritable> values, Context context) throws IOException, InterruptedException {
        long sum = 0;
        for (LongWritable val : values){
            sum += val.get();
        }
        v.set(sum);
        context.write(key,v);
    }
}
