import org.apache.spark.SparkContext

object BasicSum {
  def main(args: Array[String]): Unit = {
    val master = args.length match{
      case x:Int if x>0 => args(0)
      case _ => "local"
    }
    val sc = new SparkContext("local","BasicSum",System.getenv("SPARK_HOME"))
    val input = sc.parallelize(List(1,2,3,4,5))
    val result = input.fold(0)((x,y)=>x+y)
    println(result)
  }

}
