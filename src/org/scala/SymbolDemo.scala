package org.scala

//关于Scala符号的例子
object SymbolDemo extends App {
  //  =>
  //  code: => Unit传名参数。参数是一个返回值为Unit的代码块。在传递的时候，参数没有被调用
  def test(code: => Unit) {
    println("start")
    code // 这行才会调用传入的代码块，写成code()亦可
    println("end")
  }
  test { // 此处的代码块不会马上被调用
    println("when evaluated")
    println("bb")
  }
  println("--------")
  //  code: ()=>Unit零参数函数，是一个没有参数而且返回值为Unit的函数类型
  def test1(code: () => Unit) {
    println("start")
    code() // 要想调用传入的代码块，必须写成code()，否则不会调用。
    println("end")
  }
  test1 { //此代码块，传入后立即执行。
    println("when evaluated")
    () => { println("bb") }
  }

}