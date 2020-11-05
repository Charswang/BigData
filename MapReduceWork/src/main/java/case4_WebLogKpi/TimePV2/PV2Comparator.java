package case4_WebLogKpi.TimePV2;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class PV2Comparator extends WritableComparator {
    protected PV2Comparator(){
        super(PV2Bean.class,true);
    }

    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        PV2Bean aBean = (PV2Bean) a;
        PV2Bean bBean = (PV2Bean) b;

        int result;
        if (aBean.getIp().equals(bBean.getIp())){
            result = 0;
        }else{
            result = -1;
        }
        return result;
    }
}
