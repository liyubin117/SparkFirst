package org.scala

//仅仅在方法上指定泛型参数
class GenericMethod {
  def print[T](content: T) {
    println(content)
  }
}

object GenericMethod {
  def main(args: Array[String]) {
    val gm = new GenericMethod
    gm.print(100) //不需要指定T的类型，由100自动推断
    gm.print[String]("string")
  }
}
