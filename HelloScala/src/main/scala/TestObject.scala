import scala.annotation.meta.beanGetter
import scala.beans.BeanProperty

object TestObject {
  def main(args: Array[String]): Unit = {
    /*var pot = new Point(10, 20)
    println("原x坐标为：" + pot.getPointX())
    println("原y坐标为：" + pot.getPointY())
    pot.move(5, 5) // 调用对象中的函数*/

    /*var person = new Person
    person.setAge(21)
    person.setName("wang")
    println(s"My name is ${person.getName},I am ${person.getAge} years old")*/

    /*var student = new Student
    student.setAge(21)
    student.setName("hao")
    student.show()*/

    /*var teacher = new Teacher //无参构造器
    teacher.show()
    println("---------------------------------------------")
    var teacher1 = new Teacher("wanghao", 21) //全参构造器
    teacher1.show()*/

    //创建scala内部类对象
    /*val outer: Outer = new Outer
    val inner = new outer.Inner
    //val inner: outer.Inner = new outer.Inner
    inner.show()*/

    /*val s1 = Singleton.getInstance()
    s1.x = 5
    val s2 = Singleton.getInstance()
    println(s1.x)
    println(s2.x)
    println(s1.x == s2.x)
    println(s1 == s2)*/

    //伴生对象与伴生类
    /*var c1 = new CompanionClass() //Class CompanionClass()
    c1.show()
    println("--------------------------------")
    var c2 = CompanionClass() // Object CompanionClass
    //c2.show()  # Error  伴生对象没有伴生类的方法
    println("--------------------------------")
    var c3 = CompanionClass("wang", 21) // Object CompanionClass*/

    //类的继承
    /*val stu = new Student1("wang",21,"male")
    stu.show()
    println("------------------------------------")
    val stu1 : Person1 = new Student1("hao",21,"male")
    stu1.show()*/

    //重写equals方法，判断对象是否相等
    /*val worker1 = new Worker("wang",21)
    val worker2 = new Worker("wang",21)
    println(worker1.equals(worker2))  //true*/

    //构造子类，父类。主构造器和辅助构造器的调用过程
    /*val zi:Fu = new Zi("wang",21)*/
    /*----------Fu-primary-constructor-----------
      ----------Fu-this(name:String)--constructor
    ----------Zi-primary-constructor-----------
      ---------Zi--this(name,age)-constructor-----------*/

    //使用非匿名子类
    /*println("--------------------使用非匿名子类----------------")
    def showName(annominy: Annominy): Unit ={
      annominy.show()
    }
    showName(new NameAnnominy)*/

    //使用一个匿名子类
    /*println("--------------------使用一个匿名子类----------------")
    //父类引用指向子类对象时，编译看左边运行看右边
    //val annominy:Annominy = new Annominy(){}  这样写的话，annominy没有办法引用除了父类Annominy之外的方法。
    //val annominy = new Annominy(){}  这样就可以引用myPrint方法了
    val annominy = new Annominy(){
      override def show(): Unit = {
        super.show()
        println("这是一个匿名子类")
      }
      def myPrint(): Unit ={
        println("Peace and love")
      }
    }
    annominy.show()
    annominy.myPrint()
    showName(annominy)*/

    //使用局部匿名内部类
    /*println("---------------------使用局部匿名内部类---------------")
    showName(new Annominy(){
      override def show(): Unit = {
        super.show()
        println("使用了局部匿名内部类")
      }
    })*/

    //抽象方法 & 抽象类
    //利用多态,父类引用指向子类对象
    /*val dog:Animal = new dog()
    println("小狗的颜色：" + dog.color)
    dog.eat()
    dog.sleep()*/

    //抽象字段
    /*val abstractZi = new AbstractZi
    println(abstractZi.name)
    println(abstractZi.age)*/

    //trait特质
    val traitZi = new TraitZi with Mix
    traitZi.method()
    traitZi.log("information")
    traitZi.show()
    //trait -- 混入
    traitZi.mix()  //因为混入，所以traitZi就有了trait Mix的 mix 这一个方法

  }
}

class Point(xc: Int, yc: Int) {
  var x: Int = xc
  var y: Int = yc

  def getPointX(): Int = {
    return x
  }

  def getPointY(): Int = {
    return y
  }

  def move(dx: Int, dy: Int): Unit = {
    x = x + dx
    y = y + dy
    printf("x向右移动%d,y向右移动%d之后：坐标为(%d,%d)", dx, dy, x, y)
  }
}

/**
 * 构造getter & setter
 */
//getter & setter
class Person {
  //使用_代表默认值的时候，前面必须要指定变量类型
  private var name: String = _ //_代表默认值为null
  private var age: Int = _ //_代表默认值为0

