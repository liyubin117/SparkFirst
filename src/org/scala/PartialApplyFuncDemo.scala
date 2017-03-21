package org.scala

//偏应用函数
object PartialApplyFuncTest {

  def calc(a: Int, b: Int, c: Int) = a + b - c

  def main(args: Array[String]) {
    val list = List(1, 2, 3, 4, 5)
    list.foreach(println _) //缺失所有参数(因为println函数只有一个参数，因此println _所有参数等价于println(_)缺失一个参数)
    list.foreach(println(_)) //缺失一个参数(println实际上就一个参数)
    // list.foreach(println _)等价于list.foreach(x => println x)
    // list.foreach(println(_)),是否等价于list.foreach(println _)？等价

    val print = println(_: Int) //声明时，需要为缺失的参数指定类型，上面不需要是因为可以从list中推导出来
    list.foreach(print)

    //如下通过_定义的部分应用函数，必须为_指定类型
    //val s0 = calc //编译错，参数个数缺失或者根本不存在无参的calc函数
    val s00 = calc(1,2,4) //参数足够，直接调用
    val s1 = calc(_: Int, 2, 3) //缺失第一个参数
    val s2 = calc(_: Int, _: Int, 3) //缺失第一个，第二个参数
    val s3 = calc(_: Int, 2, _: Int) //缺失第一个，第二个参数
    val s4 = calc(_: Int, _: Int, _: Int) //缺失第一个，第二个和第三个参数
    val s5 = calc _ //所有的参数列表都缺失(缺失第一个，第二个和第三个参数)
    println(s1(10))
    println(s2(20, 30))
    println(s3(10, 20))
    println(s4(3, 2, 1))
    println(s5(1, 3, 5))

    //apply语法，s5(1,3,5)等价于s5.apply(1,3,5),apply方法将参数列表发送给s5指向的函数，进行调用

    val f = (_: Int) + (_: Int)
    println(f(1, 2))
  }
}
