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

