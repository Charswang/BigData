package case6_WeatherDataTransform;

/**
 * 封装对象
 */
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

/**封装对象*/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Weather implements WritableComparable<Weather> {
    //年
    private String year;
    //月
    private String month;
    //日
    private String day;
    //小时
    private String hour;
    //温度
    private int temperature;
    //湿度
    private String dew;
    //气压/压强
    private int pressure;
    //风向
    private String wind_direction;
    //风速
    private int wind_speed;
    //天气情况
    private String sky_condition;
    //1小时降雨量
    private String rain_1h;
    //6小时降雨量
    private String rain_6h;

    public void readFields(DataInput in) throws IOException {
        year = in.readUTF();
        month = in.readUTF();
        day = in.readUTF();
        hour = in.readUTF();
        temperature = in.readInt();
        dew = in.readUTF();
        pressure = in.readInt();
        wind_direction = in.readUTF();
        wind_speed = in.readInt();
        sky_condition = in.readUTF();
        rain_1h = in.readUTF();
        rain_6h = in.readUTF();
    }

    public void write(DataOutput out) throws IOException {
        out.writeUTF(year);
        out.writeUTF(month);
        out.writeUTF(day);
        out.writeUTF(hour);
        out.writeInt(temperature);
        out.writeUTF(dew);
        out.writeInt(pressure);
        out.writeUTF(wind_direction);
        out.writeInt(wind_speed);
        out.writeUTF(sky_condition);
        out.writeUTF(rain_1h);
        out.writeUTF(rain_6h);
    }

    public int compareTo(Weather o) {
        /********** begin **********/
        int result = this.year.compareTo(o.getYear());
        if (result == 0){
            result = this.month.compareTo(o.getMonth());
            if (result == 0){
                result = this.day.compareTo(o.getDay());
                if (result == 0){
                    result = this.temperature - o.getTemperature();
                    if (result == 0){
                        result = this.wind_speed - o.getWind_speed();
                        if (result == 0){
                            result = o.getPressure() - this.pressure;
                        }
                        return result;
                    }
                    return result;
                }
                return result;
            }
            return result;
        }
        return result;
        /********** end **********/
    }

    public String toString() {
        return year + "," + month + "," + day + "," + hour + "," + temperature + "," + dew + "," + pressure + ","
                + wind_direction + "," + wind_speed + "," + sky_condition + "," + rain_1h + "," + rain_6h;
    }
}
