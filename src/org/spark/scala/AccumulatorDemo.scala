package org.spark.scala

import org.apache.spark.SparkConf
import org.apache.spark.SparkContext 
object AccumulatorDemo {
  def main(args:Array[String]):Unit={
    val conf=new SparkConf()
    conf.setAppName("AccumulatorDemo").setMaster("local[2]")
    val sc=new SparkContext(conf)
     //?
    //val accum=new IntAccumulatorParam(0)
    //val blankLines=sc.accumulator(accum)

/*    val lines=sc.textFile("file:/d:/BigData/data/lines.txt")
    val callSigns=lines.flatMap { line => 
      {if(line=="") blankLines+=1}
      line.split(" ")
    }
    callSigns.collect()
    println(blankLines.value)*/
    //创建广播变量
      var accumulator=sc.accumulator(0)
      //创建测试的List
      val lists=List(1,2,3,4,5)
      //转换list为RDD
      val rdd=sc.parallelize(lists)
      //进行map操作
      val result=rdd.foreach { x => {
        if(x<5){
          accumulator+=3
//          accumulator.add(2)
//          println(accumulator.value)不能再task中取值，只能在driver里获取
//Caused by: Java.lang.UnsupportedOperationException: Can't read accumulator value in task          
        }
      }}
      //取出增加后的广播变量值
      println("accumulator is "+accumulator.value)
  }
}