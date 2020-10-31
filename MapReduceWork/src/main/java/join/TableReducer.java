package join;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

/**
 * 相同pid的订单数据可以是多个，不过产品数据只能是一个，因为数据库中不会出现两行一样的数据
 */
public class TableReducer extends Reducer<Text,TableBean,TableBean, NullWritable> {
    /**
     * 这里创建对象，和使用BeanUtils将values中的对象拷贝到新的tmpBean中去，是因为foreach循环values的那些并没有创建对象，而只是引用。
     * 所以要先使用BeanUtils将引用的信息，倒到tmpBean中去。
     * @param key
     * @param values
     * @param context
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    protected void reduce(Text key, Iterable<TableBean> values, Context context) throws IOException, InterruptedException {
        //创建存储订单信息的集合
        ArrayList<TableBean> tableBeans = new ArrayList<TableBean>();
        //创建产品信息对象
        TableBean pdBean = new TableBean();

        //这里的tableBean并没有创建对象，而只是一个引用。
        for (TableBean tableBean : values){
            if ("order".equals(tableBean.getFlag())){
                TableBean tmpBean = new TableBean();
                try {
                    //BeanUtils.copyProperties(要导入到那个对象中去,源信息)
                    BeanUtils.copyProperties(tmpBean,tableBean);
                    tableBeans.add(tmpBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }else {
                try {
                    BeanUtils.copyProperties(pdBean,tableBean);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
        //对订单信息设置产品名称，join操作
        for (TableBean orderBeans : tableBeans){
            orderBeans.setPname(pdBean.getPname());
            context.write(orderBeans,NullWritable.get());
        }
    }
}
