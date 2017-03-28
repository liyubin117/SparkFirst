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
