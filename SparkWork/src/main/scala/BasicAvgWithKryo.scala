import org.apache.spark.{SparkConf, SparkContext}

/**
 * 2020-12-11 by wanghao
 * KryoSerializer序列化  --  Kryo序列化库
 * kryo序列化机制比默认的java序列化机制，速度更快，序列化后的数据也要更小
 * 使用kryo序列化优化之后，可以是网络传输的数据表少，在集群中耗费的内存资源也会大大减少
 * spark性能调优：
 * 1、设置KryoSerializer是在SparkConf中进行设置 -- conf.set("spark.serializer", "org.apache.spark.serializer.KryoSerializer")//使用Kryo序列化库
 * 2、kyro不支持所有对象的序列化，可以自定义类事项对象的序列化
 * 3、可以在库中注册用户自定义的类型 -- conf.set("spark.kryo.registrator", classOf[Nothing].getName)//在Kryo序列化库中注册自定义的类集合
 */
object BasicAvgWithKryo {
  def main(args: Array[String]): Unit = {
    val master = "local"
    val conf = new SparkConf().setMaster("local").setAppName("BasicAvgWithKryo")
    conf.set("spark.serializer","org.apache.spark.serializer.KryoSerializer") // 设置Kryo序列化

    val sc = new SparkContext(conf)
    val inputdata = sc.parallelize(List(1,2,3,4))
    val outputdata = inputdata.aggregate((0,0))((x,y)=>(x._1+y,x._2+1),(x,y)=>(x._1+y._1,x._2+y._2))
    println(outputdata._1 / outputdata._2.toFloat)

  }
}
