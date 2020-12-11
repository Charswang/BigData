import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD

import scala.reflect.ClassTag

/**
 * 2020-12-10 by wanghao
 * 两个RDD进行union操作。其中设计cogroup操作和flatMapValues操作
 * 注意cogroup操作之后类型变成了Array[(Int, (Iterable[String], Iterable[String]))]
 * flatMapValues操作将两个Iterator中的元素进行了合并，具体看笔记。x++y操作
 */
object BasicIntersectByKey {
  def intersectByKey[K:ClassTag,V:ClassTag](rdd1:RDD[(K,V)], rdd2:RDD[(K,V)]):RDD[(K,V)]={
    rdd1.cogroup(rdd2).flatMapValues{
      case (Nil,_) => None
      case (_,Nil) => None
      case (x,y) => x++y
    }
  }
  def main(args: Array[String]): Unit = {
    val master = "local"
    val sc = new SparkContext(master,"BasicIntersectByKey",System.getenv("SPARK_HOME"))
    val rdd1 = sc.parallelize(List((1, "panda"), (2, "happy")))
    val rdd2 = sc.parallelize(List((2, "pandas"),(2, "pan")))
    val iRdd = intersectByKey(rdd1, rdd2)
    iRdd.collect().foreach(println(_))
  }
}
