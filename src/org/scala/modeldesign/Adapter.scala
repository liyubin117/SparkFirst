package org.scala.modeldesign

/**
 * 适配器模式
 * 原有的快速排序和二分排序算法，和目标接口并不相符合
 */
/**
 * 抽象目标
 */
trait ScoreOperation {
  /**
   * 排序
   * @param array 数组
   * @return 排序的数组
   */
  def sort(array: Array[Int]): Array[Int]

  /**
   * 查找
   * @param array 数组
   * @param key 要查找的值
   * @return 1表示找到，-1表示未找到
   */
  def search(array: Array[Int], key: Int): Int
}

object QuickSort {
  /**
   * 快速排序算法
   * @param array 数组
   * @return 排序数组
   */
  def quickSort(array: Array[Int]): Array[Int] = {
    if (array.length <= 1) array
    else {
      val mid = array(array.length / 2)
      Array.concat(
        quickSort(array.filter(mid > _)),
        array.filter(mid == _),
        quickSort(array.filter(mid < _)))
    }
  }
}

object BinarySearch {
  /**
   * 二分查找
   * @param array 数组(数组为已经排好序的数组)
   * @param key 要找的值
   * @return 找到为1 否则为-1
   */
  def binarySearch(array: Array[Int], key: Int): Int = {
    var low = 0
    var high = array.length - 1
    while (low <= high) {
      val mid = (low + high) / 2
      val midVal = array(mid)
      if (midVal < key) {
        low = mid + 1
      } else if (midVal > key) {
        high = mid - 1
      } else {
        //不能直接 1 ，否则将进入死循环
        return 1
      }
    }
    -1
  }

}

/**
 * 适配器类
 * 具体使用了对象适配器模式
 */
object OperationAdapter extends ScoreOperation {
  //快速排序，适配者
  private val sortObj = QuickSort
  //二分查找，适配者
  private val searchObj = BinarySearch

  //调用适配者快速排序的快速排序方法，实现接口的方法
  override def sort(array: Array[Int]) = sortObj.quickSort(array)

  //调用适配者二分查找的二分查找方法，实现接口的方法
  override def search(array: Array[Int], key: Int) = searchObj.binarySearch(array, key)
}

object Adapter{
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
}