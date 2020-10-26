package case3_Serializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 创造手机流量对象，并实现Writable接口，实现序列化。
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class SerializerBean implements Writable {
    private int upFlow;
    private int downFlow;
    private int sumFlow;

    public SerializerBean(){
        super();
    }
    public SerializerBean(int upFlow,int downFlow){
        super();
        this.upFlow = upFlow;
        this.downFlow = downFlow;
        this.sumFlow = upFlow + downFlow;
    }

    /**
     * 记住是writeInt，别介去写write.
     * 找这个错误，找了很长时间啦！！！
     * @param dataOutput
     * @throws IOException
     */
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(upFlow);
        dataOutput.writeInt(downFlow);
        dataOutput.writeInt(sumFlow);
    }

    public void readFields(DataInput dataInput) throws IOException {
        upFlow = dataInput.readInt();
        downFlow = dataInput.readInt();
        sumFlow = dataInput.readInt();
    }

    @Override
    public String toString() {
        return upFlow + "\t" + downFlow + "\t" + sumFlow;
    }
}
