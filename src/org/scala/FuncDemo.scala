package org.scala

object FuncDemo {
  def main(args: Array[String]) {
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
}