  //getter & setter
  def getAge = age

  def setAge(age: Int): Unit = {
    if (age < 1 || age >= 150) {
      throw new RuntimeException("年龄范围不符合条件!")
    }
    this.age = age
  }

  def getName = name

  def setName(name: String): Unit = {
    this.name = name
  }
}

/**
 * 使用@BeanProperty注解自动生成getter & setter
 */
//若类中的属性不是private修饰的，则可以使用@BeanProperty这一注解自动生成getter和setter
class Student {
  //使用@BeanProperty注解的前提是，属性是非private的
  //但是一般不会这样使用
  @BeanProperty var name: String = _
  @BeanProperty var age: Int = _

  def show(): Unit = {
    println(s"My name is ${name},I am ${age} years old")
  }
}


//构造器  Constructor
/**
 * 构造器定义：
 * 1、与java不同，Scala中和类名相同的方法不是构造器，只是一个单纯的方法
 * 2、怎样构建多个构造器，无参？一个参数？多个参数？----使用this关键字来创建多个构造器
 * 3、归纳：
 * 1* Scala中构造器有两种，一种在类名后面定义的，一种在类中使用this关键字来定义
 * 第一种构造器，称为scala类的主构造器
 * 第二种构造器，称为scala类的辅助构造器
 * 2* scala中的辅助构造器的第一句话，必须要调用本类的主构造器或者其他辅助构造器--[this()]
 */
class Teacher /*private这里加上private代表主构造器私有化，但是下面的辅助构造器仍能使用*/ (/*这是一个无参构造器*/) {
  private var name: String = _
  private var age: Int = _

  println("若这是一个构造器，则会被调用！") //构建一个类的时候，会调用这一句话。

  //这只是一个方法，，并不是类的构造器
  def Teacher(): Unit = {
    println("若这是一个构造器，则会被调用！")
  }

  //这是一个有参数构建器
  def this(name: String, age: Int) {
    this() //这一句话必不可少，这算是调用了另一个无参数构造器，就是刚开始写的class Teacher()
    this.name = name
    this.age = age
    println("若这是一个有参数构造器，则输出这一句话！")
  }

  def show(): Unit = {
    println(s"My name is ${name},I am ${age} years old")
  }
}


//内部类
/**
 * scala创建内部类以及使用方法
 */
class Outer() {
  o => //此时的o代表的就是本类的引用
  var x: Int = 5

  class Inner() {
    i => //这里的i代表的就是本类的引用
    var x: Int = 6

    def show(): Unit = {
      var x: Int = 7
      println("就近原则x = " + x)
      println("Inner类的x = " + this.x) //或者写Inner.this.x
      println("Inner类的i.x = " + i.x)
      println("Outer类的x = " + Outer.this.x)
      println("Outer类的o.x = " + o.x)
    }
  }

}

//Object对象 可以拥有静态方法
/**
 * 在 java一个类中既可以拥有static静态，也可以拥有非static，同时java中的主函数必须是static
 * 但是scala中只能提供非static成员
 * 所以为了给scala中的类也来提供类似于jav中的静态成员的功能，引入Object对象
 *
 * scala的object中定义的成员类似于java中的静态成员，无需创建对象实例，可以直接通过类名进行调用。
 *
 * 1、为scala类提供程序的运行入口，静态的main函数。--就像自己刚开始创建scala程序文件时，创建scala class文件和创建scala object文件的区别
 * 前者没有办法直接run 后者可以直接run
 * 2、给scala类能够提供静态成员----通过scala类的伴生对象来实现
 */

//单例
/**
 * 单例：本类只能创建一个对象
 * 私有化构造对象
 * 1、饿汉式 2、懒汉式
 * 利用到了伴生对象*
 */
//将对象设置成为私有的，是避免外面随意创建该对象，因为单例对象就只能允许被创建一个。
class Singleton private() {
  var x = 1
}

//饿汉式
/*object Singleton{
  private var instance:Singleton = new Singleton()
  def getInstance(): Singleton ={
    return instance
  }
}*/
//懒汉式
object Singleton {
  private var instance: Singleton = _

  def getInstance(): Singleton = {
    if (instance == null) {
      instance = new Singleton //可以不加()
    }
    instance //这样等同于return instance
  }
}

//伴生对象Companion class和object类名称一样
/**
 * scala中把同一个源文件中相同名称的object结构为class结构的伴生对象，class结构作为object的伴生类
 * 伴生类和伴生对象必须在同一个.class文件中，名称必须相同
 * 特点：
 * 1、伴生对象内部中可以访问伴生类的私有和非私有成员
 * 2、通常我们需要在伴生对象中覆盖一个方法----apply，用于构造伴生类的对象     Array，ArrayBuffer，Map等都是通过伴生对象创建的
 * apply方法特点：返回值类型是本类引用，参数列表对应伴生类的构造器的参数列表
 *
 * 3、有了伴生对象，同时覆盖了apply方法，便给伴生类提供了一个简化的的对象构造器方式，即可以省略掉new关键字
 */
