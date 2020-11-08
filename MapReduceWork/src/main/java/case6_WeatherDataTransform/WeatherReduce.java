package case6_WeatherDataTransform;

/**
 * reduce端操作
 */
import java.io.IOException;

import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.mapreduce.Reducer;
/**reduce操作*/
public class WeatherReduce extends Reducer<Weather,NullWritable,Weather,NullWritable> {
    /********** begin **********/
    protected void reduce(Weather key,Iterable<NullWritable> values,Context context)
            throws IOException, InterruptedException{
        for (NullWritable nullWritable : values){
            context.write(key,NullWritable.get());
        }
    }


    /********** end **********/

}
