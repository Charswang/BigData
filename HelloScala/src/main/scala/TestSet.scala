/**
 * Set集合，元素唯一，没有重复项
 * Scala默认使用scala.collection.immutable.Set为不可变集合
 * 若要使用可变集合，则使用scala.collection.mutable.Set下的Set
 */

object TestSet {
  def main(args: Array[String]): Unit = {
    firstSet()
    println("---------------------------------------------")
    addAndDel()
    println("---------------------------------------------")
    optionsSet()
  }

  def firstSet(): Unit = {
    val s1 = Set(1, 1, 2, 2, 3, 3, 4)
    println(s1) //Set(1, 2, 3, 4)
    println(s1.getClass)
    println(s1.getClass.getName)
    println(s1.drop(2)) //Set(3, 4)
    println(s1.exists(item => item % 2 == 0)) //true
  }

  /**
   * add和remove方法只在可变集合中才会有
   * import scala.collection.mutable.Set
   */

  import scala.collection.mutable.Set

  def addAndDel(): Unit = {
    val s1 = Set(1, 2, 3, 4)
    s1.add(5) //添加指定元素，返回boolean
    println(s1)
    s1.remove(2) //删除指定元素，返回boolean
    println(s1)

    //将可变Set更改为不可变Set
    val s2 = s1.toSet
    println(s2.getClass.getName)
  }

  /**
   * ++ / .++  连接集合
   * head tail isEmpty  集合中第一个元素，除了第一个元素其他元素，判断是否为空
   * max min  集合中最大值，最小值
   * .& / intersect 两个集合的交集
   */
  def optionsSet(): Unit = {
    val s1 = Set(1, 2, 3, 4)
    val s2 = Set(5, 6, 7, 8)
    s1.toSet
    s2.toSet

    val s3 = s1 ++ s2
    val s4 = s1.++(s2)
    s3.foreach(item => print(item + ""))
    println()
    s4.foreach(item => print(item + ""))
    println()

    println("s3最大值：" + s3.max)
    println("s3最小值：" + s3.min)

    println("s3第一个元素：" + s3.head)
    println("s3倒数n-1个元素：" + s3.tail)
    println("s3是否为空：" + s3.isEmpty)

    /**
     * 注意s4是不可变的，要将s4.drop(3)赋予到s6之上。
     */
    val s6 = s4.drop(3) //因为s4已经是不可变的集合了，所以执行drop操作之后返回的是一个新的Set
    //如果不是val s6 = s4.drop(3)，而是直接s4.drop(3)的话，执行s3.&(s4)--1,2,3,4,5,6,7,8和执行s3.&(s6)--4,5,6,7,8是不一样的结果。
    val s5 = s3.&(s6)
    print("s3和去除前三项的s4的交集：")
    s5.foreach(item => print(item + " "))
  }
}
