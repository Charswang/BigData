package compressWork;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionOutputStream;
import org.apache.hadoop.util.ReflectionUtils;

import java.io.*;

public class CompressTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        //获取输入流
        String inpath = "E:\\input\\compressTest.txt";
        FileInputStream fis = new FileInputStream(new File(inpath));

        //获取压缩方式
        //压缩方法
        String codecMethod = "org.apache.hadoop.io.compress.GzipCodec";
        //获取相应压缩方法的类
        Class codecClass = Class.forName(codecMethod);
        //利用反射获取压缩编码方式
        CompressionCodec codec = (CompressionCodec) ReflectionUtils.newInstance(codecClass,new Configuration());

        //获取输出流
        //codec.getDefaultExtension()获取压缩编码方式的后缀名称。
        FileOutputStream fos = new FileOutputStream(new File("E:\\input\\compressTest.txt" + codec.getDefaultExtension()));
        //创建出压缩输出流，和普通的输出流是不一样的。
        CompressionOutputStream out = codec.createOutputStream(fos);

        //输入输出流的拷贝
        IOUtils.copyBytes(fis,out,1024,false); //false表示不关闭流

        //关闭流
        IOUtils.closeStream(out);
        IOUtils.closeStream(fos);
        IOUtils.closeStream(fis);
    }
}
