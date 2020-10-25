package hdfsTest;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import org.apache.hadoop.hdfs.DistributedFileSystem;
import org.apache.hadoop.hdfs.protocol.DatanodeInfo;
import org.apache.hadoop.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.URI;


public class TestHDFS {
    public static void main(String[] args) throws Exception {
//        LookFileInfo();
//        getLocation();
//        getNodeInfo();
//        checkFile();
//        uploadFile();
//        uploadFile1();
//        downloadFileIO();
//        readSeekFile();
//        createDirs();
//        fileDirStatus();
        pathFilter();
//        deletefile();
    }

    /**
     * 查看指定目录下,文件/目录的详细信息
     */
    public static void LookFileInfo() throws Exception {
        //创建配置信息对象
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.246.130:9000"), configuration, "had_user");

        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/data"));
        for (FileStatus fileStatus : fileStatuses) {
//           boolean bool = fileStatus.isDirectory();
            String str = fileStatus.isDirectory() ? "目录" : "文件"; // 判断文件/目录
            String name = fileStatus.getPath().getName(); //获取文件/目录名称
            String path = fileStatus.getPath().toString(); //获取路径转为字符串;

            System.out.println(str + "->" + name + " path:" + path);
            System.out.println(fileStatus.getAccessTime());
            System.out.println(fileStatus.getBlockSize());
            System.out.println(fileStatus.getGroup()); //文件目录所属组
            System.out.println(fileStatus.getLen());
            System.out.println(fileStatus.getOwner()); //文件/目录所属用户
            System.out.println(fileStatus.getModificationTime());
            System.out.println(fileStatus.getPermission()); //查看用户对该文件/目录的权限
            System.out.println("-----------------------------------------------------");
        }
        fileSystem.close();
    }

    /**
     * 查找文件在hdfs集群中的位置
     *
     * @throws Exception
     */
    public static void getLocation() throws Exception {
        System.out.println("Start");
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.246.130:9000"), configuration, "had_user");
        //getFileStatus(new Path(..))  结果包括指定文件的元数据信息
        //getStatus(new Path(..))  结果包括文件系统中文件或和目录的元数据
        FileStatus fileStatus = fileSystem.getFileStatus(new Path("/data/test_apk/hello1.txt"));
//        FsStatus status = fileSystem.getStatus(new Path("/data/test_apk"));
        BlockLocation[] fileBlockLocations = fileSystem.getFileBlockLocations(fileStatus, 0, fileStatus.getLen());
        for (int i = 0, h = fileBlockLocations.length; i < h; i++) {
            String[] hosts = fileBlockLocations[i].getHosts();
            for (String host : hosts) {
                System.out.println("block:" + i + "-- location:" + host);
            }
        }
        System.out.println("Over");
        fileSystem.close();
    }

    /**
     * 获取DataNode信息
     *
     * @throws Exception
     */
    public static void getNodeInfo() throws Exception {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.246.130:9000"), configuration, "had_user");
        DistributedFileSystem fileSystem1 = (DistributedFileSystem) fileSystem;
        DatanodeInfo[] dataNodeStats = fileSystem1.getDataNodeStats();
        for (int i = 0, h = dataNodeStats.length; i < h; i++) {
            System.out.println("Datanode:" + i + "  info:" + dataNodeStats[i].getHostName());
        }
        fileSystem.close();
        fileSystem1.close();
    }

    /**
     * 判断是文件/目录
     *
     * @throws Exception
     */
    public static void checkFile() throws Exception {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.246.130:9000"), configuration, "had_user");
        FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/data"));
        for (FileStatus fileStatus : fileStatuses) {
            if (fileStatus.isFile()) {
                System.out.println("f--" + fileStatus.getPath().getName());
            } else {
                System.out.println("d--" + fileStatus.getPath().getName());
            }
        }
        fileSystem.close();
    }

    /**
     * 上传文件   注意文件扩展名
     *
     * @throws Exception
     */
    public static void uploadFile() throws Exception {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.246.130:9000"), configuration, "had_user");
        fileSystem.copyFromLocalFile(new Path("hello.txt"), new Path("/data/test_apk/hello2.txt"));
        System.out.println("Over");
        fileSystem.close();
    }

