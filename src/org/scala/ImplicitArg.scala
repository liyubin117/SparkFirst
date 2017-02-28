package org.scala

object ObjectA {
  def print(content: String)(implicit prefix: String) {
    println(content + "," + prefix)
  }
}

//ObjectA隐式转换的作用域
object ObjectAWrapper {
  implicit val DEFAULT_OBJECT_A_STRING = "ObjectA"
  //ERROR:若设置两个，会产生二义性
  //implicit val DEFAULT_OBJECT_A_STRING2 = "ObjectA"
}

class A[T] {
  //这里相当于为T指定约束，即T必须能够隐式转换到Ordered  
  //T能够隐式转换到Ordered，那么对类型T的变量使用<操作时，将隐式地将T转换为Ordered类型的变量，然后调用Ordered类型的<方法  
  //T隐式转换到Ordered，需要预先定义  
  def min(a: T, b: T)(implicit order: T => Ordered[T]) = {
    if (a < b) a else b
  }
}

object ImplicitArgument {
  def main(args: Array[String]) {
    ObjectA.print("ABC")("DEF")
    import ObjectAWrapper._ //Error: not enough argument if this line doesn't exist
    //为String类型的参数隐式的提供DEFAULT_OBJECT_A_STRING值
    ObjectA.print("ABC")
    ObjectA.print("ABC")("XYZ")

    val a = new A[Int]();
    println(a.min(10, 11)) //Scala已经实现了Int到Ordered的隐式转换，否则3 < 4做不了  
    class B {}

    /**
     * 报错的原因是No implicit view available from B => Ordered[B].即B没有定义到Ordered的隐式转换，所以报错
     * val b = new A[B]() //
     * b.min(new B(), new B())
     */
  }
}