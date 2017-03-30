package org.scala.modeldesign

//解释器模式
/**
 * 行为特质
 */
trait Action

/**
 * 方向特质
 */
trait Direction

/**
 * 向上实现方向特质
 */
object Up extends Direction

/**
 * 向下实现方向特质
 */
object Down extends Direction

/**
 * 向左实现方向特质
 */
object Left extends Direction

/**
 * 向右实现方向特质
 */
object Right extends Direction

/**
 * run实现行为特质
 */
object Run extends Action

/**
 * move实现行为特质
 */
object Move extends Action

/**
 * then实现行为特质
 */
object Then extends Action

/**
 * robot up move length 1 and right run length 3
 * @param x x坐标
 * @param y y坐标
 */
class Robot(var x: Int = 0, var y: Int = 0) {
  /**
   * 行为
   */
  private var actions: String = null
  /**
   * 方向
   */
  private var directions: Direction = null
  /**
   * 记录方向
   */
  private var tag: String = null

  /**
   * 向上
   * @param action 行为
   * @return 自身类型
   */
  def up(action: Action): this.type = {
    action match {
      case Run => actions = "快速移动"
      case Move => actions = "移动"
    }
    directions = Up
    this
  }

  /**
   * 向下
   * @param action 行为
   * @return 自身类型
   */
  def down(action: Action): this.type = {
    action match {
      case Run => actions = "快速移动"
      case Move => actions = "移动"
    }
    directions = Down
    this
  }

  /**
   * 向左
   * @param action 行为
   * @return 自身类型
   */
  def left(action: Action): this.type = {
    action match {
      case Run => actions = "快速移动"
      case Move => actions = "移动"
    }
    directions = Left
    this
  }

  /**
   * 向右
   * @param action 行为
   * @return 自身类型
   */
  def right(action: Action): this.type = {
    action match {
      case Run => actions = "快速移动"
      case Move => actions = "移动"
    }
    directions = Right
    this
  }

  /**
   * 长度设置
   * @param length 长度
   * @return 自身类型
   * @return 自身类型
   */
  def length(length: Int): this.type = {
    directions match {
      case Up =>
        y += length; tag = "向上"
      case Down =>
        y -= length; tag = "向下"
      case Left =>
        x -= length; tag = "向左"
      case Right =>
        x += length; tag = "向右"
    }
    println(s"机器人$tag$actions :$length ,此时的位置为x坐标:$x,y坐标:$y")
    this
  }

  /**
   * and方法用于过度
   * @param word Then
   * @return 自身类型
   */
  def and(word: Then.type) = this

}

object Interpreter {
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
