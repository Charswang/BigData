/**
 * 了解Scala基本数据类型创建
 * 创建变量和创建常量的区别
 * 控制语句及循环语句
 */
object TestFirstScala {
  def main(args: Array[String]): Unit = {
    //输出语句print / println
    println("hello scala")

    //测试1-10求和
    /*var sum = 0
    for (a<-1 to 10){
      sum += a
      println("Value of a:" + a + "   Until sum:" + sum)
    }*/

    println("---------------------------------------------")

    //var声明变量，val声明常量

    //声明变量格式
    //规则1 声明变量必须同时赋值
    //规则2 当变量第一次赋值后,系统自动判断数据类型,当再次给变量赋值,必须是数据类型相同,否则编译不通过
    var myVar : String = "MyFirstScalaVariable"
    println(myVar)

    myVar = "ChangeVariable" //Right
    //myVar = 1   # Error  因为myVar是已被声明的变量，所以更改myVar时要与声明时的数据类型保持一致
    println("AfterChange：" + myVar)

    println("---------------------------------------------")

    //val声明常量是不可变的。
    val x, y = "hello"//虽然可以同时设置两个常量，但是这两个常量要赋予相同的值；若写成val x, y = 1, 2是会报错的。
    println("x = " + x + ";y = " + y)
    //x = "ss"  #Error  因为val声明的是常量，所以x，y均不可变

    println("---------------------------------------------")

    //元组
    val pa : (Int,String) = (10,"foo")
    println("pa：" + pa + "\t" +"pa的数据类型：" +  pa.getClass)

    // >> << >>>运算符
    var a = 60 // 0011 1100
    println(a>>2) //二进制向右移动两位，如果为负数的话第一位的1不动 0000 1111 2^4-1 = 15
    println(a<<2) //二进制向左移动两位，如果为负数的话第一位的1不动 1111 0000 2^4+2^5+2^6+2^7 = 16+32+64+128 = 48 + 192 = 240

    println("---------------------------------------------")

    // 流程语句 if...else
    //var x = 5  //这里的var x = 5貌似在for循环中的if语句不能识别出来
    for (i <-1 to 10){ //for循环中的i<-1 to 10表示i的范围包括[1,10]里面的10个数
      var x = 5  //只有在这里声明var x = 5才能在if语句中执行
      if (x<i){
        println("i = " + i + ", x = " + x)
      }else{
        println("i = " + i)
      }
    }

    println("---------------------------------------------")

    var b = 10
    if (b == 5){
      println("x的值为：" + 5)
    }else if (b == 8){
      println("x的值为：" + 8)
    }else{
      println("x的值为：" + b)
    }

    println("---------------------------------------------")

    //循环while; do...while; for

    //for循环  for (i <- 1 to/until 10)
    for (i <- 1 to 10){
      println("Value of i：" + i)
    }
    println("<-代表对i赋值，to表示包括尾数。")

    println("---------------------------------------------")

    for (i <- 1 until 10){
      println("Value of i：" + i)
    }
    println("<-表示对i进行赋值，until表示不包括尾数。")

    println("---------------------------------------------")

    // while
    var i_while = 0
    while (i_while < 5){
      println("Value of i_while：" + i_while)
      i_while += 1
    }

    println("---------------------------------------------")

    //do...while
    var i_dowhile = 0
    do{
      println("Value of i_dowhile：" + i_dowhile)
      i_dowhile += 1
    }while(i_dowhile < 5)

    println("---------------------------------------------")
  }
}
