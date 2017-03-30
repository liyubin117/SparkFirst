package org.scala.modeldesign

import scala.collection.mutable.ArrayBuffer
import scala.util.Random

//其实Scala的object本身就是单例对象，除了可用作单例对象外，还具有静态方法的功能如存放工具类等
object LoadBalance {
  /**
   * 服务器组
   */
  var servers = new ArrayBuffer[String]()

  /**
   * 增加服务器
   * @param server 服务器名
   */
  def addServer(server: String): Unit = {
    println(s"add $server")
    servers += server
  }

  /**
   * 删除服务器
   * @param server 服务器名
   */
  def removeServer(server: String): Unit = {
    println(s"remove $server")
    servers -= server
  }

  /**
   * 随机获取服务器转发
   * @return 服务器名
   */
  def getServer(): String = {
    servers(Random.nextInt(servers.length))
  }
}

object SingletonServer{
  def testSingletonServer() {
    println("--------------单例模式：--------------")
    //构建负载均衡器1
    val balance1 = LoadBalance
    //构建负载均衡器2
    val balance2 = LoadBalance
    //判断balance1和balance2是否一致
    println(balance1.eq(balance2))
    //添加服务器
    balance1.addServer("Server1")
    balance1.addServer("Server2")
    balance1.addServer("Server3")
    balance1.addServer("Server4")
    //随机获取服务器
    for (i <- 1 to 10) {
      println("get " + balance1.getServer())
    }
    //用balance2移除服务器
    balance2.removeServer("Server1")
    //随机获取服务器
    for (i <- 1 to 10) {
      println("get " + balance2.getServer())
    }
  }
}
