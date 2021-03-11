package com.BasicTest

import java.io.File

import org.apache.spark.SparkContext

object BasicAvgFromFiles {
  def main(args: Array[String]): Unit = {
    val master = args.length match {
      case x:Int if x>0 => args(0)
      case _ => "local"
    }
    val sc = new SparkContext(master,"BasicAvgFromFiles",System.getenv("SPARK_HOME"))
    val path = "D:\\WangHao\\Works\\BigData\\SparkWork\\src\\main\\resources\\inputfiles"
    /**
     *
     */
    //    val inputdata = sc.wholeTextFiles(path)
    /*val outputdata = inputdata.mapValues{y=>
      val nums = y.split(",").map(_.toDouble)
      nums.sum / nums.size.toDouble
    }*/
    /*val outpath="D:\\WangHao\\output"
    if (new File(outpath).exists()){
      del(new File(outpath))
    }
    outputdata.saveAsTextFile(outpath)*/

    val inputdata = sc.textFile(path)
    val outputdata = inputdata.take(10)
    println(outputdata)
  }
  def del(dir : File): Unit = {
    if(dir.isDirectory) {
      val files = dir.listFiles()
      for(file <- files) {
        del(file)
      }
    }
    dir.delete()
  }
}
