package org.spark.scala
import org.apache.spark._
object BroadcastDemo {
  def main(args: Array[String]) {
    val conf = new SparkConf()
    conf.setAppName("BroadcastDemo").setMaster("local")
    val sc = new SparkContext(conf)

    //创建广播变量
    val broads = sc.broadcast(3) //变量可以是任意类型
    //创建一个测试的List
    val lists = List(1, 2, 3, 4, 5)
    //转换为rdd（并行化）  
    val listRDD = sc.parallelize(lists)
    //map操作数据
    val results = listRDD.map(x => x * broads.value)
    //遍历结果
    results.foreach(x => println("增加后的结果：" + x))
  }

}