package org.scala.modeldesign
import scala.collection.mutable.ArrayBuffer
//命令模式
/**
 * 加法类，请求接收者
 */
class Adder {
    /**
     * 计算器初始值
     */
    private var num = 0

    /**
     * 加法操作
     * @param value 传入值
     * @return 计算结果
     */
    def add(value: Int) = {
        num += value
        num
    }

}

/**
 * 抽象命令类
 */
abstract class AbstractCommand {
    /**
     * 命令执行方法
     * @param value 数值
     * @return
     */
    def execute(value: Int): Int

    /**
     * 命令撤销方法
     * @return 撤消后数值
     */
    def undo(): Int

    /**
     * 二次撤销方法
     * @return 二次撤销后数值
     */
    def redo(): Int
}

/**
 * 具体命令类
 */
class AddCommand extends AbstractCommand {
    /**
     * 值栈存放传入数值，用于进行undo
     */
    private val values = new ArrayBuffer[Int]()

    /**
     * 值栈存放undo数值，用于redo
     */
    private val valuesRedo = new ArrayBuffer[Int]()
    /**
     * 请求接收者
     */
    val adder = new Adder

    /**
     * 执行过程中将操作数放入值栈
     * @param value 数值
     * @return
     */
    override def execute(value: Int) = {
        values += value
        adder.add(value)
    }

    /**
     * 如果是栈底，则不可undo，返回初始的num
     * 否则进行撤销，从值栈取最后一个值并加上相反数，并设置temp用于后续的redo
     * @return 撤消后数值
     */
    override def undo() = {
        values.length match {
            case 0 => println("不可undo"); adder.add(0)
            case _ => val temp = values.remove(values.length - 1)
                valuesRedo += temp
                adder.add(-temp)
        }

    }

    /**
     * 如果是redo值栈为0，返回原值+0的方式获取原值，因为原值是私有对象，并不需要加入值栈
     * 否则取redo队列最后一个，加入值栈队列并执行加法
     * @return 二次撤销后数值
     */
    override def redo() = {
        valuesRedo.length match {
            case 0 => println("不可redo"); adder.add(0)
            case _ => val temp = valuesRedo.remove(valuesRedo.length -1)
                values += temp
                adder.add(temp)
        }
    }
}
/**
 * 命令上下文context
 * @param command 可接受实现了抽象命令的具体命令
 */
class CalculatorForm(command: AbstractCommand) {
    def compute(value: Int): Unit = {
        val i = command.execute(value)
        println(s"执行运算，运算结果为: $i")
    }

    def undo(): Unit = {
        val i = command.undo()
        println(s"执行undo，运算结果为: $i")

    }

    def redo(): Unit = {
        val i = command.redo()
        println(s"执行redo，运算结果为: $i")
    }
}

object Command{
  def testCommand() {
    println("--------------命令模式：--------------")
    val command: AbstractCommand = new AddCommand
    val form = new CalculatorForm(command)
    form.compute(10)
    form.compute(5)
    form.undo()
    form.undo()
    form.redo()
    form.redo()
    form.redo()
    form.undo()
    form.undo()
    form.undo()
    form.redo()
    form.compute(100)
  }
}