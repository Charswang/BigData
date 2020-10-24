package compressWork;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.io.compress.CompressionCodec;
import org.apache.hadoop.io.compress.CompressionCodecFactory;
import org.apache.hadoop.io.compress.CompressionInputStream;

import java.io.*;

public class DecompressTest {
    public static void main(String[] args) throws IOException {
        //检查压缩方式
        CompressionCodecFactory factory = new CompressionCodecFactory(new Configuration());
        Path inpath = new Path("E:\\input\\DecompressTest.txt.gz");
        // 获取压缩编码方式。
        CompressionCodec codec = factory.getCodec(inpath);
        //通过压缩编码方式来判断文件是否是压缩文件。
        if (codec == null){
            System.out.println("No codec found for " + inpath.toString());
            System.exit(1);
        }
        //获取输入流
        FileInputStream fis = new FileInputStream(new File(inpath.toString()));
        CompressionInputStream cis = codec.createInputStream(fis);
        //获取输出流
        FileOutputStream fos = new FileOutputStream(new File(inpath.toString() + ".decode"));
        //输入输出流拷贝
        IOUtils.copyBytes(cis,fos,1024,false);
        //文件流关闭
        IOUtils.closeStream(fos);
        IOUtils.closeStream(cis);
        IOUtils.closeStream(fis);
    }
}
