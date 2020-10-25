import javafx.concurrent.Worker

import scala.io.StdIn

/**
 * scala中没有java中的switch case语句。只能进行值的匹配
 * 相对应使用match case语句，即模式匹配。可以匹配各种情况。
 */
object TestMatch {
  def main(args: Array[String]): Unit = {
//    switchOps()
//    yeildOps()
//    typeOps()
//    matchCollection()

    //接收主函数中的参数args
    val Array(f1,f2,f3) = args
    println("f1:" + f1)
    println("f2:" + f2)
    println("f3:" + f3)
  }

  //匹配集合
  def matchCollection(): Unit ={
    println("---------------匹配字符串-------------")
    val str = "Hello Scala"
    for (ch <- str){
      ch match{
        case ' ' => print(",")
        case _ => print(ch)
      }
    }
    println()
    println("---------------匹配数组-------------")
    val array = Array(1,2,3)
    array match {
      // 当array数组只有两个元素时，匹配该数组，并将两个元素进行输出
      case Array(x,y) => println(s"x=${x},y=${y}")
      // 当array这一个数组第一个元素为1时，匹配该数组
      case Array(1,_*) => println("数组首元素是1时，匹配该数组")
      case _ => println("Others")
    }
  }

  //匹配类型
  def typeOps(): Unit ={
    class Person(name:String,age:Int){

    }
    class Worker(name:String,age:Int) extends Person(name,age){
      def work(): Unit ={
        println(s"工人：${name},年龄为${age}")
      }
    }
    class Student(name:String,age:Int) extends Person(name,age){
      def student(): Unit ={
        println(s"学生：${name},年龄为：${age}")
      }
    }
    def matchObject(person:Person): Unit ={
      person match{
        case worker:Worker => worker.work()
        case stu:Student => stu.student()
        case _ => println("无匹配选项!")
      }
    }
    matchObject(new Worker("wang",21))
  }


  //模式匹配的基本操作
  def yeildOps(): Unit ={
    println("请从控制台输入一个字符")
    val ch:Char = StdIn.readChar()
    // match是有返回值的。并且match case中case 要使用单引号，不能使用双引号。
    var sign = ch match{
      case '+' => 1
      case '-' => -1
      case '*' => 2
      case '/' => -2
      case _ => 0
    }
    println("yeild_sign= " + sign)
  }
  //模仿java中的switch case
  def switchOps(): Unit = {
    println("请从控制台输入一个字符：")
    val ch: Char = StdIn.readChar() //从控制台输入
    var sign: Int = 0
    ch match{
      case '+' => sign = 1
      case '-' => sign = -1
      case '*' => sign = 2
      case '/' => sign = -2
      case _ => sign = 0
    }
    println("sign= " + sign)
  }
}
