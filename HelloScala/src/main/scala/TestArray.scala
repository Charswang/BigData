/**
 * 创建数组的两种方法
 * 利用Array.ofDim(shape[0],shape[1])创建多维数组
 * 利用Array.concat(arr1,arr2)合并数组
 * 利用Array.range(start,end,step)创建区间数组
 * 数组实例的一些方法[reverse,map,filter...]
 * foreach遍历使用
 *
 * 注意Scala中的”_“符号的使用
 */

object TestArray {
  def main(args: Array[String]): Unit = {
    var arr = firstArray()
    //println(arr) //输出arr的地址
    println("声明并创建一个长度为3的数组：[声明]var arr = new Array[String](3)")
    for (i <- arr) {
      println(i)
    }

    println("---------------------------------------------")

    //直接对数组进行赋值
    println("直接创建对数组进行赋值：var arr = Array(1,2,..)")
    //var arr1 = Array(1,2,3,4,5)
    var arr1 = Array((1, 2), (3, 4), (5, 6))
    println("arr1.Class:" + arr1.getClass)
    for (i <- arr1) {
      println(i)
    }

    println("---------------------------------------------")

    //创建多维数组 var arr = Array.ofDim(m,n)
    println("创建多维数组并迭代：Array.ofDim(shape1,shape2)")
    var arr2 = Array.ofDim[Int](3, 2)
    for (i <- 0 until arr2.length) {
      for (j <- 0 until arr2(0).length) {
        arr2(i)(j) = j
      }
    }
    for (i <- 0 until arr2.length) {
      for (j <- 0 until arr2(0).length) {
        print(arr2(i)(j) + " ")
      }
      println()
    }

    println("---------------------------------------------")

    println("合并数组：Array.concat(arr1,arr2)")
    var arr3 = concatArray()
    for (i <- arr3) {
      println(i)
    }

    println("---------------------------------------------")

    println("创建区间数组：Array.range(start,end,step),包首不包尾")
    var arr4 = rangeArray()
    for (i <- arr4) {
      println(i)
    }

    println("---------------------------------------------")

    //一些数组实例的方法
    functionsArray()
  }

  //声明数组
  def firstArray(): Array[String] = {
    var arr = new Array[String](3)
    arr(0) = "tom"
    arr(1) = "lili"
    arr(2) = "lucy"
    return arr
  }

  //合并数组
  def concatArray(): Array[String] = {
    var arr1 = Array("a", "b", "c")
    var arr2 = Array("d", "e")
    var arr3 = Array.concat(arr1, arr2)
    return arr3
  }

  //创建区间数组
  def rangeArray(): Array[Int] = {
    var arr1 = Array.range(1, 10, 2)
    var arr2 = Array.range(10, 15)
    var arr3 = Array.concat(arr1, arr2)

    return arr3
  }

  //数组的一些方法
  def functionsArray(): Unit = {
    var arr = Array(1, 2, 3, 4, 5)
    println("原数组")
    for (i <- 0 until arr.length) {
      print(arr(i) + " ")
    }

    //数组反转
    println("\n数组反转:arr.reverse")
    arr = arr.reverse
    /*for (i<- 0 until arr.length){
      print(arr(i) + " ")
    }*/
    /**
     * 习惯使用类似语句arr.foreach(item => print(item))
     * 其中item代表arr中的元素，=>后面的语句代表要执行的操作/要返回的值
     */
    arr.foreach(item => print(item + " "))

    println("---------------------------------------------")

    /**
     * map  两种形式 1、arr.map(item => item * 2)  2、arr.map(_ * 2)
     */
    println("map方法,使arr数组元素*2")
    arr = arr.map(item => item * 2)
    //arr = arr.map(_ * 2)
    arr.foreach(item => print(item + " "))

    println("---------------------------------------------")

    /**
     * filter  两种形式1、arr.filter(item => item > 5)  2、arr.filter(_ > 5)
     */
    println("filter方法，过滤元素")
    //arr = arr.filter(item => item > 5)
    arr = arr.filter(_ > 5)
    arr.foreach(item => print(item + " "))
  }
}
