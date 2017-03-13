package org.scala

//case 模式匹配
object CaseDemo {
  def simpleMatch(arg: Any) = arg match {
    case v: Int => "This is an Int"
    case v: (Int, String) => ("This is a (Int, String): " + v._1 + "," + v._2)
    case _ => ("Unknown type, print as is: " + _) //模糊匹配，匹配所有的情况，类似*  
  }

  def caseClassMatch(arg: Any) = arg match {
    case Dog(_) => arg
    case Cat(_) => arg
    case Horse(n, a, w) => w
  }

  def constantMatch(arg: Any) = arg match {
    case 3 => "This is an Int 3"
    case "3" => "This is a string 3"
    case true => "This is boolean true"
    case Nil => "This is a Nil"
    case null => "This is null"
  }

  def partialMatch(arg: List[_]) = arg match {
    case List(0, _, _) => "This is a list of length 3, and also starts with 0"
    case List(0, _*) => "This is a list of length not 3, and starts with 0: " + arg.size
  }

  def tupleMatch(arg: Any) = arg match {
    case (k, v) => "Tuple2: " + k + "," + v
    case (k, v, w) => "Tuple3: " + k + "," + v + "," + w
  }

  def typeMatch(arg: Any) = arg match {
    case v: Int => "Int:" + v
    case m: Map[_, _] => "Map:" + m.size
    case t: (_, _) => "Tuple2: " + t._1 + "," + t._2
    case _ => "Unknown type:" + arg
  }

  def typeEraseMatch(arg: Any) = arg match {
    case m: Map[Int, Int] => "Map from Int to Int:" + m.size
    case l: List[Int] => "Int List: " + l.size
    case t: (String, String) => "String tuple" + t._1
  }

  def arrayTypeEraseMatch(arg: Any) = arg match {
    case ia: Array[Int] => "Int Array: " + ia.length
    case sa: Array[String] => "String List: " + sa.length
  }

  def precedenceMatch(arg: Any) = arg match {
    case e: IllegalArgumentException => "This is an IllegalArgumentException"
    case e: Exception => "This is an Exception."
  }

  def optionMatch(arg: Option[_]) = arg match {
    case Some(s) => s
    case None => "?"
  }

  //等同于 abstract class Animal { /*empty body*/}  
  abstract class Animal

  //1. 无参的case class已经deprecated  
  //2. name默认成为case class的val field  
  //3. 无需new Cat("kitty")  
  //4.可以copy进行构造一个全新的case class，只有部分属性不同的情况很合适  
  //5. match/case匹配，match/case class匹配,=>用于分隔模式和模式匹配结果的表达式  
  //6. match/case匹配，match/case class匹配,case中的属性值，可以使用变量指代或者_指代  
  //7. case _ => expr，表达的是模糊匹配或者任意匹配  
  //8.常量匹配  
  //9. List匹配  
  //10. Tuple匹配  
  //11.类型匹配  
  //12. 集合元素的类型运行时擦除：List,Map,Tuple，不包括数组  
  //13. 数组元素类型运行时不檫除  
  //14.匹配优先级，类似switch/case+break  
  //15. Option  
  case class Cat(name: String) extends Animal

  case class Dog(name: String) extends Animal

  case class Horse(name: String, age: Int, weight: Double)

  def main(args: Array[String]) {
    println(Cat("kitty"))
    println(Cat("kitty").name)
    println(new Dog("carl"))
    val h1 = Horse("H", 8, 121.1)
    val h2 = h1.copy(name = "X")
    println(h2)

    println(simpleMatch(100))
    println(simpleMatch(100, "200"))

    println(caseClassMatch(Dog("Carl")))
    println(caseClassMatch(Horse("H", 11, 222.2)))

    //常量匹配  
    println(constantMatch(3))
    println(constantMatch(1 == 1))
    println(constantMatch("3"))
    println(constantMatch(null))
    println(constantMatch(Nil)) //Nil表示空List  

    //部分匹配  
    println(partialMatch(List(0, 1, 2)))
    println(partialMatch(List(0, 1, 2, 3)))

    //元组匹配  
    println(tupleMatch(1, 2))
    println(tupleMatch("One", "Tow", "Three"))

    //类型匹配  
    println(typeMatch(100))
    println(typeMatch(Map(1 -> 2, 2 -> 3)))
    println(typeMatch(("One", "Two")))
    println(typeMatch(("One", "Two", "Three"))) //Unknown type  

    //类型的运行时擦除：List,Map,Tuple  
    println(typeEraseMatch(Map(1 -> 1, 2 -> 2))) //匹配m成功  
    println(typeEraseMatch(Map("One" -> "One", "Two" -> "Two"))) //匹配m成功  

    println(typeEraseMatch(List(1, 2, 3))) //匹配l成功  
    println(typeEraseMatch(List("One", "Two", "Three"))) //匹配l成功  

    println(typeEraseMatch((1, 1))) //匹配t成功  
    println(typeEraseMatch(("One", "One"))) //匹配t成功  

    println(arrayTypeEraseMatch(Array(1, 2))) //匹配ia成功  
    println(arrayTypeEraseMatch(Array("One", "Two"))) //匹配sa成功  

    println(precedenceMatch(new IllegalArgumentException()))
    println(precedenceMatch(new NullPointerException()))

    //Option匹配  
    println(optionMatch(Map("One" -> "Two").get("One"))) //Two  
    println(optionMatch(Map("One" -> "Two").get("1"))) //?  
  }
}