class CompanionClass() {
  private var x = 5

  def this(name: String, age: Int) {
    this()
    println("name:" + name)
    println("age：" + age)
  }

  def show(): Unit = {
    println("this is a show function")
  }
}

object CompanionClass {
  def apply(): CompanionClass = {
    var c1 = new CompanionClass()
    println("无参的伴生对象" + c1.x)
    c1
  }

  def apply(name: String, age: Int): CompanionClass = {
    var c1 = new CompanionClass(name, age)
    println("有参数的伴生对象：" + name + " " + age)
    c1
  }
}

//类的继承
/**
 * scala中类的继承/扩展
 * 1、使用关键字extends，子类可以继承父类中所有非私有成员
 * 2、如果父类被final覆盖，或者父类的成员(field和method)被final修饰，该父类也是没有办法被继承的
 * 3、scala中子类覆盖父类的方法时，必须要添加关键字override进行修饰，以标识要对父类方法进行覆盖，
 * 否则认为子类重新定义一个同名方法，这就会造成方法同名，会报错.除非该方法时抽象的。
 * 4、子类要想访问父类的成员的时候，就需要使用super关键字来完成
 */
class Person1 {
  var name: String = _
  var age: Int = 20

  def this(name: String, age: Int) {
    this()
    this.name = name
    this.age = age
  }

  def setName(name: String): Unit = {
    this.name = name
  }

  def setAge(age: Int): Unit = {
    this.age = age
  }

  def getName(): String = {
    this.name
  }

  def getAge(): Int = {
    this.age
  }

  def show(): Unit = {
    println(s"Person1 --- My name is ${this.name},I am ${this.age} years old")
  }
}

class Student1 extends Person1 {
  var gender : String = _
  def this(name: String,age:Int,gender:String) {
    this()
    /*super.name = name //对name进行赋值。***
    super.age = age*/ //Error
    /*this.name = name
    this.age = age*/  // Right  但是前提是父类的成员属性是非私有的才可以

    // 一般来说，这样是最保险的
    super.setName(name)
    super.setAge(21)
    this.gender = gender
  }

  //重写父类方法  使用override关键字
  override def show(): Unit = {
    super.show()
    println("Student1 --- this is student1")
  }
}

//类的类型检查和转换
/**
 *判断两个对象是否相等----java中重写equals方法（通过判断name和age来判断两个对象是否相同）
 *scala中的类型检查和转换
 * 1、传统的重写equals方法：类型检查：obj.isInstanceof[对象类型]；类型转换：obj.asInstanceof[对象类型]
 * 2、使用scala中的模式匹配来完成类进行检查和转换
 * 注意，在写匹配模式的时候，一定要把默认的选项加上[case _ =>{}]，避免所有的可能性都没有匹配成功，若不加会报错：scala.MatchError
 */
class Worker {
  private var name :String = _
  private var age : Int = _

  def this(name:String,age:Int){
    this()
    this.name = name
    this.age = age
  }

  def getName(): String ={
    this.name
  }
  def getAge(): Int ={
    this.age
  }
  def setName(name:String): Unit ={
    this.name = name
  }
  def setAge(age:Int): Unit ={
    this.age = age
  }

  //1、传统的重写equals方法
  /*override def equals(obj: Any): Boolean = {
    if (obj == null){
      false
    }else if(!obj.isInstanceOf[Worker]) {
      false
    }else{
      var worker:Worker = obj.asInstanceOf[Worker] // 类似java中的强制转换
      this.name.eq(worker.getName()) && this.age == worker.getAge() //返回
    }
  }*/

  //2、scala中的利用模式匹配写法
  override def equals(obj: Any): Boolean = {
    obj match {
      case worker: Worker =>{
        return this.getName().eq(worker.getName()) && this.getAge() == worker.getAge()
      }
      case _ => {
        println("模式不匹配不能进行比较")
        return false
      }
    }
  }
}

//protected修饰的受保护的成员
/**
 *scala中被protected修饰的成员特点：
 * scala在使用protected和private作访问权限修饰时，除了传统的意义外，还可以做到非常精准的控制某一个成员在某个package下面被访问
 * 例如：在proteced/privete[具体的包名]  --> 该成员只能在该包及其子包下面被访问
 * 其中一个特例：就是protected[this] --> 表示该成员只能在本类及其子类中使用，不能被子类的对象实例来进行访问；
 * 就像一个Animal对象有个prtected[this] var age:Int=2  ,那么Animal子类Dog中使用this.age没问题，但是Dog创建一个实例dog,dog.age就会报错
 */

