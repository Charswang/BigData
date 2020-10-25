package groupingComparable;

import org.apache.hadoop.io.WritableComparable;
import org.apache.hadoop.io.WritableComparator;

public class OrderGroupingComparator extends WritableComparator {
    //进入这个分组类之前不会进入reducer，执行完这个，才会将分组放到reducer中，继续运行。

    /**
     * ！！！！！
     * 使用分组排序时，必须要创建
     */
    protected OrderGroupingComparator(){
        //第一个参数表示，要比较的对象。true是要创建key，如果是false的话，会报空指针异常。
        super(OrderBean.class,true);
    }
    @Override
    public int compare(WritableComparable a, WritableComparable b) {
        //要求只要id相同，就认为是相同的key
        OrderBean aBean = (OrderBean) a;
        OrderBean bBean = (OrderBean) b;

        int result;
        if (aBean.getOrder_id() > bBean.getOrder_id()){
            //
            result = 1;
        }else if (aBean.getOrder_id() < bBean.getOrder_id()){
            result = -1;
        }else{
            //因为mapper和reducer中的key都是OrderBean对象，所以即使order_id相同的OrderBean对象也不会放到一个reduce中去。
            //返回0的话，就像等于是返回True。表示两个OrderBean对象的order_id是相同的。可以将这两个OrderBean对象放到同一个reduce中去。
            result = 0;
        }
        return result;
    }
}
