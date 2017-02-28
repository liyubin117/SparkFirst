package org.scala

class B {
  def add(x: Int, y: Int) = {
    x + y
  }
}

object ImplicitClassContext {

  //隐式类，可以为一个类型(参数的类型)提供方法
  //为主构造函数指定的类型进行增强
  implicit class RichB(arg: B) {

    def multiply(x: Int, y: Int) = {
      x * y
    }

  }

}

object ImplicitClass {
  def main(args: Array[String]) {
    import ImplicitClassContext._
    val b = new B();
    println(b.multiply(3, 4))
  }
}
