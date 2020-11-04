package MapJoin;

import org.apache.commons.lang.StringUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

/**
 * 为什么会产生两个文件？？？
 */
public class MapJoinTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, URISyntaxException {
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf);

        job.setJarByClass(MapJoinTest.class);
        job.setMapperClass(MapJoinMapper.class);

        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(NullWritable.class);

        //设置Reducer数量为0。不进行Reduce阶段。减少了shuffle和io所需要的时间.
        job.setNumReduceTasks(0);
        //先将产品表加载到缓存中去,注意URI中的路径写法，前面要加上file:///，路径中使用单条斜杠
        job.addCacheFile(new URI("file:///d:/WangHao/input/joininput/product.txt"));

        Path inpath = new Path("D:\\WangHao\\input\\joininput");
        FileInputFormat.addInputPath(job,inpath);
        Path outpath = new Path("D:\\WangHao\\output");
        if (outpath.getFileSystem(conf).exists(outpath)){
            outpath.getFileSystem(conf).delete(outpath,true);
        }
        FileOutputFormat.setOutputPath(job,outpath);

        boolean result = job.waitForCompletion(true);
        System.exit(result?0:1);
    }

    public static class MapJoinMapper extends Mapper<LongWritable,Text,Text,NullWritable>{
        Map<String,String> hm = new HashMap<String, String>();
        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            //pid   pname     ---   产品表的结构

            //获取job已经加载到缓存中的文件
            URI[] cacheFiles = context.getCacheFiles();

            //获取文件路径
            String path = cacheFiles[0].getPath().toString();

            //创建输入流，读
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path),"utf-8"));
            String line;
            //import org.apache.commons.lang.StringUtils;
            while (StringUtils.isNotEmpty(line = br.readLine())){
                String[] split = line.split("\t");
                //将每一行数据拆分，放入hashmap中。
                hm.put(split[0],split[1]);
            }

            IOUtils.closeStream(br);
        }

        Text k = new Text();
        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            //加载订单表，并与产品表执行join操作
            String line = value.toString();
            String[] split = line.split("\t");
            String pid = split[1]; //获取到每一行订单信息中的产品pid
            String pname = hm.get(pid); //根据产品pid从产品表中获取产品pname
            line = line + "\t" + pname; //直接将pname这一列加到订单表后面，完成拼接

            k.set(line);
            context.write(k,NullWritable.get());
        }
    }
}
