package flowBeanComparable;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 对phone_data中的数据进行按照总流量倒序排序。
 * 因为之前是将手机号作为key，bean对象作为value。
 * 现在要将bean对象作为key，然后按照key中的总流量属性进行排序，bean对象中要重写comparaTo方法，对总流量进行排序。
 */
@Data
//@AllArgsConstructor
@NoArgsConstructor
public class FlowBean implements WritableComparable<FlowBean> {

    private long upFlow;
    private long downFlow;
    private long sumFlow;

    public FlowBean(long upFlow,long downFlow){
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        sumFlow = upFlow + downFlow;
    }
    //比较
    public int compareTo(FlowBean o) {
        //核心比较,降序
        if (sumFlow > o.getSumFlow()){
            return -1;
        }else if (sumFlow < o.getSumFlow()){
            return 1;
        }
        return 0;
    }

    //序列化
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    //反序列化
    public void readFields(DataInput dataInput) throws IOException {
        upFlow = dataInput.readLong();
        downFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }
}
