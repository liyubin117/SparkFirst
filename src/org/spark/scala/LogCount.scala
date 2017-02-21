package org.spark.scala
import org.apache.spark._
object LogCount {
  def main(args:Array[String]){
    val conf=new SparkConf().setAppName("LogCount").setMaster("local[2]")
    val sc=new SparkContext(conf)
    
    val log=sc.textFile("file/log.txt",2)
    val logCount=log.map(_.split(" ")).filter(_.size>1).map(x=>(x(0),1)).reduceByKey(_+_)
    logCount.foreach(println)
  }
  
}