package com.BasicTest

import org.apache.spark.SparkContext

object BasicSortByKey {
  def main(args: Array[String]): Unit = {
    val master = "local"
    val sc = new SparkContext(master,"com.BasicTest.BasicSortByKey",System.getenv("SPARK_HOME"))
    val inputdata = sc.parallelize(List(("spark",5),("spark",2),("learning",1),("learning",3)))
    val outputdata = inputdata.sortByKey(true).collect().mkString("--")
    println(outputdata)

    // 先按照key进行升序排序，key相等的按照value升序排序
    val result = inputdata.sortBy(x=>(x._1,x._2),true).collect().mkString("--")
    println(result)

    // 自定义排序
//    val result2 = inputdata.sortBy(x=>new com.BasicTest.UDFSort(x._1,x._2)).sortByKey(true).collect().mkString("--")
    val result2 = inputdata.sortBy(x=>new UDFSort(x._1,x._2)).collect().mkString("--")
    println(result2)
  }
}
class UDFSort (val first:String,val second:Int) extends Ordered[UDFSort] with Serializable{
  override def compare(that: UDFSort): Int = {
    if (!first.equals(that.first)){
      first.compare(that.first)  // 字符串升序排序
    }else{
      that.second - second // Int类型降序排序
    }
  }
}
