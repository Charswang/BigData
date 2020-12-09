import org.apache.spark.SparkContext

object BasicAvgFromFile {
  def main(args: Array[String]): Unit = {
    /*if (args.length<2){
      println("Usage [sparkmaster] [inputfile]")
      System.exit(0)
    }
    val master = args(0)
    val path = args(1)
    val sc = new SparkContext(master,"BasicAvgFromFile","SPARKHOME")
    val inputdata = sc.textFile(path)
    val outputdata = inputdata.map(x=>(x.toInt,1)).fold((0,0))((x,y)=>(x._1+y._1,x._2+y._2))
    val result = outputdata._1 / outputdata._2.toFloat
    println(result)*/

    val master = args.length match{
      case x:Int if x>0 => args(0)
      case _ => "local"
    }
    val path = "D:\\WangHao\\Works\\BigData\\SparkWork\\src\\main\\resources\\inputfiles\\nums.txt"
    val sc = new SparkContext(master,"BasicAvgFromFile",System.getenv("SPARK_HOME"))
    val inputdata = sc.textFile(path)
//    val outputdata = inputdata.flatMap(x=>x.split(",")).map(x=>(x.toInt,1)).fold((0,0))((x,y)=>(x._1+y._1,x._2+y._2))
    val outputdata = inputdata.flatMap(x=>x.split(",")).map(x=>x.toInt).aggregate((0,0))((x,y)=>(x._1+y,x._2+1),(x,y)=>(x._1+y._1,x._2+y._2))
    val result = outputdata._1 / outputdata._2.toFloat
    println(result)
  }
}
