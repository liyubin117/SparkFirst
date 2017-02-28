package org.scala

//假如Arithmetic是已有的类库中的类，仅仅提供add函数
class Arithmetic {
  def add(x: Int, y: Int): Int = {
    x + y
  }
}

//=========================================
//下面是增强Arithmetic类，添加一个subtract方法
//=========================================

class RichArithmetic(val arithmetic: Arithmetic) {
  def substract(x: Int, y: Int) = {
    x - y
  }
}

//转换的逻辑，隐式方法
object ArithmeticToRichArithmetic {
  //将Arithmetic转换为RichArithmetic
  //Arithmetic对象上可以直接调用RichArithmetic定义的方法
  implicit def arithmeticToRichArithmetic(arithmetic: Arithmetic):RichArithmetic = {
    new RichArithmetic(arithmetic)
  }
}

object ImplicitDemo {
  def main(args: Array[String]) {
    import ArithmeticToRichArithmetic._ //引用方法
    val a = new Arithmetic
    println(a.add(11, 21))
    println(a.substract(21,11)) //substract不属于Arithmetic
  }


}