//超类的构造过程
/**
 * scala超类的构造过程
 * 1、scala中在继承体系中，构造子类的时候，先构造父类
 * 2、子类在构造器中要想传递参数到父类的构造器中，只能通过主构造器。因为辅助构造器的第一句话，只能调用本类的主构造器，或者其他辅助构造器。
 */
class Fu(name:String,age:Int){
  println("----------Fu-primary-constructor-----------")
  def this(name:String){
    this(name,21)
    println("----------Fu-this(name:String)--constructor")
  }
}
class Zi(name:String) extends Fu(name:String){
  println("----------Zi-primary-constructor-----------")
  var age :Int = _
  def this(name:String,age:Int){
    this(name)
    this.age = age
    println("---------Zi--this(name,age)-constructor-----------")
  }
}

//匿名内部类
/**
 * scala中匿名内部类/匿名子类：定义一个类的没有名称的子类。通常出现在继承体系中，对于接口，抽象类只被调用一次的情况，同时父类中的(抽象)方法相对较少
 * val xxx = new XXX(){可以重写方法}   --->   匿名子类
 * 常见作用：作为方法的参数出现
 */
class Annominy{
  val name:String = "父类方法"
  def show(): Unit ={
    println(name)
  }
}
//非匿名子类
class NameAnnominy extends Annominy{
  override def show(): Unit = {
    println("这是一个非匿名子类！")
  }
}

//抽象类
/**
 *scala中的抽象类
 * scala中一个类中的方法或字段，只做了声明，没有进行实现或初始化，我们就将这种类叫做抽象类。需要使用abstract关键字
 * 和java中一样，既可以有抽象成员也可以有非抽象成员.
 * 但抽象成员必须是在抽象类中。
 * 抽象类的子类必须重写抽象方法/成员
 * scala中的抽象成员可以省略掉abstract关键字。
 * scala类中的子类重写父类的抽象成员时，可以省略掉override关键字
 */
abstract class Animal{
  var color:String = _
  //抽象方法的重写可以省略override关键字
  /*abstract*/ def eat()
  def sleep(): Unit ={
    println("睡觉姿势")
  }
}
class dog extends Animal{
  color = "yellow"
  /*override */def eat(): Unit = {
    println("小狗爱吃骨头")
  }

  override def sleep(): Unit = {
    println("小狗趴着睡")
  }
}

//抽象字段
/**
 * scala中的抽象字段：只有字段的声明，没有初始化的字段
 */
abstract class AbstractFu{
  //定义抽象字段(var/val)
  /*abstract */var name:String
  /*abstract */val age:Int
}
class AbstractZi extends AbstractFu{
  /*override */var name: String = "wang"  //这里的var要有的
  /*override */val age: Int = 21
}

//trait -- 特质   在一定程度上类似java中的接口(interface，所有方法都是抽象的)
/**
 * trait是在scala中一种和class、object同一级别的语法结构，需要被trait关键字声明
 * scala中是可以进行多实现的，同时多实现的方法不一定都是抽象的
 * scala中把类似java中的多实现接口，不叫作多实现，而叫做trait的多扩展
 * 如果一个trair的所有方法都是抽象的，那么scala中的trait在一定程度上可以看作java的接口
 * trait的多扩展时，使用的时with，而不像java中多实现的时候使用","
 * 如果既继承一个类还要扩展一个trait，那么继承类优先，然后在with特质
 * scala中无论是继承类还是trait都使用extends关键字，scala中没有implement的概念
 *
 * trait--混入
 * 假如说之前的程序员走了，新来了一个程序员，还不太了解代码，为了避免代码出错和污染，就不能直接将mix这个trait直接在声明TraitZi类的时候直接with上去
 * 所以要采用混入的方式。
 * 在运行时，让一个类具备另一个类的特征，把这种操作称之为scala的混入，此时的影响只是在局部
 * 需要在创建类对象的时候，使用with关键字连接需要具备的类即可
 */
class TraitFu{
  def method(): Unit ={
    println("----TraitFu method----")
  }
}
trait Mix{
  def mix(): Unit ={
    println("trait-->mix 混入")
  }
}
trait Log{
  //抽象方法
  def log(msg:String)  //这里就不能添加abstract关键字了。abstract关键字是用在抽象类及其中的抽象成员的。
  //非抽象方法
  def show(): Unit ={
    println("仅仅是证明trait与java中的interface的不同之处，可以有非抽象方法")
  }
}
class TraitZi extends TraitFu with Log{
  /*override */def log(msg: String): Unit = {
    println("TraitZi：" + msg)
  }
}