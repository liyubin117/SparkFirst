package org.scala.modeldesign
import scala.collection.mutable.ArrayBuffer
/**
 * 中介者模式，抽象中介者，通信软件
 */
abstract class AbstractSoftware(val name: String) {
  private var persons = new ArrayBuffer[Person]()

  /**
   * 增加用户
   * @param person 用户
   */
  def addPerson(person: Person): Unit = {
    if (persons.forall(!person.eq(_))) {
      println(s"群${name}增加${person.name}")
      persons += person
    } else {
      println(s"用户${person.name}已在群${name}内")
    }
  }

  /**
   * 删除用户
   * @param person 用户
   */
  def removePerson(person: Person): Unit = {
    if (persons.exists(person.eq(_))) {
      persons -= person
    } else {
      println("该用户已被删除")
    }
  }

  /**
   * 发放通知
   * @param person 发起者
   * @param message 信息
   */
  def notify(person: Person, message: String): Unit = {
    if (persons.exists(person.eq(_))) {
      persons.filter(!person.eq(_)).foreach(p => println(s"${p.name}从${person.name}接收到信息:$message"))
    } else {
      println(s"${person.name}您已经不在群组:$name")
    }

  }

  /**
   * 私聊
   * @param send 发送者
   * @param receive 接收者
   * @param message 信息
   */
  def pm(send: Person, receive: Person, message: String): Unit = send match {
    case p if persons.exists(p.eq(_)) => receive match {
      case r if persons.exists(r.eq(_)) => println(s"${send.name}发送信息:$message 给${receive.name}")
      case _ => println(s"接收者${receive.name}没有获得该软件的许可")
    }
    case _ => println(s"发送者${send.name}没有获得该软件的许可")
  }
}

/**
 * qq，具体中介者
 * @param name 群名
 */
class QQSoftware(name: String) extends AbstractSoftware(name) {
  override def notify(person: Person, message: String): Unit = {
    println(s"这里是qq群:$name")
    super.notify(person, message)
  }

  override def pm(send: Person, receive: Person, message: String): Unit = {
    println(s"使用qq软件进行私聊")
    super.pm(send, receive, message)
  }

}

/**
 * msn，具体中介者
 * @param name 群名
 */
class MSNSoftware(name: String) extends AbstractSoftware(name) {
  override def notify(person: Person, message: String): Unit = {
    println(s"这里是msn群:$name")
    super.notify(person, message)
  }

  override def pm(send: Person, receive: Person, message: String): Unit = {
    println(s"使用msn软件进行私聊")
    super.pm(send, receive, message)
  }

}

/**
 * 抽象同事类
 * @param name 名称
 */
abstract class Person(val name: String) {
  /**
   * 设置群组
   * @param software 群组
   */
  def setAbstractSoftware(software: AbstractSoftware)

  /**
   * 发言
   * @param message 信息
   */
  def speak(message: String): Unit

  /**
   * 删除用户
   * @param person 用户
   */
  def remove(person: Person): Unit

  /**
   * 增加用户
   * @param person 用户
   */
  def add(person: Person): Unit

  /**
   * 私聊
   * @param person 接收者
   * @param message 信息
   */
  def privateChat(person: Person, message: String): Unit
}

/**
 * 管理员角色，属于同事
 * @param name 名称
 */
class Admin(name: String) extends Person(name) {
  private var abstractSoftware: AbstractSoftware = null

  def setAbstractSoftware(software: AbstractSoftware) = abstractSoftware = software

  override def speak(message: String) = abstractSoftware.notify(this, message)

  /**
   * 删除
   * @param person 用户
   */
  def remove(person: Person) = abstractSoftware.removePerson(person)

  /**
   * 增加
   * @param person 用户
   */
  def add(person: Person) = {
    println(s"${name}进行添加用户${person.name}的操作")
    abstractSoftware.addPerson(person)
  }

  /**
   * 私聊
   * @param person 接收者
   * @param message 信息
   */
  def privateChat(person: Person, message: String) = abstractSoftware.pm(this, person, message)
}

/**
 * 普通用户角色，属于同事
 * @param name 名称
 */
class Member(name: String) extends Person(name) {
  private var abstractSoftware: AbstractSoftware = null

  def setAbstractSoftware(software: AbstractSoftware) = abstractSoftware = software

  override def speak(message: String) = abstractSoftware.notify(this, message)

  /**
   * 增加用户
   * @param person 用户
   */
  override def add(person: Person): Unit = {
    println(s"${name}您不是管理员，不具备增加用户权限")

  }

  /**
   * 判断是否为删除自己，如果是删除自己则为退群
   * @param person 用户
   */
  override def remove(person: Person): Unit = {
    if (person.eq(this)) {
      println(s"$name，您将退出${abstractSoftware.name}")
      abstractSoftware.removePerson(person)
    } else {
      println(s"${name}您不是管理员，不具备删除用户权限")
    }
  }

  /**
   * 私聊
   * @param person 接收者
   * @param message 信息
   */
  def privateChat(person: Person, message: String) = abstractSoftware.pm(this, person, message)
}

object Mediator {
  def testMediator() {
    println("--------------中介者模式：--------------")
    val admin: Person = new Admin("admin")
    val member1: Person = new Member("member1")
    val member2: Person = new Member("member2")
    val member3: Person = new Member("member3")
    val member4: Person = new Member("member4")

    val qqSoftware: AbstractSoftware = new QQSoftware("研发中心")
    admin.setAbstractSoftware(qqSoftware)
    member1.setAbstractSoftware(qqSoftware)
    member2.setAbstractSoftware(qqSoftware)
    member3.setAbstractSoftware(qqSoftware)
    member4.setAbstractSoftware(qqSoftware)

    admin.add(admin)
    admin.add(member1)
    admin.add(member2)
    admin.add(member3)
    admin.add(member4)
    admin.add(member1)

    admin.speak("hello")
    admin.remove(member1)

    member1.speak("hi")

    member2.add(member1)
    member2.remove(member2)

    member2.speak("admin")

    member3.privateChat(admin, "你好")
    member3.privateChat(member2, "你好")

    member2.privateChat(admin, "加我")

    println("*****************************")

    val msnSoftware: AbstractSoftware = new MSNSoftware("通研事业部")
    admin.setAbstractSoftware(msnSoftware)
    member1.setAbstractSoftware(msnSoftware)
    member2.setAbstractSoftware(msnSoftware)
    member3.setAbstractSoftware(msnSoftware)
    member4.setAbstractSoftware(msnSoftware)

    admin.add(admin)
    admin.add(member1)
    admin.add(member2)
    admin.add(member3)
    admin.add(member4)
    admin.add(member1)

    admin.speak("hello")
    admin.remove(member1)

    member1.speak("hi")

    member2.add(member1)

    member2.speak("admin")

    member2.privateChat(member3, "test")
  }
}
