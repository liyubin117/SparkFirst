package main.scala

object ForDemo {
  def main(args:Array[String]){
    //支持嵌套
    for(i<-1 to 3;j<-2 to 3){
      println(i+" "+j)
    }
    //等效
    for(i<-1 to 3){
      for(j<-2 to 3){
        println(i+" "+j)
      }
    }
    
    //支持if判断
    for(i<-1 to 3;if(i!=3);if(!(i==2))){
      println(i)
    }
  }
}