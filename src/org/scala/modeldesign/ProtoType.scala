package org.scala.modeldesign

/**
 * 原型模式
 * 样例类，周报，通过其提供的copy方法完成一个原型模式
 * @param name 姓名
 * @param date 时间
 * @param content 内容
 */
case class WeeklyLog(var name: String, var date: String, var content: String, var attachment: Attachment)

/**
 * 附件样例类
 * @param name 附件名
 */
case class Attachment(var name: String) {
    def download() = println("下载附件，文件名为：" + name)
}



/**
 * 原型管理器
 */
object PrototypeManager {
    //存储officedocument对象
    var ht = new scala.collection.mutable.HashMap[String, OfficialDocument]
    //默模版
    ht += ("far" -> new FAR("<可行性报告>"))
    ht += ("srs" -> new SRS("<软件需求规格说明书>"))

    //增加文章模版
    def addOfficialDocument(key: String, officialDocument: OfficialDocument) = ht += (key -> officialDocument)

    //获取模版
    def getOfficialDocument(key: String): OfficialDocument = ht.getOrElse(key, OfficialDocument("null")).copy()

}

/**
 * 公文样例类
 * @param title 标题
 */
case class OfficialDocument(title: String) {
    def display(): Unit = println("公文标题：" + title)
}

/**
 * 可行性分析报告
 * @param title 标题
 */
class FAR(title: String) extends OfficialDocument(title)

/**
 * 软件需求规格说明书
 * @param title 标题
 */
class SRS(title: String) extends OfficialDocument(title)