package case4_WebLogKpi.PV;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class PvReducer extends Reducer<Text, IntWritable, Text, IntWritable> {
    IntWritable v = new IntWritable(1);
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values){
            sum += value.get();
        }
        v.set(sum);
        context.write(key,v);
    }
}
