package org.scala.modeldesign

import scala.collection.mutable.ArrayBuffer

/**
 * 迭代器模式
 * 其实scala对反向遍历的支持比java好很多，我们完全可以使用自身的去实现反向遍历
 * 即将迭代器直接reverse即可
 * 但此次通过自定义游标的方式结合内部类和外部类两种方式实现
 */
/**
 * 抽象迭代器特质
 */
trait AbstractIterator {

  /**
   * 移至下一个元素
   */
  def next(): Unit

  /**
   * 判断是否为最后一个元素
   * @return 是否为最后一个元素
   */
  def isLast: Boolean

  /**
   * 移至上一个元素
   */
  def previous(): Unit

  /**
   * 判断是否为第一个元素
   * @return 是否为第一个元素
   */
  def isFirst: Boolean

  /**
   * 获取下一个元素
   * @return 下一个元素
   */
  def getNextItem: Any

  /**
   * 获取上一个元素
   * @return 上一个元素
   */
  def getPreviousItem: Any
}

/**
 * 抽象聚合类
 * @param objects 存放元素
 */
abstract class AbstractAnyArrayBuffer(objects: ArrayBuffer[Any]) {
  def addAny(any: Any) = objects += any

  def removeAny(any: Any) = objects -= any

  def getAny = this.objects

  def createIterator: AbstractIterator
}

/**
 * 商品聚合类
 * @param objects 存放元素
 */
class ProductArrayBuffer(objects: ArrayBuffer[Any]) extends AbstractAnyArrayBuffer(objects) {
  override def createIterator = new ProductIterator

  /**
   * 内部类实现
   */
  protected class ProductIterator extends AbstractIterator {
    /**
     * 正向遍历游标
     */
    private var cursor1: Int = 0

    /**
     * 反向遍历游标
     */
    private var cursor2: Int = objects.length - 1

    override def next(): Unit = {
      if (cursor1 < objects.length) {
        cursor1 += 1
      }
    }

    override def isLast: Boolean = cursor1 == objects.length

    override def previous(): Unit = {
      if (cursor2 > -1) {
        cursor2 -= 1
      }
    }

    override def isFirst: Boolean = cursor2 == -1

    override def getNextItem = objects(cursor1)

    override def getPreviousItem = objects(cursor2)

  }

}

/**
 * 不采用内部类
 * @param objects 存放元素
 */
class ProductArrayBuffer2(objects: ArrayBuffer[Any]) extends AbstractAnyArrayBuffer(objects) {
  override def createIterator = new ProductIterator2(this)
}

/**
 * 不采用内部类
 * @param productArrayBuffer2 商品聚合类
 */
class ProductIterator2(productArrayBuffer2: ProductArrayBuffer2) extends AbstractIterator {
  private val products = productArrayBuffer2.getAny
  private var cursor1: Int = 0
  private var cursor2: Int = products.length - 1

  override def next(): Unit = {
    if (cursor1 < products.length) {
      cursor1 += 1
    }
  }

  override def isLast: Boolean = cursor1 == products.length

  override def previous(): Unit = {
    if (cursor2 > -1) {
      cursor2 -= 1
    }
  }

  override def isFirst: Boolean = cursor2 == -1

  override def getNextItem = products(cursor1)

  override def getPreviousItem = products(cursor2)

}

object Iterator{
  def testIterator() {
    println("--------------迭代器模式：--------------")
    var products = new ArrayBuffer[Any]()

    products += "a"
    products += "b"
    products += "c"
    products += "d"
    products += "e"

    println("正向遍历")
    products.foreach(x => print(x + ", "))
    println()
    println("*********************")
    println("反向遍历")
    products.reverse.foreach(x => print(x + ", "))
    println()

    val arrayBuffer1: AbstractAnyArrayBuffer = new ProductArrayBuffer(products)

    arrayBuffer1.removeAny("a")
    arrayBuffer1.addAny("cc")

    val iterator1: AbstractIterator = arrayBuffer1.createIterator

    println("正向遍历")

    while (!iterator1.isLast) {
      print(iterator1.getNextItem + ", ")
      iterator1.next()
    }

    println()
    println("*********************")
    println("逆向遍历")
    while (!iterator1.isFirst) {
      print(iterator1.getPreviousItem + ", ")
      iterator1.previous()
    }

    println()

    val arrayBuffer2: AbstractAnyArrayBuffer = new ProductArrayBuffer2(products)

    arrayBuffer2.removeAny("b")
    arrayBuffer2.addAny("cca")

    val iterator2: AbstractIterator = arrayBuffer2.createIterator

    println("正向遍历")

    while (!iterator2.isLast) {
      print(iterator2.getNextItem + ", ")
      iterator2.next()
    }

    println()
    println("*********************")
    println("逆向遍历")
    while (!iterator2.isFirst) {
      print(iterator2.getPreviousItem + ", ")
      iterator2.previous()
    }
  }
}