package case4_WebLogKpi.TimePV2;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Partitioner;

/**
 * 按照访问时间段进行设置分区。，但是还差一步，差一步按照倒序排序，找出在该时间段访问量前5名
 */
public class PV2Partitioner extends Partitioner<PV2Bean, IntWritable> {
    @Override
    public int getPartition(PV2Bean bean, IntWritable intWritable, int i) {
        if ("17".equals(bean.getTime())){
            return 0;
        }else if ("18".equals(bean.getTime())){
            return 1;
        }else if ("21".equals(bean.getTime())){
            return 2;
        }else {
            return 3;
        }
    }
}
