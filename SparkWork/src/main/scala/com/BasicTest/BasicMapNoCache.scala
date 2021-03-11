package com.BasicTest

import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

/**
 * 验证有缓存RDD和没有缓存RDD的情况下，执行两个action操作所运行的时间对比
 * 运行时间几乎相差一倍
 */
object BasicMapNoCache {
  def main(args: Array[String]): Unit = {
    val master = args.length match{
      case x:Int if x>0 => args(0)
      case _ => "local"
    }
    val sc = new SparkContext(master,"BasicMapNoCache",System.getenv("SPARK_HOME"))
    val inputdata = sc.parallelize(List(1,2,3,4,5))
    val outputdata = inputdata.map(x=>x*x)
    outputdata.persist(StorageLevel.DISK_ONLY) // 持久化RDD
    //将执行两次上面计算出来outputdata的过程，因为没有对RDD进行持久化，没有放到内存中
    val t1 = System.currentTimeMillis()
    println(outputdata.count())
    println(outputdata.collect().mkString(","))
    val t2 = System.currentTimeMillis()
    println(t2-t1) // 没有持久化RDD的运行时间：1360； 持久化RDD之后的运行时间：677
  }
}
