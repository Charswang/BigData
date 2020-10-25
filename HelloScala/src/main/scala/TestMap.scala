/**
 * Map -- 键值对  key->value
 * 键唯一
 * 分不可变Map和可变Map
 */
object TestMap {
  def main(args: Array[String]): Unit = {
    val m1 = createMap()
    m1.foreach(item => print(item + " "))

    println("------------------------------------------")

    optionMap()

    println("------------------------------------------")

    mergeMap()
  }

  /**
   * 创建Map  --  一种直接创建空的HashMap 【var m1 : Map[String,Int] = Map()】
   * 一种直接赋值  [val m2 = Map("red"->1,"blue"->2)]
   *
   * @return
   */
  def createMap(): Map[String, Int] = {
    var m1: Map[String, Int] = Map()
    m1 += ("wang" -> 1) //添加键值对
    val m2 = Map("red" -> 1, "blue" -> 2, "yellow" -> 3)
    return m2
  }

  /**
   * 返回Map的keys，values以及判断是否为空
   * 利用Map.keys迭代显示Map.values
   */
  def optionMap(): Unit = {
    var m1 = Map("red" -> 1, "blue" -> 2)
    m1 += ("yellow" -> 3)

    println("m1的keys为：" + m1.keys) //Set(red, blue, yellow)
    println("m1的values为：" + m1.values) //Iterable(1, 2, 3)
    println("判断m1是否为空：" + m1.isEmpty) //false

    //迭代显示
    m1.keys.foreach(item => print("(" + item + "," + m1(item) + ") "))
  }

  /**
   * Map合并  -- ++ / .++
   * contains方法判断Map对象是否存在某个key
   */
  def mergeMap(): Unit = {
    var m1: Map[String, Int] = Map()
    m1 += ("red" -> 1)
    m1 += ("blue" -> 2)

    var m2 = Map("yellow" -> 3)

    var m3 = m1 ++ m2
    print("m1：")
    m1.keys.foreach(item => print("(" + item + "," + m1(item) + ") "))

    print("\nm2：")
    m2.keys.foreach(item => print("(" + item + "," + m2(item) + ") "))

    print("\nm1,m2合并得到的m3：")
    m3.keys.foreach(item => print("(" + item + "," + m3(item) + ") "))
    println()

    println("利用m1.contains('red')来判断m1中是否包含red这一个键：" + m1.contains("red"))
  }
}
