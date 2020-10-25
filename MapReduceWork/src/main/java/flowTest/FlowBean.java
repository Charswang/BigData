package flowTest;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

//lombok注解
//@NoArgsConstructor
//@AllArgsConstructor
@Data
public class FlowBean implements Writable {
    private long upFlow;
    private long downFlow;
    private long sumFlow;

    //构造方法
    public FlowBean(){
        super();
    }

    public FlowBean(long upFlow, long downFlow) {
        super();
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    //序列化，将对象转成字节序列形式，在传递和保存对象时.保证对象的完整性和可传递性
    //重写序列化
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeLong(upFlow);
        dataOutput.writeLong(downFlow);
        dataOutput.writeLong(sumFlow);
    }

    //重写反序列化
    //注意要和上面的序列化方法，语句顺序一致,写序列化谁就先反序列化谁
    public void readFields(DataInput dataInput) throws IOException {
        upFlow = dataInput.readLong();
        downFlow = dataInput.readLong();
        sumFlow = dataInput.readLong();
    }

    //重写toString方法，以便于mapper拆分
    @Override
    public String toString() {
        return upFlow +"\t"+ downFlow + "\t" + sumFlow;
    }
}
