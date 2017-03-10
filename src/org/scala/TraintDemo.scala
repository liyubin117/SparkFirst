package org.scala

object TraintDemo {
  def main(args: Array[String]) {
    trait TraintA { def funA = println("TraintA") }
    trait TraintB { def funB = println("TraintB") }
    //子类可覆盖父类或特质中不带参数的def
    class AB extends TraintA with TraintB { def funAB = println("AB");override def funA=println("AB-A") }
    new AB().funAB
    new AB().funA
  }
}