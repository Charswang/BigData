package Reducejoin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/**
 * 两个表的join操作，将共用字段作为key，其余两个表的全部作为value
 * 加上flag字段的原因是因为，要知道数据具体来自哪个文件，以便采用哪种具体的措施。
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TableBean implements Writable {
    private String id;
    private String pid;
    private int amount;
    private String pname;
    private String flag;


    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(id);
        dataOutput.writeUTF(pid);
        dataOutput.writeInt(amount);
        dataOutput.writeUTF(pname);
        dataOutput.writeUTF(flag);
    }

    public void readFields(DataInput dataInput) throws IOException {
        id = dataInput.readUTF();
        pid = dataInput.readUTF();
        amount = dataInput.readInt();
        pname = dataInput.readUTF();
        flag = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return id +"\t" + pname + "\t" + amount;
    }
}
