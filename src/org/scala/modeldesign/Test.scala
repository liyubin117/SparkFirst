package org.scala.modeldesign

/**
 * 测试
 */
object Client extends App {

  testSingletonServer
  testSimpleFactoryChart
  testAbstractFactory
  testFactoryMethod
  testBuilder
  testProtoType
  testAdapter
  testBridge

  //测试单例模式
  def testSingletonServer() {
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

  //测试简单工厂
  def testSimpleFactoryChart() {
    //产出饼状图
    val pie = Chart("pie")
    pie.display()
    //产出柱状图
    val histogram = Chart("histogram")
    histogram.display()
    //产出折线图
    val line = Chart("line")
    line.display()
  }

  //测试工厂方法
  def testFactoryMethod() {
    //日志工厂1
    val Logger1: LoggerFactory = DatabaseLoggerFactory
    //日志工厂2
    val Logger2: LoggerFactory = FileLoggerFactory
    Logger1.createLogger().writeLog()
    Logger2.createLogger().writeLog()
  }

  //测试抽象工厂
  def testAbstractFactory() {
    // 构建皮肤工厂
    val factory: SkinFactory = SpringSkinFactory
    //按钮的展示方法
    factory.createButton().display()
    //文本框的方法
    factory.createTextField().display()
  }

  //测试建造者
  def testBuilder() {
    /**
     * 英雄构造器
     */
    val ab1: ActorBuilder = HeroBuilder
    val actor1 = ab1.construct(ab1)
    println(actor1.role + "的外观：")
    println("性别：" + actor1.sex)
    println("面容：" + actor1.face)
    println("服装：" + actor1.costume)
    println("发型：" + actor1.hairstyle)

    /**
     * 天使构造器
     */
    val ab2: ActorBuilder = AngleBuilder
    val actor2 = ab2.construct(ab2)
    println(actor2.role + "的外观：")
    println("性别：" + actor2.sex)
    println("面容：" + actor2.face)
    println("服装：" + actor2.costume)
    println("发型：" + actor2.hairstyle)

    /**
     * 恶魔构造器
     */
    val ab3: ActorBuilder = DevilBuilder
    val actor3 = ab3.construct(ab3)
    println(actor3.role + "的外观：")
    println("性别：" + actor3.sex)
    println("面容：" + actor3.face)
    println("服装：" + actor3.costume)
    println("发型：" + actor3.hairstyle)
  }

  //测试原型模式
  def testProtoType() {
    println("-------原型模式：-------")
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

  //测试适配器
  def testAdapter() {
    println("-------适配器模式：-------")
    //原数组
    val sources = Array(84, 76, 50, 69, 90, 91, 88, 86)
    //适配器接口
    val scoreOperation: ScoreOperation = OperationAdapter
    //排序
    val result = scoreOperation.sort(sources)
    println("成绩排序输出")
    result.foreach(x => print(x + ","))
    var key = 90
    println(s"查找成绩：$key")
    println(scoreOperation.search(result, key))
    if (scoreOperation.search(result, key) == 1) println(s"找到成绩$key") else println(s"没有找到成绩$key")
    key = 89
    println(s"查找成绩：$key")
    if (scoreOperation.search(result, key) == 1) println(s"找到成绩$key") else println(s"没有找到成绩$key")
  }

  //测试桥接模式
  def testBridge() {
    println("-------桥接模式：-------")
    /**
     * 图片
     */
    val image: Image = new JPGImage
    /**
     * 图片展示
     */
    image.imageImp = new WindowsImp

    /**
     * 设置文件
     */
    image.parseFile("你好")
  }

}
