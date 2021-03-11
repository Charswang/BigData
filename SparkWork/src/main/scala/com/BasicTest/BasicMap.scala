package com.BasicTest

import org.apache.spark.SparkContext

object BasicMap {
  def main(args: Array[String]): Unit = {
    val master = args.length match{
      case x:Int if x>0 => args(0)
      case _ => "local"
    }
    val sc = new SparkContext(master,"BasicMap",System.getenv("SPARK_HOME"))
    val inputdata = sc.parallelize(List(1,2,3,4))
    val outputdata = inputdata.map(x=>x*x)
//    print(outputdata.collect().foreach(x=>println(x)))
    println(outputdata.collect().mkString(","))
  }
}
