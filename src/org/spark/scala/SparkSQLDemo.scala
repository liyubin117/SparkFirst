package org.spark.scala
import org.apache.spark.sql.hive.HiveContext
import org.apache.spark.{SparkConf,SparkContext}

object SparkSQLDemo {
  def main(args:Array[String]){
    val conf=new SparkConf()
    conf.setAppName("Spark SQL Demo").setMaster("local")
    val sc=new SparkContext(conf)
    
    val hiveCtx=new HiveContext(sc)
    /*val input = hiveCtx.(inputFile)
    // 注册输入的SchemaRDD
    input.registerTempTable("tweets")
    // 依据retweetCount （ 转发计数）选出推文
    val topTweets = hiveCtx.sql("SELECT text, retweetCount FROM
    tweets ORDER BY retweetCount LIMIT 10")
*/  
    }
}