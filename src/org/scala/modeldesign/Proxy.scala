package org.scala.modeldesign
import org.scala.base.MySQLDBConn
import scala.collection.mutable.ArrayBuffer
/**
 * 代理模式
 */
/**
 * 抽象主题类：查询特质
 */
trait Searcher {
  def doSearch(id: String, pass: String, keyword: String): String
}

/**
 * 身份验证业务单例对象
 */
object AccessValidator {
  /**
   * 验证方法
   * @param id 查询的id
   * @param pass 用户名
   * @return 用户是否合法
   */
  def validate(id: String, pass: String): Boolean = {
    println(s"数据库验证$id 是否为合法用户")
    val params = new ArrayBuffer[Any]()
    params += id
    val result = AccessDAO.checkUser(params)

    /**
     * 判断result.head获取第一个元素的name和result.head获取第一个元素的pass和传入的id和pass是否一致
     * 一致返回true，否则返回false
     */
    if (result.head.getOrElse("name", "null") == id && result.head.getOrElse("pass", "null").toString == pass) {
      println("登录成功")
      true
    } else {
      println("登录失败")
      false
    }
  }

}

/**
 * 日志记录类
 */
object Logger2 {
  /**
   * 记录日志
   * @param id 登录id
   */
  def log(id: String): Unit = {
    val params = new ArrayBuffer[Any]()
    params += id
    val row = AccessDAO.insertLog(params)
    if (row > 0) {
      println(s"记录$id 到数据库")

    } else {
      println("出现异常")
    }

  }
}

/**
 * 真实主题类：具体查询类
 */
object RealSearcher extends Searcher {
  /**
   * 复写查询方法
   * @param id 用户id
   * @param pass pass
   * @param keyword 关键字
   * @return 查询内容
   */
  override def doSearch(id: String, pass: String, keyword: String): String = {
    println(s"用户：$id 使用关键字$keyword 查询商务信息！")
    "具体内容"
  }
}

/**
 * 代理主题类：代理查询类
 */
object ProxySearcher extends Searcher {
  /**
   * 真实查询对象
   */
  private val realSearcher = RealSearcher
  /**
   * 身份验证对象
   */
  private val accessValidator = AccessValidator
  /**
   * 日志对象
   */
  private val logger = Logger2

  /**
   * 复写查询
   * @param id 用户id
   * @param pass 密码
   * @param keyword 关键字
   * @return 查询结果
   */
  override def doSearch(id: String, pass: String, keyword: String): String = {
    /**
     * 判断是否登录成功，如果登录成功则记录到数据库中，并执行真实查询类的查询方法
     */
    if (validate(id, pass)) {
      log(id)
      realSearcher.doSearch(id, pass, keyword)
    } else {
      null
    }
  }

  /**
   * 日志方法，使用日志对象的日志方法
   * @param id 用户id
   */
  def log(id: String): Unit = logger.log(id)

  /**
   * 身份验证类
   * @param id 用户id
   * @param pass 密码
   * @return 是否验证成功
   */
  def validate(id: String, pass: String): Boolean = accessValidator.validate(id, pass)
}

/** * 代理帐号操作数据库对象 *  */
object AccessDAO {
  /**     * 查询sql     */
  private val sqlSelect = "select name,pass from user where name = ?"
  /**     * 查询     * @param params 参数列表     * @return ArrayBuffer     */
  def checkUser(params: ArrayBuffer[Any]) = MySQLDBConn.Result(sqlSelect, params)
  /**     * 插入日志列表sql     */
  private val sqlInsert = "insert into log(userid) values(?)"
  /**     * 插入操作     * @param params 参数     * @return 受影响行数     */
  def insertLog(params: ArrayBuffer[Any]) = MySQLDBConn.updateRow(sqlInsert, params)
}

object Proxy{
  def testProxy() {
    println("--------------代理类模式：--------------")
    val searcher1: Searcher = ProxySearcher
    println(searcher1.doSearch("ctt", "12223", "hello"))
    val searcher2: Searcher = ProxySearcher
    println(searcher2.doSearch("ct", "12334", "helloworld"))

    print(searcher1.eq(searcher2))
  }
}
