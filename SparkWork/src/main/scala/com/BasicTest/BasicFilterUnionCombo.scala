package com.BasicTest

import org.apache.spark.SparkContext

/**
 * 对两个RDD进行Filter，然后进行union合并操作
 */
object BasicFilterUnionCombo {
  def main(args: Array[String]): Unit = {
    val master = args.length match{
      case x:Int if x>0 => args(0)
      case _ => "local"
    }
    val sc = new SparkContext(master,"BasciFilterUnionCombo",System.getenv("SPARK_HOME"))
    val inputpath = "src/main/resources/inputfiles/filterword.txt"
    val data = sc.textFile(inputpath)
    val errorsRDD = data.filter(_.contains("error")) //这样rdd中的一个元素就是一行
    val errorRDD = data.flatMap(x=>x.split(" ")).filter(x=>x.contains("error"))
//    println(data.flatMap(x=>x.split(" ")).collect().mkString("-"))
    val warnsRDD = data.filter(_.contains("warn"))
    val warnRDD = data.flatMap(x=>x.split(" ")).filter(x=>x.contains("warn"))
//    println(errorsRDD.collect().mkString("-"))
    val badlinesRDD = errorsRDD.union(warnsRDD)
    val badlineRDD = errorRDD.union(warnRDD)

    println(badlinesRDD.collect().mkString("\n"))
    println("----------------------------------")
    println(badlineRDD.collect().mkString("\n"))
  }
}
