package com.BasicTest

import org.apache.spark.SparkContext

/**
 * 2020-12-11
 * WordCount例子中，使用单词个数进行降序排序
 * sortBy(x=>x._2,false)  // false代表降序，true为升序排序
 */
object BasicSort {
  def main(args: Array[String]): Unit = {
    val master = "local"
    val sc = new SparkContext(master,"Sort",System.getenv("SPARK_HOME"))
    val data = sc.parallelize(List("count value","spark test spark test test"))
    val outputdata = data.flatMap(_.split(" "))
    println(outputdata.collect().mkString("--")) //count--value--spark--test--sortspark

    outputdata.flatMap(_.split(" ")).map(x=>(x,1)).reduceByKey(_+_).sortBy(x=>x._2,false).collect().foreach(println(_))
  }
}
