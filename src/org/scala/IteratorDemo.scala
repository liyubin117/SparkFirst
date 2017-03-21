package org.scala

object IteratorDemo extends App{
  val it=Iterator("a","b","c")
    
  var count=0L
  while(it.hasNext){
    count+=1
    it.next()    
  }
  println("while loop:"+count)
  
  //Iterator对象只能迭代一次
  println("it.size:"+it.size)
  println("it.length:"+it.length)
  
}