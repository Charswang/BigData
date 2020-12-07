import org.apache.spark.SparkContext

object WordCount {
  def main(args: Array[String]): Unit = {
    val master = args.length match {
      case x: Int if x > 0 => args(0)
      case _ => "local"
    }
    val sc = new SparkContext(master,"WordCount",System.getenv("SPARK_HOME")) // 第三个参数--设置SPARK-HOME变量
    val input = args.length match{
      case x:Int if x>1 => sc.textFile(args(1))
      case _ => sc.parallelize(List("pandas","Spark","i like pandas"))
    }
    val words = input.flatMap(line => line.split(" "))
    args.length match{
      case x:Int if x>2 => {
        val counts = words.map(word => (word,1)).reduceByKey{case(x,y)=>(x+y)}
        counts.saveAsTextFile(args(2))
      }
      case _ =>{
        //countByValue()操作产生的类型是Map类型？
        val wc = words.countByValue()  // Map(Spark -> 1, pandas -> 2, i -> 1, like -> 1)
        println(wc)
      }
    }

  }

}
