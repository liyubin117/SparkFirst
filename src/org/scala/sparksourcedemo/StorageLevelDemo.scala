package org.scala.sparksourcedemo

object StorageLevelDemo {
  def main(args: Array[String]) {
    val _useDisk = true
    val _useMemory = false
    val _useOffHeap = false
    val _deserialized = true

    def toInt: Int = {
      var ret = 0
      if (_useDisk) {
        ret |= 8
      }
      if (_useMemory) {
        ret |= 4
      }
      if (_useOffHeap) {
        ret |= 2
      }
      if (_deserialized) {
        ret |= 1
      }
      ret
    }
    println(Integer.toBinaryString(toInt)) //1001，二进制的9
  }

}