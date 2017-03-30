package org.scala

//枚举
object EnumerationColor extends Enumeration{
  //val Red,Yellow,Green=Value
  val Red=Value(0,"Stop");//id为0，名称为Stop
  val Yellow=Value(10) //id为10，名称为Yellow
  val Green=Value("Go") //名称为Go
  

}

object EnumerationDemo extends App{
    for(c<-EnumerationColor.values){
      println(c.id+":\t"+c)
    }
    
    println(EnumerationColor(0))
    println(EnumerationColor.withName("Stop"))
    
    println(doWhat(EnumerationColor(10)))
  
  def doWhat(color:EnumerationColor.Value)={
    if(color==EnumerationColor.Red) "stop"
    else if(color==EnumerationColor.Yellow) "hurry up"
    else "go"
  }
}