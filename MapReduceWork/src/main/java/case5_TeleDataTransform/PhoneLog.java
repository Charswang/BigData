package case5_TeleDataTransform;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PhoneLog implements WritableComparable<PhoneLog> {
    private String userNameA;
    private String userNameB;
    private String numberA;
    private String numberB;
    private String startTime;
    private String endTime;
    private long timeLen;
    private String addressA;
    private String addressB;

    public void setPhoneLog(String userA, String userB, String userA_Phone, String userB_Phone, String startTime,
                                       String endTime, Long timeLen, String userA_Address, String userB_Address){
        this.userNameA = userA;
        this.userNameB = userB;
        this.numberA = userA_Phone;
        this.numberB = userB_Phone;
        this.startTime = startTime;
        this.endTime = endTime;
        this.timeLen = timeLen;
        this.addressA = userA_Address;
        this.addressB = userB_Address;
    }

    @Override
    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeUTF(userNameA);
        dataOutput.writeUTF(userNameB);
        dataOutput.writeUTF(numberA);
        dataOutput.writeUTF(numberB);
        dataOutput.writeUTF(startTime);
        dataOutput.writeUTF(endTime);
        dataOutput.writeLong(timeLen);
        dataOutput.writeUTF(addressA);
        dataOutput.writeUTF(addressB);
    }

    @Override
    public void readFields(DataInput dataInput) throws IOException {
        userNameA = dataInput.readUTF();
        userNameB = dataInput.readUTF();
        numberA = dataInput.readUTF();
        numberB = dataInput.readUTF();
        startTime = dataInput.readUTF();
        endTime = dataInput.readUTF();
        timeLen = dataInput.readLong();
        addressA = dataInput.readUTF();
        addressB = dataInput.readUTF();
    }

    @Override
    public String toString() {
        return userNameA + "," + userNameB + "," + numberA + "," + numberB + "," + startTime + "," + endTime + "," +
                timeLen + "," + addressA + "," + addressB;
    }

    @Override
    public int compareTo(PhoneLog o) {
        if (this.hashCode()==o.hashCode()){
            return 0;
        }else{
            return -1;
        }
    }
}
