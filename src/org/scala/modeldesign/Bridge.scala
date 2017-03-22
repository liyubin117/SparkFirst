package org.scala.modeldesign

/**
 * 桥接模式
 * 适用于两个或多个维度的变化
 * 本例中有两个维度的变化：1、图片格式（jpg、png、gif）；2、图片显示平台（win、linux、mac）
 * 若按普通思路需要建9个类，可扩展性几乎没有。
 * 按桥接模式只需要建6个类即可，且容易扩展
 */

//抽象图片格式
abstract class Image {
  //图片显示平台
  var imageImp: ImageImp
  //文件设置
  def parseFile(fileName: String)
}

/**
 * 抽象图片显示平台
 */
trait ImageImp {
  def doPaint()
}

/**
 * 具体jpg图片
 */
class JPGImage extends Image {
  override var imageImp: ImageImp = _

  override def parseFile(fileName: String) = {
    imageImp.doPaint()
    println(fileName + ",格式为JPG")
  }
}

/**
 * 具体png图片
 */
class PNGImage extends Image {
  override var imageImp: ImageImp = _

  override def parseFile(fileName: String) = {
    imageImp.doPaint()
    println(fileName + ",格式为PNG")
  }
}

/**
 * 具体gif图片
 */
class GIFImage extends Image {
  override var imageImp: ImageImp = _

  override def parseFile(fileName: String) = {
    imageImp.doPaint()
    println(fileName + ",格式为GIF")
  }
}


/**
 * win具体展示
 */
class WindowsImp extends ImageImp {
  override def doPaint() = println("在windows操作系统中显示图片：")
}

/**
 * linux具体展示
 */
class LinuxImp extends ImageImp {
  override def doPaint() = println("在linux操作系统中显示图片")
}

/**
 * mac具体展示
 */
class MaxOsImp extends ImageImp {
  override def doPaint() = println("在Mac操作系统中显示图片")
}