package org.scala.modeldesign

/**
 * 建造者模式
 */

/**
 * Builder
 * 角色建造者
 */
trait ActorBuilder {
  /**
   * 角色
   */
  protected var actor: Actor

  /**
   * 设置角色
   */
  def buildRole(): Unit

  /**
   * 设置性别
   */
  def buildSex(): Unit

  /**
   * 设置脸型
   */
  def buildFace(): Unit

  /**
   * 设置服装
   */
  def buildCostume(): Unit

  //是否为光头
  val BareHead = false

  //设置发型
  def buildHairStyle(): Unit

  //创建角色
  def createActor(): Actor

  /**
   * 控制枢纽，控制每一步的顺序
   * Director
   * @param ab 建造者
   * @return 创建的角色
   */
  def construct(ab: ActorBuilder): Actor = {
    //先设置角色，再设置性别，其次是脸型，服装，用钩子程序判断是否是光头，不是光头则设置发型
    ab.buildRole()
    ab.buildSex()
    ab.buildFace()
    ab.buildCostume()
    if (!ab.BareHead) {
      ab.buildHairStyle()
    }
    ab.createActor()
  }
}

/**
 * 角色类
 * Product
 */
class Actor {
  //角色
  var role: String = _
  //性别
  var sex: String = _
  //脸型
  var face: String = _
  //服装
  var costume: String = _
  //发型
  var hairstyle: String = "光头"

}

/**
 * 英雄建造器
 * ConcreteBuilder
 */
object HeroBuilder extends ActorBuilder {

  override protected var actor: Actor = new Actor()

  override def buildRole() = actor.role = "英雄"

  override def buildSex() = actor.sex = "男"

  override def buildFace() = actor.face = "英俊"

  override def buildCostume() = actor.costume = "盔甲"

  override def buildHairStyle() = actor.hairstyle = "飘逸"

  override def createActor() = actor
}

object DevilBuilder extends ActorBuilder {
  override protected var actor: Actor = new Actor()

  override def buildRole() = actor.role = "恶魔"

  override def buildSex() = actor.sex = "妖"

  override def buildFace() = actor.face = "丑陋"

  override def buildCostume() = actor.costume = "黑衣"

  override def buildHairStyle() = actor.hairstyle = "我想要头发"

  /**
   * 由于钩子程序的存在并不会执行buildHairStyle
   */
  override val BareHead = true
  override def createActor() = actor
}

object AngleBuilder extends ActorBuilder {
  override protected var actor: Actor = new Actor()

  override def buildRole() = actor.role = "天使"

  override def buildSex() = actor.sex = "女"

  override def buildFace() = actor.face = "漂亮"

  override def buildCostume() = actor.costume = "白裙"

  override def buildHairStyle() = actor.hairstyle = "长发"

  override def createActor() = actor
}