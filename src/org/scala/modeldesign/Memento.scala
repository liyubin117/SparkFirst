package org.scala.modeldesign
import scala.collection.mutable.ArrayBuffer
/**
 * 备忘录模式，在command模式中简单实现过一次
 * 原发器：象棋类
 * @param label 标签
 * @param x x坐标
 * @param y y坐标
 */
case class Chessman(var label: String, var x: Int, var y: Int) extends ChessmanFunction {


    /**
     * 保存状态
     * @return 备忘录
     */
    def save: ChessMemento = ChessMemento(this.label, this.x, this.y)

    /**
     * 恢复状态
     * @param chessMemento 备忘录
     */
    def restore(chessMemento: ChessMemento): Unit = {
        label = chessMemento.label
        x = chessMemento.x
        y = chessMemento.y
    }
}

/**
 * 备忘录样例类
 * @param label 标签
 * @param x x坐标
 * @param y y坐标
 */
case class ChessMemento(label: String, x: Int, y: Int)

/**
 * 下棋方法特质
 */
trait ChessmanFunction {
    /**
     * 存放多个备忘录
     */
    private var mementoArrayBuffer = new ArrayBuffer[ChessMemento]()
    /**
     * 定义初始位置索引
     */
    private var index = -1

    /**
     * 设置备忘录
     * @param memento 备忘录
     */
    def setMemento(memento: ChessMemento): Unit = mementoArrayBuffer += memento

    /**
     * 下棋
     * @param chessman 棋子
     */
    def play(chessman: Chessman): Unit = {
        setMemento(chessman.save)
        index += 1
        println(s"棋子${chessman.label} 当前位置为第${chessman.x}行，第${chessman.y}列")
    }

    /**
     * 悔棋
     * @param chessman 棋子
     */
    def undo(chessman: Chessman): Unit = {
        println("******悔棋******")
        index match {
            case -1 => println("已经在最初位置，无法撤销")
            case 0 => index -= 1
                println("已经在最初位置，无法撤销")
            case length: Int if length > 0 => index -= 1
                chessman.restore(mementoArrayBuffer(index))
                println(s"棋子${chessman.label} 当前位置为第${chessman.x}行，第${chessman.y}列")
            case _ => println("出现异常")
        }
    }


    /**
     * 撤销悔棋
     * @param chessman 棋子
     */
    def redo(chessman: Chessman): Unit = {
        println("******撤销悔棋******")
        index match {
            case -1 => index = 1
                chessman.restore(mementoArrayBuffer(index))
                println(s"棋子${chessman.label} 当前位置为第${chessman.x}行，第${chessman.y}列")
            case length: Int if -1 < length && length < mementoArrayBuffer.length - 1 => index += 1
                chessman.restore(mementoArrayBuffer(index))
                println(s"棋子${chessman.label} 当前位置为第${chessman.x}行，第${chessman.y}列")
            case length: Int if length >= mementoArrayBuffer.length - 1 => println("已经在最终位置，无法恢复")
            case _ => println(s"异常$index")
        }
    }


}