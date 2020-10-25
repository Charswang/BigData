/**
 * Tuple操作
 * 元组不可变，元组可以包括不同类型的元素。
 */
object TestTuple {
  def main(args: Array[String]): Unit = {
    state_tuple()
    println("--------------------------------------")
    iteration_tuple()
    println("--------------------------------------")
    tuple_toString()
    println("--------------------------------------")
    tuple_swap()
  }

  //声明元组，迭代元组
  def state_tuple(): Unit ={
    var t1 = (1,2,"tuple")
    val sum = t1._1 + t1._2 + t1._3
    println("t1--" + t1)
    println("通过t1._1来访问元组第一个元素--元组所有元素和相加：" + sum)
  }

  //使用t1.productIteration.foreach(item => println(item))进行迭代
  def iteration_tuple(): Unit ={
    var t1 = new Tuple10(1,2,3,4,5,6,7,8,9,10)
    //println(t1)
    print("t1迭代：")
    t1.productIterator.foreach(item => print(item + " "))
    println()
  }

  //元组转字符串
  def tuple_toString(): Unit ={
    var t1 = (1,2,3,"hello")
    println("元组t1：" + t1)
    println("元组t1转字符串：" + t1.toString())
    println(t1.toString().getClass)
  }

  //元组元素交换,仅适用于两个元素。
  def tuple_swap(): Unit ={
    var t1 = (1,2)
    println("原t1：" + t1)
    println("swap后的t1：" + t1.swap)
  }
}
