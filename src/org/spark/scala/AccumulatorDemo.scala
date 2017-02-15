package org.spark.scala

import org.apache.spark._
 

object AccumulatorDemo {
  def main(args:Array[String]){
    val conf=new SparkConf()
    conf.setAppName("AccumulatorDemo").setMaster("local")
    val sc=new SparkContext(conf)
     //?
    val blankLines=sc.accumulator(0)
    
    val lines=sc.textFile("file:/d:/BigData/data/lines.txt")
    val callSigns=lines.flatMap { line => 
      {if(line=="") blankLines+=1}
      line.split(" ")
    }
    callSigns.collect()
    println(blankLines.value)
  }
}