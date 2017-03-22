package org.scala.modeldesign

/**
 * 抽象工厂模式：
 * 多个抽象产品类，每个抽象产品类可以派生出多个具体产品类。一个抽象产品继承了其他多个抽象产品
 * 一个抽象工厂类，可以派生出多个具体工厂类。
 * 每个具体工厂类可以创建多个具体产品类的实例。
 */

/**
 * 皮肤工厂特质
 */
trait SkinFactory {
  /**
   * 创建按钮方法
   * @return 按钮特质
   */
  def createButton(): Button

  /**
   * 创建文本框方法
   * @return 文本框特质
   */
  def createTextField(): TextField
}

/**
 * 按钮特质
 */
trait Button {
  /**
   * 展示方法
   */
  def display(): Unit
}

/**
 * 文本框特质
 */
trait TextField {
  /**
   * 展示方法
   */
  def display(): Unit
}

/**
 * 春天皮肤工厂，生产春天皮肤的文本框和按钮
 */
object SpringSkinFactory extends SkinFactory {

  /**
   * 春天皮肤按钮
   */
  class SpringButton extends Button {
    override def display(): Unit = println("spring button")
  }

  /**
   * 春天皮肤文本框
   */
  class SpringTextField extends TextField {
    override def display(): Unit = println("spring textField")
  }

  /**
   * 复写方法，产生春天皮肤的按钮
   * @return 按钮特质
   */
  override def createButton(): Button = new SpringButton

  /**
   * 复写方法，产生春天皮肤的文本框
   * @return 文本框特质
   */
  override def createTextField(): TextField = new SpringTextField
}

/**
 * 夏天皮肤工厂
 */
object SummerSkinFactory extends SkinFactory {

  /**
   * 夏天皮肤按钮
   */
  class SummerButton extends Button {
    override def display(): Unit = println("summer button")
  }

  /**
   * 夏天皮肤文本框
   */
  class SummerTextField extends TextField {
    override def display(): Unit = println("summer textField")
  }

  /**
   * 复写方法，产生夏天皮肤按钮
   * @return 按钮特质
   */
  override def createButton(): Button = new SummerButton

  /**
   * 复写方法，产生夏天皮肤文本框
   * @return 文本框特质
   */
  override def createTextField(): TextField = new SummerTextField
}