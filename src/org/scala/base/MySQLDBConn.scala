package org.scala.base

import java.sql._
import scala.collection.mutable
import scala.collection.mutable.ArrayBuffer
/** * Mysql数据库连接 * */
object MySQLDBConn {
  try {
    /**         * 加载驱动         */
    classOf[com.mysql.jdbc.Driver]
  } catch {
    case e: ClassNotFoundException => e.printStackTrace()
  }
  /**     * 获取数据库连接     * @return 数据库连接     */
  def connection(): Connection = DriverManager.getConnection(DBUtil.url, DBUtil.username, DBUtil.pass)
  /**     * 返回查询的结果保存成ArrayBuffer     * @param sql 执行sql语句     * @param params 参数     * @return ArrayBuffer     */
  def Result(sql: String, params: ArrayBuffer[Any]): ArrayBuffer[mutable.HashMap[Any, Any]] = {
    var arrayBuffer = new ArrayBuffer[mutable.HashMap[Any, Any]]()
    var conn: Connection = null
    var prepare: PreparedStatement = null
    var rs: ResultSet = null
    try {
      conn = connection()
      prepare = conn.prepareStatement(sql)
      var i = 0
      if (params != null) {
        if (params.nonEmpty) {
          for (s <- params) {
            prepare.setObject(i + 1, s)
            i += 1
          }
        }
      }
      rs = prepare.executeQuery()
      while (rs.next()) {
        val md = rs.getMetaData
        val column = md.getColumnCount
        var rowData = new mutable.HashMap[Any, Any]()
        for (i <- 1 to column) {
          rowData.put(md.getColumnName(i), rs.getObject(i))
        }
        arrayBuffer += rowData
      }
    } catch {
      case sql: SQLException => sql.printStackTrace()
    } finally {
      closeAll(conn, prepare, rs)
    }
    arrayBuffer
  }
  /**     * 更新受影响行数     * @param sql sql语句     * @param params 参数     * @return 受影响行数     */
  def updateRow(sql: String, params: ArrayBuffer[Any]): Int = {
    var conn: Connection = null
    var prepare: PreparedStatement = null
    try {
      conn = connection()
      prepare = conn.prepareStatement(sql)
      var i = 0
      if (params != null) {
        if (params.nonEmpty) {
          for (s <- params) {
            prepare.setObject(i + 1, s)
            i += 1
          }
        }
      }
      return prepare.executeUpdate()
    } catch {
      case sql: SQLException => sql.printStackTrace()
    } finally {
      closeAll(conn, prepare, null)
    }
    -1
  }
  /**     * 关闭数据库连接     * @param conn 数据库连接     */
  def closeConn(conn: Connection): Unit = {
    conn.close()
  }
  /**     * 关闭resultSet     * @param resultSet resultSet     */
  def closeResultSet(resultSet: ResultSet): Unit = {
    resultSet.close()
  }
  /**     * 关闭preparedStatement     * @param preparedStatement preparedStatement     */
  def closePreparedStatement(preparedStatement: PreparedStatement): Unit = {
    preparedStatement.close()
  }
  /**     * 关闭全部     * @param connection connection     * @param preparedStatement preparedStatement     * @param resultSet resultSet     */
  def closeAll(connection: Connection, preparedStatement: PreparedStatement, resultSet: ResultSet): Unit = {
    if (connection != null) {
      closeConn(connection)
    }
    if (preparedStatement != null) {
      closePreparedStatement(preparedStatement)
    }
    if (resultSet != null) {
      closeResultSet(resultSet)
    }
  }
}
