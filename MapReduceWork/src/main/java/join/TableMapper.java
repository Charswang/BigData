package join;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.InputSplit;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileSplit;

import java.io.IOException;

/**
 * 对两个表中的数据进行一个标记。在reducer端才是真正的join
 */
public class TableMapper extends Mapper<LongWritable, Text, Text, TableBean> {
    //重写初始化方法，获取分片信息，因为每个分片是一个文件，所以转为FileSplit
    //从分片信息中得到数据来自哪一个文件。
    private String name;

    @Override
    protected void setup(Context context) throws IOException, InterruptedException {
        FileSplit inputSplit = (FileSplit) context.getInputSplit();
        name = inputSplit.getPath().getName();//获取文件名称
    }

    private TableBean tableBean = new TableBean();
    private Text k = new Text();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        String line = value.toString();

        //根据不同的文件名称，采用不同的封装TableBean
        if (name.startsWith("order")) {
            String[] s = line.split("\t");
            tableBean.setId(s[0]);
            tableBean.setPid(s[1]);
            tableBean.setAmount(Integer.parseInt(s[2]));
            tableBean.setPname("");//因为order文件中并没有产品信息，所以为了避免出错，所以将产品名称设为""
            tableBean.setFlag("order");

            k.set(s[1]);
        } else {
            String[] s = line.split("\t");
            tableBean.setId("");
            tableBean.setPid(s[0]);
            tableBean.setPname(s[1]);
            tableBean.setAmount(0);
            tableBean.setFlag("product");

            k.set(s[0]);
        }

        context.write(k,tableBean);
    }
}
