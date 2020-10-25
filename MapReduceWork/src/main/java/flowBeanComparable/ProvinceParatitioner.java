package flowBeanComparable;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;


public class ProvinceParatitioner extends Partitioner<FlowBean, Text> {

    public int getPartition(FlowBean flowBean, Text text, int i) {
        //取前三位手机号码，按照前三位手机号码进行分区，并且在分区内部可以按照总流量进行倒序排序。
        String phone_num = text.toString().substring(0,3);
        int partition = 4;
        if ("135".equals(phone_num)){
            return 0;
        }else if ("136".equals(phone_num)){
            return 1;
        }else if ("137".equals(phone_num)){
            return 2;
        }else if ("138".equals(phone_num)){
            return 3;
        }
        return 4;
    }
}
