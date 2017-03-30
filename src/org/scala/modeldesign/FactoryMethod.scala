package org.scala.modeldesign

/**
 * 工厂方法模式：
 * 一个抽象产品类，可以派生出多个具体产品类。
 * 一个抽象工厂类，可以派生出多个具体工厂类。
 * 每个具体工厂类只能创建一个具体产品类的实例。
 */

/**
 * 日志工厂特质
 */
trait LoggerFactory {
  /**
   * 创建日志方法
   * @return 日志
   */
  def createLogger(): Logger
}

/**
 * 日志特质
 */
trait Logger {
  /**
   * 写日志方法
   */
  def writeLog(): Unit
}

/**
 * 数据库日志，继承日志特质
 */
class DatabaseLogger extends Logger {
  override def writeLog(): Unit = println("数据库日志记录")
}

/**
 * 文件日志，继承日志特质
 */
class FileLogger extends Logger {
  override def writeLog(): Unit = println("文件日志记录")
}

/**
 * 数据库日志工厂方法
 */
object DatabaseLoggerFactory extends LoggerFactory {
  override def createLogger(): Logger = new DatabaseLogger
}

/**
 * 文件日志方法
 */
object FileLoggerFactory extends LoggerFactory {
  override def createLogger(): Logger = new FileLogger
}

object FactoryMethod{
  def testFactoryMethod() {
    println("--------------工厂方法模式：--------------")
    //日志工厂1
    val Logger1: LoggerFactory = DatabaseLoggerFactory
    //日志工厂2
    val Logger2: LoggerFactory = FileLoggerFactory
    Logger1.createLogger().writeLog()
    Logger2.createLogger().writeLog()
  }
}
