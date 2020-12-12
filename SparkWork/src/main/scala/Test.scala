import org.apache.spark.SparkContext

object Test {
  def main(args: Array[String]): Unit = {
    val sc = new SparkContext("local","Test",System.getenv("SPARK_HOME"))
    val data = sc.textFile("src/main/resources/inputfiles/loadNumsAccumulator.txt")
    val result = data.flatMap(x=>x.split(" ")).collect().mkString("-")
    println(result)
  }
}
