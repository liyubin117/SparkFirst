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

object ProtoType{
  def testProtoType() {
    println("--------------原型模式：--------------")
    val log_previous = WeeklyLog("陈珏煊", "第10周", "本周主要是进行设计模式学习", Attachment("原型模式.pdf"))
    println("*******周报*******")
    println("周次：" + log_previous.date)
    println("姓名：" + log_previous.name)
    println("内容：" + log_previous.content)
    log_previous.attachment.download()
    println("----------------------------")

    val log_new = log_previous.copy()
    /**
     * 结果为false，由此可以看出为深克隆
     */
    println(log_new.eq(log_previous))

    /**
     * 结果为true，附件对象为浅克隆
     */
    println(log_new.attachment.eq(log_previous.attachment))
    log_new.date = "第11周"
    log_new.attachment = Attachment("工厂模式.pdf")
    println("*******周报*******")
    println("周次：" + log_new.date)
    println("姓名：" + log_new.name)
    println("内容：" + log_new.content)
    log_new.attachment.download()
    println("----------------------------")

    //原型管理器
    val pm = PrototypeManager
    //公文对象，从hashmap中获取克隆对象
    val doc1 = pm.getOfficialDocument("")
    val doc2 = pm.getOfficialDocument("")
    doc1.display()
    doc2.display()
    println(doc1.eq(doc2))
  }
}