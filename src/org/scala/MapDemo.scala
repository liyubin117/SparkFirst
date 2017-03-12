package org.scala
//元组Tuple是不同类型对象的集合，映射是键值对偶的集合（二元组）
object MapDemo {
  def main(args:Array[String]){
    //默认是不可变映射
    val map1=Map(1->"li",2->"yu",3->"bin")
    //map1+=(1->"a")
    
    //可变映射
    //静态初始化
    var map2=scala.collection.mutable.Map(1->"a",2->"b")
    map2+=(3->"c")
    //动态初始化
    var map3=new scala.collection.mutable.HashMap[Int,String]
    map3=scala.collection.mutable.HashMap((1,"a"),(2,"b"))
    //映射 -> 相当于 二元组 (a,b)
    map3+=(3->"c")
    map3+=((4,"d"))
    
    //获取值
    println(map3.get(1))
    println(map3.getOrElse(10, 0))
    
    //更新映射中的值
    //可变映射更新：+= -=  不可变映射更新只能新建
    map3+=(3->"new 3")
    println(map3.get(3))
    map3-=(4)
    map2=map2+(3->"new 3")
    println(map2.get(3))
    
    //迭代映射
    //反转
    val a=for((k,v)<-map2) yield (v,k)
    println(a)
    //获取键集合和值集合
    println(a.keySet)
    println(a.values)
    
    //元组
    val tuple1=(1,2.1,"hello")
    //用_.位置访问，位置由1开始
    println(tuple1._2)
    //使用模式匹配获取元组的组元
    val (first,second,third)=tuple1
    println(first)
    //如果不是所有的部件都需要，在不需要的部位位置上用_
    val (t1,t2,_)=tuple1
    println(t2)
    //元组用于函数需要返回不止一个值的情况
    println("New York".partition(_.isUpper))
    
    //拉链操作
    val symbols=Array("<","-",">")
    val counts=Array(2,10,2)
    val pairs=symbols.zip(counts)
    for((k,v)<-pairs) print(k*v)
    println()
    //把对偶的集合转换成映射
    println(pairs.toMap)
  }
}