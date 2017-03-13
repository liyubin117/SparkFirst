package org.scala

object ClosureTest {

  def sum(args: List[Int]) = {
    var sum = 0
    //sum += _是函数常量，其中sum是自由变量  
    val calc = sum += (_: Int)
    args.foreach(calc)
    println("sum1: " + sum) //6， sum是自由变量，计算结束后，sum的值在闭包内的变化，能够在外面也看到  
    args.foreach(calc) //sum的值改变后，函数常量能够看到这个变化，再次计算结果12  
    println("sum2: " + sum) //12,  
  }

  def increaseBy(offset: Int) = {

    def add(x: Int) = {
      x + offset
    }
    add _
    //方法体可以直接使用 (x: Int) => x + offset  
  }

  def main(args: Array[String]) {
    val args = List(1, 2, 3)
    sum(args)
    println(increaseBy(10)(100)) //110  
  }
}  