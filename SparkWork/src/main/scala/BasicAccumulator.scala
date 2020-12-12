import java.io.File

import org.apache.spark.SparkContext
import org.apache.spark.storage.StorageLevel

/**
 * Accumulator累加器经常被用作记录某些事件的数量
 */
object BasicAccumulator {
  def main(args: Array[String]): Unit = {
    val master = "local"
    val sc = new SparkContext(master,"BasicAccumulator",System.getenv("SPARK_HOME"))
    /*println(Some(("aa",1))) //Some((aa,1))
    println(Some("aa",2)) //Some((aa,2))
    println(Some("aa",2).getClass.getName) //scala.Some
    println(1.getClass.getName) // int*/
    val inputpath = "src/main/resources/inputfiles/loadNumsAccumulator.txt"
    val inputdata = sc.textFile(inputpath)
    val errorlines = sc.accumulator(0,"errorlines")
    val datalines = sc.accumulator(0,"datalines")
    val count = inputdata.flatMap(x=>{
      try{
        val data = x.split(" ")
        val num = data(1).toInt
//        datalines+=1
        datalines.add(1)
        /**
         * Can't read accumulator value in task
         */
        // println(datalines.value)  // Can't read accumulator value in task
        data
      }catch {
        case e: java.lang.NumberFormatException => {
          errorlines+=1
          None
        }
        case e:java.lang.ArrayIndexOutOfBoundsException => {
          errorlines += 1
          None
        }
      }
    })
    count.persist(StorageLevel.DISK_ONLY)
    println(count.collect().mkString("-"))
    println(s"${errorlines} -- ${datalines}")  // 4--5

    val outpath = "D:\\WangHao\\outputdata"
    del(new File(outpath)) // 删除已存在的输出目录
    count.saveAsTextFile(outpath)

    /**
     * 不保存文件，获取errorlines和datalines.values的时候就会是0
     * Why???
     * k，你没有action操作，你让他怎么执行flatMap操作，不执行flatMap操作累加器的值可不就是0嘛。zz
     *
     * 但是执行reduceByKey之后为啥仍然不行，执行collect和saveAsTextFile就可以？？
     * zz,因为reduceByKey是转换操作
     */
    println(errorlines) // 8
    println(s"${datalines.value}") //10

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
