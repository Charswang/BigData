package com.BasicTest

import java.io.File

import org.apache.spark.SparkContext

object BasicAccumulator2 {
  def main(args: Array[String]) {
    val master = "local"
    val inputFile = "src/main/resources/inputfiles/loadNumsAccumulator.txt"
    val sc = new SparkContext(master, "BasicLoadNums", System.getenv("SPARK_HOME"))
    val file = sc.textFile(inputFile)
    val errorLines = sc.accumulator(0)  // Create an Accumulator[Int] initialized to 0
    val dataLines = sc.accumulator(0)  // Create a second Accumulator[Int] initialized to 0
    val counts = file.flatMap(line => {
      try {
        val input = line.split(" ")
        val data = Some((input(0), input(1).toInt))
        dataLines += 1
        data
      } catch {
        case e: java.lang.NumberFormatException => {
          errorLines += 1
          None
        }
        case e: java.lang.ArrayIndexOutOfBoundsException => {
          errorLines += 1
          None
        }
      }
    }).reduceByKey(_ + _)

    /**
     * ？？？
     * 同样是action操作：
     * 为什么执行reduceByKey操作之后获取不到累加器的值，但是collect和saveAsTextFile就可以
     *
     * zz,因为reduceByKey是转换操作
     */
//    print(counts.collect().mkString("-"))
    if (errorLines.value < 0.1 * dataLines.value) {
      val outpath = "D:\\WangHao\\outputdata"
      del(new File(outpath))
      counts.saveAsTextFile(outpath)
//      println(s"Too many errors ${errorLines.value} for ${dataLines.value}")
    } else {
      println(s"Too many errors ${errorLines.value} for ${dataLines.value}")
    }
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
