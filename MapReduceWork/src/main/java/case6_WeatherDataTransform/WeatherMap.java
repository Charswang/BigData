package case6_WeatherDataTransform;

/**
 * map端操作
 */
import java.io.*;
import java.util.HashMap;
import java.util.Map.Entry;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import javax.sound.midi.Soundbank;

/**map端操作*/
public class WeatherMap extends Mapper<LongWritable,Text,Weather,NullWritable>{
    /********** begin **********/

    //方便用来进行join操作。
    HashMap<String,String> weatherMap = new HashMap<String,String>();
    protected void setup(Context context) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:\\WangHao\\casefiles\\case6input\\sky.txt")));
        String line = "";
        while ((line = br.readLine()) != null){
            String[] split = line.split(",");
            weatherMap.put(split[0],split[1]);
        }
        br.close();
    }

    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();
        String[] split = line.split("\\s+");

        String year = split[0];
        String month = split[1];
        String day = split[2];
        String hour = split[3];
        int temperature = Integer.parseInt(split[4]);
        String dew = split[5];
        int pressure = Integer.parseInt(split[6]);
        String wind_direction = split[7];
        int wind_speed = Integer.parseInt(split[8]);
        String sky_condition = split[9];
        String rain_1h = split[10];
        String rain_6h = split[11];

        if (split.length == 12 && Integer.parseInt(wind_direction)>=0 &&
                Integer.parseInt(wind_direction)<=360 && wind_speed >= 0 && pressure >= 0 &&
                Integer.parseInt(sky_condition)>=0 && Integer.parseInt(sky_condition)<=10 &&
                Integer.parseInt(dew)>=0 && Integer.parseInt(dew)<=100 &&
                temperature>=-40 && temperature<=50){
            sky_condition = weatherMap.get(sky_condition);
            Weather w = new Weather(year,month,day,hour,temperature,dew,pressure,wind_direction,wind_speed,sky_condition,rain_1h,rain_6h);
            context.write(w,NullWritable.get());
        }
    }
    /********** end **********/
}