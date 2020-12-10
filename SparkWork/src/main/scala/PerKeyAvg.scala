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
    //result是个键值对形式的RDD，所以直接使用result.map(key,xy=>(key,xy._1/xy._2))会出错,
    //所以在使用map操作的话，要使用case指定元素形式[指明是使用键值对转键值对形式]result.map{case (key,xy)=>(key,xy._1/xy._2)}
    result.map{case (key,xy)=>(key,xy._1/xy._2)}.collect().foreach(println(_))
//    result.map{case (key,xy)=>(key,xy._1/xy._2)}.collectAsMap().foreach(println(_))
//    result.map{case (key,xy)=>(key,xy._1/xy._2)}.collectAsMap().map(println(_))

    // 另外不使用map方法，而是直接使用针对键值对中的value进行转换操作的mapValue方法，也可以完成操作。
    result.mapValues(xy=>xy._1/xy._2.toFloat).collect().foreach(println(_))
  }
}
