/**
 * String对象不可变
 * StringBuilder对象可以进行修改
 */

object TestString {
  def main(args: Array[String]): Unit = {
    println("输出一个String：" + printString())
    println("---------------------------------------------")
    println("创建一个String" + createString())
    println("---------------------------------------------")
    println("创建一个StringBuilder：" + CreateStringBuilder())
    println("---------------------------------------------")
    var testStr = "hello,scala!"
    println("hello,scala!的长度为：" + getStringLengh(testStr))
    println("---------------------------------------------")
    println(concatString("concatString"))
    println("---------------------------------------------")
    formatString("Wang",21) //格式化字符串时，要使用printf才可以

    /**
     * 其他方法，慢慢接触练习。
     */
  }

  //def printString(): String = "Hello,Scala!"
  def printString() = "Hello,Scala!"

  def createString(): String = {
    var str: String = "The Create String"
    return str
  }

  //创建StringBuilder对象之后，不能过直接赋值[若赋值，则就可以把该变量成为String类型了]
  //需要通过+= / ++=来进行更改，其中+=只能添加''字符 ， ++=只能添加""字符串
  def CreateStringBuilder(): StringBuilder = {
    var str = new StringBuilder()
    str += 'z'
    str ++= "zzz"
    return str
  }

  //字符串长度
  def getStringLengh(str: String): Int = str.length() //str.length亦可

  //字符串拼接 -> concat/直接使用加号均可
  //def concatString(str: String): String = str.concat("zzz")
  def concatString(str: String): String = str + "zzzz"

  //格式化字符串
  def formatString(str: String, age: Int): Unit = printf("My name is %s.I am %d years old!", str, age)
}