    /**
     * IO流上传文件
     *
     * @throws Exception
     */
    public static void uploadFile1() throws Exception {
        Configuration configuration = new Configuration();
        FileSystem fileSystem = FileSystem.get(new URI("hdfs://192.168.246.130:9000"), configuration, "had_user");

        //创建输入流
        FileInputStream fileInputStream = new FileInputStream(new File("D:/WangHao/hello.txt"));
        //获取输出路径
        Path filePath = new Path("hdfs://192.168.246.130:9000/data/test_apk/hello3.txt");
        //创建输出流
        //FSDataOutputStream类不支持指定位置写入，只能在文件末尾处进行写入。
        FSDataOutputStream fsDataOutputStream = fileSystem.create(filePath);
        //流对接
        IOUtils.copyBytes(fileInputStream, fsDataOutputStream, 4096, false);

        fileSystem.close();
    }

    /**
     * 使用IO流下载hdfs指定路径文件
     *
     * @throws Exception
     */
    public static void downloadFileIO() throws Exception {
        Configuration configuration = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://192.168.246.130:9000"), configuration, "had_user");
        //创建输入流
        FSDataInputStream fsDataInputStream = fs.open(new Path("/data/test_apk/hello3.txt"));

        //创建输出流
        FileOutputStream fileOutputStream = new FileOutputStream(new File("hello3_copy.txt"));

        //流对接输出到控制台
        //IOUtils.copyBytes(fsDataInputStream,System.out,4096,false);

        //流对接输出到指定文件
        IOUtils.copyBytes(fsDataInputStream, fileOutputStream, 4096, true);
        //参数buffSize用来拷贝的缓冲区大小。
        //close是否关闭数据流，如果false的话，要try..catch一下,在finally中关闭数据流。IOUtils.closeStream(instream),IOUtils.closeStream(outStream)

        fs.close();
    }

    /**
     * 读取字符偏移后的数据
     */
    public static void readSeekFile() throws Exception {
        //读取第一块数据
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), conf, "had_user");
        FSDataInputStream fsDataInputStream = fs.open(new Path("/data/test_apk/hello3.txt"));

        FileOutputStream fileOutputStream = new FileOutputStream(new File("./hello_copy.txt"));
        IOUtils.copyBytes(fsDataInputStream, fileOutputStream, 4096, false);
        fsDataInputStream.seek(2);//字符偏移量，从第三个字符开始读取
        //seek()方法开销较高
        IOUtils.copyBytes(fsDataInputStream, System.out, 4096, true);
    }

    /**
     * fileSystem.mkdirs(new Path(...))可递归创建目录
     *
     * @throws Exception
     */
    public static void createDirs() throws Exception {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), conf, "had_user");

        boolean b = fs.mkdirs(new Path("/data/testDir1/testDir2"));
        System.out.println(b);
    }

    /**
     * listStatus()列出文件或目录的状态信息
     */
    public static void fileDirStatus() throws Exception {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), conf, "had_user");
        FileStatus[] fileStatuses = fs.listStatus(new Path("/data"));
        for (FileStatus fileStatus : fileStatuses) {
            String str = fileStatus.isDirectory() ? "--dir--" : "--file--";
            String name = fileStatus.getPath().getName();
            String path = fileStatus.getPath().toString();

            System.out.println("文件类型：" + str);
            System.out.println("文件路径：" + path);
            System.out.println("文件名称：" + name);
            System.out.println("块的大小：" + fileStatus.getBlockSize());
            System.out.println("文件所有者：" + fileStatus.getOwner() + "--" + fileStatus.getGroup());
            System.out.println("文件权限：" + fileStatus.getPermission());
            System.out.println("文件长度：" + fileStatus.getLen());
            System.out.println("文件备份数：" + fileStatus.getReplication());
            System.out.println("文件最后修改时间：" + fileStatus.getModificationTime());
            System.out.println("-----------------------------------------------------------");
        }
    }

    /**
     * 路径过滤
     * globStatus()返回与路径相匹配的所有文件的FileStatus对象数组，并按路径进行排序。
     * PathFilter、命令作为可选项可以进一步对匹配进行限制。
     */
    public static void pathFilter() throws Exception {
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"), conf, "had_user");

        FileStatus[] fileStatuses = fs.globStatus(new Path("/data/test*"));
        System.out.println("匹配：/data/test*的文件/目录");
        for (FileStatus fileStatus : fileStatuses){
            if (fs.exists(fileStatus.getPath())){
                System.out.println(fileStatus.getPath().toString());
            }
        }
    }


    /**
     * 删除数据  fileSystem.delete(Path,boolean[为true时，非空目录会被删除]) --- 返回boolean
     */
    public static void deletefile() throws Exception{
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(new URI("hdfs://master:9000"),conf,"had_user");

        boolean delete_result = fs.delete(new Path("/data/testDir1"), true);
        System.out.println(delete_result);
    }

    /**
     * 数据流
     * LocalFileSystem
     * DistributedFileSystem
     */
    public static void dataStream(){

    }

}
