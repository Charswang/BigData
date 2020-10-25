/**
 * 列表 List  元素类型相同，不可变
 * 集合 Set
 * 映射 Map
 * 元组 tuple
 * 迭代器 Iterator
 */

object TestList {
  def main(args: Array[String]): Unit = {
    //List
    val l1 = createList()
    println(l1)
    val l2 = createTwoList()
    println(l2)
    /**
     * 不太理解这样形式的创建List
     */
    var l3 = "tom" :: ("john" :: ("lisa" :: Nil))
    println(l3)

    var l4 = Nil //空列表
    println(l4)

    println("---------------------------------------------")

    optionList()

    println("---------------------------------------------")

    concatList()

    println("---------------------------------------------")

    println(createListByfill())

    println("---------------------------------------------")

    println(createListByFun())

    println("---------------------------------------------")

    delAndAdd()
  }

  /**
   * 创建列表List
   *
   * @return
   */
  def createList(): List[String] = {
    //创建普通列表
    var l1 = List("tom", "lisa", "john")
    //创建空列表
    l1 = List()
    return l1
  }

  def createTwoList(): List[List[Int]] = {
    //创建二维列表
    var l1 = List(List(1, 2, 3), List(4, 5, 6), List(7, 8, 9))
    return l1
  }

  /**
   * 列表操作----首项，除第一项其他尾项，判断列表是否为空
   */
  def optionList(): Unit = {
    var l1 = ("tom" :: ("john" :: ("lisa" :: Nil)))
    var l2 = Nil
    var l3 = List("tom", "john", "lisa")

    //l1.head直接显示第一项,l1.head(2)显示第一项所属字符串索引为2的字符
    println("l1列表首项：(l1.head)：" + l1.head)
    println("l1列表除第一项其他尾项：(l1.tail)：" + l1.tail)

    println("l3列表首项：(l3.head)：" + l3.head)
    println("l3列表除第一项其他尾项：(l3.tail)：" + l3.tail)

    println("判断l2列表是否为空：(l1.isEmpty)：" + l2.isEmpty)
  }

  /**
   * 连接列表 ---- l1:::l2 , l1.:::(l2) , List.concat(l1,l2)
   * l1:::l2  List.concat(l1,l2)  最终列表都是前面是l1后面才是l2
   * l1.:::l2 最终列表前面是l2,后面是l1
   */
  def concatList(): Unit = {
    var l1 = ("tom" :: ("lisa" :: ("john" :: Nil)))
    var l2 = ("zhangsan" :: ("lisi" :: Nil))
    println("三种形式连接两个列表  [l1:::l2 , l1.:::(l2) , List.concat(l1,l2)]")
    println(l1 ::: l2)
    println(l1.:::(l2))
    println(List.concat(l1, l2))
  }

  /**
   * 指定重复数量的元素创建List
   */
  def createListByfill(): List[String] = {
    val l1 = List.fill(3)("kk")
    return l1
  }

  /**
   * 利用函数创建List
   */
  def createListByFun(): List[List[Int]] = {
    //val l1 = List.tabulate(3)(item => item * 2)
    val l1 = List.tabulate(3, 3)((item, item2) => (item * item2))
    return l1
  }

  /**
   * 删除添加
   */
  def delAndAdd():Unit={
    var l1 = "tom" :: ("lisa" :: ("john" :: Nil))
    println(l1 :+ "hao") //在后面添加元素
    println(l1.drop(3)) //丢弃前3个元素
    println(l1.dropRight(1)) //丢弃掉后1个元素
    println(l1.contains("tom")) //判断是否包含元素
    println(l1.apply(2))  //获取指定位置元素
    println(l1.exists(s=>s.endsWith("m"))) //判断是否存在以m结尾的元素
  }
}
