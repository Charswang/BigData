import org.apache.spark.SparkContext

/**
 * 2020/12/9
 * 每个键的平均值
 */
object PerKeyAvg {
  def main(args: Array[String]): Unit = {
    val master = args.length match {
      case x : Int if x>0 => args(0)
      case _ => "local"
    }
    val sc = new SparkContext(master,"PerKeyAvg",System.getenv("SPARK_HOME"))
    val inputdata = sc.parallelize(List(("coffee",1),("coffee",2),("panda",4)))
    val result = inputdata.combineByKey(v=>(v,1),(x:(Int,Int),y)=>(x._1+y,x._2+1),(x:(Int,Int),y:(Int,Int))=>(x._1+y._1,x._2+y._2))
    result.map{case (key,xy)=>(key,xy._1/xy._2)}.collect().foreach(println(_))
//    result.map{case (key,xy)=>(key,xy._1/xy._2)}.collectAsMap().foreach(println(_))
//    result.map{case (key,xy)=>(key,xy._1/xy._2)}.collectAsMap().map(println(_))
  }
}
