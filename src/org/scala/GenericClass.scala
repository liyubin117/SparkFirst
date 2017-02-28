package org.scala

class GenericClass[K, V](val k: K, val v: V) {
  def print() {
    println(k + "," + v)
  }
}

//范型上下界
//指定T必须是实现了Comparable接口的类型，  
class ComparableGenericClass[T <: Comparable[T]](val v1: T, val v2: T) {  
  def min() = {  
    if (v1.compareTo(v2) < 0) v1 else v2  
  }  
}

object GenericClass {
  def main(args: Array[String]) {
    ///不需要指定K,V的类型，自动推断
    val gc = new GenericClass("ABC", 100)
    ///显式指定K,V的类型，自动推断
    val gc2 = new GenericClass[String, Integer]("ABC", 100)
    gc.print
    gc2.print
    
    val cgc = new ComparableGenericClass[String]("10", "20")  
    println(cgc.min())  
  }
}