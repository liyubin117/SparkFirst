package org.spark.scala
import org.apache.spark._

class IntAccumulatorParam extends AccumulatorParam[Int] {
  def zero(initialValue: Int): Int = {
    initialValue
  }
  def addInPlace(v1: Int, v2: Int): Int = {
    v1+v2
  }
  
  def IntAccumulatorParam(initialValue: Int){
    initialValue
  }
}