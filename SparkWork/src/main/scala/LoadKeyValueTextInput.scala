import org.apache.spark.SparkContext
import org.apache.hadoop.io.Text
import org.apache.hadoop.mapred.KeyValueTextInputFormat

/**
 * 使用Hadoop中的KeyValueTextInput输入方式读取文件
 *
 * KeyValueTextInput在读取文件的时候，是以一个tab键为分隔，第一个tab键之前的当作键，之后的当作值
 * 所以在hadoopfile.txt中每一行第一个单词后面跟了tab键，后面的就以空格分离
 */
object LoadKeyValueTextInput {
  def main(args: Array[String]): Unit = {
    val master = args.length match {
      case x:Int if x>0 => args(0)
      case _ => "local"
    }
    val sc = new SparkContext(master,"LoadKeyValueTextInput",System.getenv("SPARK_HOME"))
    val inputPath = "src/main/resources/inputfiles/hadoopfile.txt"
    val data = sc.hadoopFile[Text,Text,KeyValueTextInputFormat](inputPath).map{
      case (x,y) => (x.toString,y.toString)
    }
    println(data.collect().mkString("--"))
  }
}
