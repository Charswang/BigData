/**
 * idea整理代码快捷键
 * ctrl + alt + l  -->  整理代码规范格式
 */
/**
 * 对各种形式函数进行创建，以及理解Scala闭包
 */
object TestFunction {
  def main(args: Array[String]): Unit = {
    println(addInt(1, 2))
    println("---------------------------------------------")
    helloScala()
    println("---------------------------------------------")
    println(m(2))
    println("---------------------------------------------")
    println(f(1))
    println("---------------------------------------------")
    println(f1(3))
    println("---------------------------------------------")
    printString("Scala", "Java", "Python")
    println("---------------------------------------------")
    printString2(0, "Scala", "Java", "Python")
    println("---------------------------------------------")
    //运行递归函数
    for (i <- 1 to 10) {
      println(i + "的阶乘为：" + factorial(i))
    }
    println("---------------------------------------------")
    //前n项之和
    println(OneToTen())
    //println(OneToTen(5))

    println("---------------------------------------------")
    println(apply(f2, 1))

    println("---------------------------------------------")
    println("使用闭包进行5*定义在函数外面的自由变量factor(10)=" + f3(5)) //5*10=50
  }

  //def 函数名称([参数名称:参数类型]):返回参数类型={参数主体 返回值}
  def addInt(a: Int, b: Int): Int = {
    var sum: Int = 0
    sum = a + b
    return sum
  }

  //如果方法没有返回值，可以返回为 Unit，这个类似于 Java 的 void
  def helloScala(): Unit = {
    println("Hello,Scala!")
  }

  def m(a: Int): Int = a + 3 //等号后面的相等于是返回的数值或对象

  //匿名函数
  val f = (a: Int) => a + 1
  var f1 = (a: Int) => a + 2

  //包含可变参数的函数方法
  def printString(args: String*): Unit = {
    var i: Int = 0

    for (arg <- args) {
      println("Arg value[" + i + "]=" + arg)
      i += 1
    }
  }

  def printString2(num: Int, args: String*): Unit = {
    for (arg <- args) {
      println("num = " + num + " StringValue = " + arg)
    }
  }

  //递归函数
  def factorial(i: Int): Int = {
    if (i <= 1) {
      return 1
    } else {
      return i * factorial(i - 1)
    }
  }

  //默认参数值
  def OneToTen(num: Int = 10): Int = {
    var sum: Int = 0
    for (i <- 1 to num) {
      sum += i
    }
    return sum
  }

  //函数可以作为参数进行传递 函数作为参数传递时的形式->[函数名称:参数类型=>返回类型]
  def apply(f: Int => String, v: Int): String = f2(v)

  def f2(v: Int): String = "[" + v.toString + "]"

  //闭包，函数使用到了定义在函数外面的自由变量
  var factor = 10
  var f3 = (i: Int) => i * factor
}
