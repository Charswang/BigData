package case4_WebLogKpi.TimePV2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class PV2Bean implements Writable, WritableComparable<PV2Bean> {
    private String ip;
    private String time;

    /**
     * 序列化
     * @param dataOutput
     * @throws IOException
     */
    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(ip);
        dataOutput.writeUTF(time);
    }

    /**
     * 反序列化
     * @param dataInput
     * @throws IOException
     */
    @Override
    public void readFields(DataInput dataInput) throws IOException {
        ip = dataInput.readUTF();
        time = dataInput.readUTF();
    }

    /**
     * 是要进行排序的，如果这里不实现WritableComparable的compareTo方法的话，会报错。hadoop貌似不知道要怎样去自动排序。
     *
     * 这里找错误真的是，找了好久，解决了，但是仍然是不理想。
     * 原本是想按照ip进行排序，然后再是时间段，再加上分组排序、设置分区，使每个用户输出一个文件，每个用户中的文件包含了该用户在所有时间段的访问量。
     * 但是原本想用辅助排序和这里的compareTo排序并分组，但是由于ip格式问题，一直没能解决。！！！
     *
     * 结果就只能是先按时间段进行排序，时间段相等的看ip是否相等，相等的话返回0。不相等的话返回-1
     * @param o
     * @return
     */
    @Override
    public int compareTo(PV2Bean o) {
        if (Integer.parseInt(time) > Integer.parseInt(o.getTime())) {
            return 1;
        } else if (Integer.parseInt(time) < Integer.parseInt(o.getTime())) {
            return -1;
        } else {
            //如果时间段相等的话，看ip是否相同，相同的话返回0，表示对象一样，mapper中的key也一样
            if (ip.equals(o.getIp())){
                return 0;
            }
            //不相同的话返回-1
            return -1;
        }
    }

    @Override
    public String toString() {
        return ip + "\t" + time;
    }

}
