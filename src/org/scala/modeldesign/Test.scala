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
  testComposite
  testDecorator
  //程序有一些问题，待研究
  //testProxy
  testChain
  testCommand
  testInterpreter
  testMediator

  //测试单例模式
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

  //测试简单工厂
  def testSimpleFactoryChart() {
    println("--------------简单工厂模式：--------------")
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
    println("--------------工厂方法模式：--------------")
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
    println("--------------建造者模式：--------------")
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

  //测试适配器
  def testAdapter() {
    println("--------------适配器模式：--------------")
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
    println("--------------桥接模式：--------------")
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

  //测试组合模式
  def testComposite() {
    println("--------------组合模式：--------------")
    /**
     * 文件夹
     */
    val folder1 = Folder("李宇彬的资料")
    val folder2 = Folder("图像资料")
    val folder3 = Folder("文本文件")
    val folder4 = Folder("视频资料")

    /**
     * 文件
     */
    val file1 = ImageFile("qq截图.jpg")
    val file2 = ImageFile("美图.png")
    val file3 = TextFile("solr.log")
    val file4 = TextFile("迭代.doc")
    val file5 = VideoFile("测试.avi")

    folder2.add(file1)
    folder2.add(file2)
    folder3.add(file3)
    folder3.add(file4)
    folder4.add(file5)
    folder1.add(folder2)
    folder1.add(folder3)
    folder1.add(folder4)

    folder1.remove(folder3)
    //    folder1.child(2).killVirus()
    /**
     * 对根文件夹杀毒，递归调用
     */
    folder1.killVirus()
  }

  //测试装饰器
  def testDecorator() {
    println("--------------装饰器模式：--------------")
    /**
     * 窗口
     */
    val component: Component = new Window
    /**
     * 滚动条来装饰窗口
     */
    val componentSB: Component = new ScrollBarDecorator(component)
    /**
     * 黑色边框装饰滚动条装饰类
     */
    val componentBB: Component = new BlackBorderDecorator(componentSB)
    componentBB.display()
  }

  //测试代理类
  def testProxy() {
    println("--------------代理类模式：--------------")
    val searcher1: Searcher = ProxySearcher
    println(searcher1.doSearch("ctt", "12223", "hello"))
    val searcher2: Searcher = ProxySearcher
    println(searcher2.doSearch("ct", "12334", "helloworld"))

    print(searcher1.eq(searcher2))
  }

  //测试责任链
  def testChain() {
    println("--------------责任链模式：--------------")
    val ZH: Approve = new Director("周华")
    val YJY: Approve = new VicePresident("游建友")
    val WZX: Approve = new President("吴志雄")
    val meeting: Approve = new Congress("董事会")

    ZH.setSuccessor(YJY)
    YJY.setSuccessor(WZX)
    WZX.setSuccessor(meeting)

    ZH.processRequest(PurchaseRequest(45000, 1001, "大数据卡口项目"))
    ZH.processRequest(PurchaseRequest(60000, 1002, "服务器购置"))
    ZH.processRequest(PurchaseRequest(145000, 1003, "星环开科技专利购买"))
    ZH.processRequest(PurchaseRequest(1145000, 1004, "公司并购"))
  }

  //测试命令模式
  def testCommand() {
    println("--------------命令模式：--------------")
    val command: AbstractCommand = new AddCommand
    val form = new CalculatorForm(command)
    form.compute(10)
    form.compute(5)
    form.undo()
    form.undo()
    form.redo()
    form.redo()
    form.redo()
    form.undo()
    form.undo()
    form.undo()
    form.redo()
    form.compute(100)
  }

  //测试解释器模式
  def testInterpreter() {
    println("--------------解释器模式：--------------")
    val robot = new Robot
    robot up Run length 10 and Then left Move length 5 and Then right Run length 3 and Then down Move length 8
  }

  //测试中介者模式
  def testMediator() {
    println("--------------中介者模式：--------------")
    val admin: Person = new Admin("admin")
    val member1: Person = new Member("member1")
    val member2: Person = new Member("member2")
    val member3: Person = new Member("member3")
    val member4: Person = new Member("member4")

    val qqSoftware: AbstractSoftware = new QQSoftware("研发中心")
    admin.setAbstractSoftware(qqSoftware)
    member1.setAbstractSoftware(qqSoftware)
    member2.setAbstractSoftware(qqSoftware)
    member3.setAbstractSoftware(qqSoftware)
    member4.setAbstractSoftware(qqSoftware)

    admin.add(admin)
    admin.add(member1)
    admin.add(member2)
    admin.add(member3)
    admin.add(member4)
    admin.add(member1)

    admin.speak("hello")
    admin.remove(member1)

    member1.speak("hi")

    member2.add(member1)
    member2.remove(member2)

    member2.speak("admin")

    member3.privateChat(admin, "你好")
    member3.privateChat(member2, "你好")

    member2.privateChat(admin, "加我")

    println("******************")

    val msnSoftware: AbstractSoftware = new MSNSoftware("通研事业部")
    admin.setAbstractSoftware(msnSoftware)
    member1.setAbstractSoftware(msnSoftware)
    member2.setAbstractSoftware(msnSoftware)
    member3.setAbstractSoftware(msnSoftware)
    member4.setAbstractSoftware(msnSoftware)

    admin.add(admin)
    admin.add(member1)
    admin.add(member2)
    admin.add(member3)
    admin.add(member4)
    admin.add(member1)

    admin.speak("hello")
    admin.remove(member1)

    member1.speak("hi")

    member2.add(member1)

    member2.speak("admin")

    member2.privateChat(member3, "test")

  }
}
