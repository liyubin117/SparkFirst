package main.scala

object TraintDemo {
  def main(args:Array[String]){
    trait TraintA{def funA=println("TraintA")}
    trait TraintB{def funB=println("TraintB")}
    class AB extends TraintA with TraintB{def funAB=println("AB")}
    new AB().funAB
    new AB().funA
  } 
}