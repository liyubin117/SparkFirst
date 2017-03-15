package org.scala

//单例对象
object Accounts {
  var accountId = 0;
  def newUniqueAccount() = {
    accountId+=1
    accountId
  }
}
//当出现同名class时，与之同名的object即是其伴生对象，存储其静态方法和静态变量
class Accounts(var balance:Double){
  val id=Accounts.newUniqueAccount()
  def deposit(amount:Double){balance+=amount} 
}

object ObjectDemo {
  def main(args: Array[String]) {
    println(Accounts.newUniqueAccount())
    println(Accounts.newUniqueAccount())
    println(Accounts.newUniqueAccount())
    
    var acc1=new Accounts(10000.5)
    acc1.deposit(5000)
    println(acc1.balance)
  }
}

