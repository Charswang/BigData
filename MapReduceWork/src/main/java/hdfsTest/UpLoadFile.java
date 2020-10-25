package hdfsTest;


import lombok.extern.java.Log;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.yarn.api.records.URL;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class UpLoadFile {
    public static void main(String[] args) {
        System.out.println("Start");
        //1、获取文件系统
        Configuration configuration = new Configuration();
        //配置在集群上运行
//        configuration.set("fs.defaultFS","hdfs://192.168.246.130:9000");
        try {
//            FileSystem fileSystem = FileSystem.get(configuration);
            //客户端去操作hdfs时，是有一个用户身份的。默认情况下，hdfs客户端api会从jvm中获取一个参数来作为自己的用户身份：-DHADOOP_USER_NAME=itstar，itstar为用户名称。

            //另外一种方式，直接配置FileSystem
            FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.246.130:9000"),configuration,"had_user");

            //2、将本地文件上传到hdfs中 ??????
//            fileSystem.copyFromLocalFile(new Path("file:///hello.txt"),new Path("/data/test/hello1.copy.txt"));
//            fileSystem.copyToLocalFile(new Path("hdfs://192.168.246.130:9000/data/input/my_wordcount.txt"),new Path("./hello2.txt"));

            //向hdfs创建目录
//            fileSystem.mkdirs(new Path("/data/test"));

            //删除hdfs中的文件/目录
//            fileSystem.delete(new Path("/data/test/hello1.txt"),true);  //第二个参数指的是是否递归删除，这里删除文件，无所谓

            //重命名文件/文件夹
//            fileSystem.rename(new Path("/data/test"),new Path("/data/test_apk"));

            //创建文件
//            fileSystem.create(new Path("/data/test_apk/keyValueFormatFile1.txt"));
            //3、关闭资源
            fileSystem.close();
            System.out.println("Over");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
