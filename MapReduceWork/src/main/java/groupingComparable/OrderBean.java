package groupingComparable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.hadoop.io.WritableComparable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderBean implements WritableComparable<OrderBean> {

    private int order_id;
    private double price;

    public int compareTo(OrderBean o) {
        //升序
        if (order_id > o.order_id){
            return 1;
        }else if (order_id < o.order_id){
            return -1;
        }else{
            //降序
            if (price > o.price){
                return -1;
            }else if (price < o.price){
                return 1;
            }else{
                return 0;
            }
        }
    }

    public void write(DataOutput dataOutput) throws IOException {
        dataOutput.writeInt(order_id);
        dataOutput.writeDouble(price);
    }

    public void readFields(DataInput dataInput) throws IOException {
        order_id = dataInput.readInt();
        price = dataInput.readDouble();
    }

    @Override
    public String toString() {
        return order_id + "\t" + price;
    }
}
