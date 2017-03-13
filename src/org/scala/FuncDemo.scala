package org.scala

object FuncDemo {
  def main(args: Array[String]) {
    println(sum2(1, 2, 3, 4, 5))
    exceptionProcessing()
    println(addXY(3)(5))
    
    //部分应用函數
    val a = addXY(1)(_)
    println(a(3))
    
    println(closure(10)(100))
    //函数与表达式
    println(myValue)
    println(myValue2)
    println(myValue3())
  }
  //变长参数T*
  def sum2(elements: Int*): Int = {
    var sum = 0
    //elements.foreach(x=>sum+=x)
    elements.foreach(sum += _)
    sum
  }
  //异常
  def exceptionProcessing() {
    try {
      throw new Exception("error");
    } catch {
      case e: IllegalArgumentException => println("IAE" + e.getMessage)
      case e: NullPointerException => println("NPE" + e.getMessage);
      case e: Exception => println("error again " + e) //如果是Exception，则重新抛出  
    }
  }
  //柯里化，带有多个参数的函数
  def addXY(x: Int)(y: Int): Int = {
    x + y
  }
  //闭包。注意Int=>Int是函数sum的类型，即输入为Int，输出也为Int
  def closure(x: Int): Int => Int = {
    def sum(y: Int): Int = {
      x + y //x is not in the scoped of the  
    }
    sum
  }
  //不加=，相当于:Unit=
  def myValue{
    var a=10
    a+10
  }
  //不加括号，只 是个块表达式，不是函数
  def myValue2={
    var a=10
    a+10
  }
  //加括号，是函数
  def myValue3()={
    var a=10
    a+10
  }

    //变长参数
    def sum(args: Int*): Int = {
      var result = 0;
      for (arg <- args) {
        result += arg;
      }
      result;
    }
    println(sum(1, 2, 3));
//    val s=sum(1 to 3); //错误。如果sum函数被调用时传入的是单个参数，那么该参数必须是单个参数，而不是一个整数区间，即1,2,3和(1,2,3)不同
    //解决方法是追加:_*，使之作为参数序列处理
    println(sum(1 to 3:_*));
    
    //sum的递归写法
    def sumDG(args:Int*):Int={
      if(args.length==0) 0
      else
        args.head+sumDG(args.tail:_*)
    }
    println(sumDG(1,2,3))
    
    //过程，没有=
    def sayHello(){
      println("hello!")
    }
    sayHello
  
}