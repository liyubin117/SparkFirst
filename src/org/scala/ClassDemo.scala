package org.scala

class Counter {
  private var value = 0
  //对于无参方法可不加括号，建议对于改值器方法加括号，取值器方法不加括号
  def increment() { value += 1 }
  def current = value
}

class Person {
  var age = 26
  //对于private[this]声明的属性，不生成方法
  private[this] var name = "li"
}
//使用javap -private Person.class，反编译后的结果
//public class Person {
//  private int age;
//  private java.lang.String name;
//  public int age();
//  public void age_$eq(int);
//  public Person();
//}

//主 副构造器
class Employee(name: String) {
  println("name:" + name)
  def this(name: String, age: Int) {
    this(name)
    println("name:" + name + " " + "age:" + age)
  }
  def this(name: String, age: Int, addr: String) {
    this(name, age)
    println("name:" + name + " age:" + age + " addr:" + addr)
  }
  //无val或var的参数被一个或多个非构造器方法使用则升格为字段
  def out() {
    println(name)
  }

}
//public class Employee extends java.lang.Object{
//    private final java.lang.String name;
//    public void out();
//    public Employee(java.lang.String);
//    public Employee(java.lang.String, int);
//    public Employee(java.lang.String, int, java.lang.String);
//}

object ClassDemo {
  def main(args: Array[String]) {
    //可加括号或不加括号
    val p = new Person
    p.age = 27 //调用的是p.age_$eq(27)方法
    println(p.age) //调用的是p.age方法

    var e = new Employee("liyubin", 26)
    var e2 = new Employee("ye", 24, "HZ")

  }
}