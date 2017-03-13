package org.scala

import scala.collection.mutable.ArrayBuffer

object ArrayDemo {
  def main(args:Array[String]){
	  //Array 定长数组
    //动态初始化，所有元素初始化为null
    val nums=new Array[Int](10);
    val str=new Array[String](10)
    //静态初始化，不需要new
    val s=Array("Hello","World")
    println(s(0))
    s(0)="Good"
    println(s(0))
    
    //ArrayBuffer 数组缓冲，变长数组
    //有无new都可以
    //动态初始化
    val abi=ArrayBuffer[Int]()
    var abs=new ArrayBuffer[String]();
    //静态初始化
    val abd=ArrayBuffer(12.1,10.5);
    abi+=1
    abi+=(1,2,3)
    abi++=Array(4,5,6)
    abi.trimEnd(2)
    println(abi)
    abs=ArrayBuffer("li","bin")
    abs.insert(1,"yu")
    println(abs)
    abs.remove(0)
    println(abs)
    
    //数组缓冲可以转换为定长数组
    val a:Array[String]=abs.toArray
    
    //遍历数组
    for(i<-0 until abi.length){
      print(abi(i)+" ")
    }
    println()
    //两个元素一跳，0 2 4
    for(i<-0 until (abi.length,2)){
      print(abi(i)+" ")
    }
    println()
    //反序遍历
    for(i<-(0 until abi.length).reverse){
      print(abi(i)+" ")
    }
    println()
    //类似于java的for in循环，不用下标遍历
    for(el<-abi){
      print(el+" ")
    }
    println()
    //增加守卫if 显示偶数
    for(el<-abi if el%2==0){
      print(el+" ")
    }
    println()
    
    //数组转换 yield
    val result=for(el<-abi) yield 2*el
    val result2=abi.map(_*2)
    println(result)
    println(result2)
    
    //常用算法
    val arr=Array(10,30,20)
    println(arr.sum)
    println(arr.max)
    println(arr.min)
    var sortedArr=arr.sorted
    //for(el<-sortedArr) print(el+" ")
    //不用像上面这么麻烦，直接用mkString返回一个String对象
    println(sortedArr.mkString(" "))
    //提供比较函数
    val descArr=arr.sortWith(_>_)
    println(descArr.mkString(" "))
    //quickSort可直接对数组排序，但无法对数组缓冲排序
    scala.util.Sorting.quickSort(arr)
    println(arr.mkString(" "))
    
    //多维数组
    val arrs=Array.ofDim[Int](2,3)
    arrs(0)(1)=10
    println(arrs(0)(1))
    
  }
}