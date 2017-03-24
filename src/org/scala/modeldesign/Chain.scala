package org.scala.modeldesign

/**
 * 职责链模式，自我感觉和akka的管理机制很像
 */

/**
 * 审批对象
 * @param amount 金额
 * @param number 编号
 * @param purpose 采购目的
 */
case class PurchaseRequest(amount: Double, number: Int, purpose: String)

/**
 * 抽象处理者，审批者类
 * @param name 审批人姓名
 */
abstract class Approve(name: String) {

  /**
   * 定义后继对象
   */
  protected var successor: Approve = _

  /**
   * 设置后继对象
   * @param approve 审批者类
   */
  def setSuccessor(approve: Approve) = successor = approve

  /**
   * 抽象处理审批请求方法
   * @param request 审批对象
   */
  def processRequest(request: PurchaseRequest): Unit
}

/**
 * 主任类
 * @param name 审批人姓名
 */
class Director(name: String) extends Approve(name) {
  override def processRequest(request: PurchaseRequest) = request.amount match {
    case x: Double if x < 50000 =>
      println(s"主任$name 审批采购单：" + request.number + ",金额：" + request.amount + "元，采购目的：" + request.purpose)
    case _ => this.successor.processRequest(request)
  }
}

/**
 * 副董事长类
 * @param name 审批人姓名
 */
class VicePresident(name: String) extends Approve(name) {
  override def processRequest(request: PurchaseRequest) = request.amount match {
    case x: Double if x < 100000 =>
      println(s"副董事长$name 审批采购单：" + request.number + ",金额：" + request.amount + "元，采购目的：" + request.purpose)
    case _ => this.successor.processRequest(request)
  }
}

/**
 * 董事长类
 * @param name 审批人姓名
 */
class President(name: String) extends Approve(name) {
  override def processRequest(request: PurchaseRequest) = request.amount match {
    case x: Double if x < 500000 =>
      println(s"董事长$name 审批采购单：" + request.number + ",金额：" + request.amount + "元，采购目的：" + request.purpose)
    case _ => this.successor.processRequest(request)
  }
}

/**
 * 董事会类
 * @param name 审批人姓名
 */
class Congress(name: String) extends Approve(name) {
  override def processRequest(request: PurchaseRequest) =
    println("召开董事会审批采购单：" + request.number + ",金额：" + request.amount + "元，采购目的：" + request.purpose)
}