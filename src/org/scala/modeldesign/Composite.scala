package org.scala.modeldesign
import scala.collection.mutable.ArrayBuffer

/**
 * 组合模式
 * 抽象构件，透明组合模式
 */
trait AbstractFile {
    def add(abstractFile: AbstractFile) = println("对不起，不支持该方法")

    def remove(abstractFile: AbstractFile) = println("对不起，不支持该方法")

    def child(i: Int): AbstractFile = {
        println("对不起，不支持该方法")
        null
    }

    def killVirus(): Unit

}

/**
 * 图片文件样例类
 * @param name 文件名
 */
case class ImageFile(name: String) extends AbstractFile {
    override def killVirus(): Unit = println(s"------对图像$name 进行杀毒")
}

/**
 * 文本文件样例类
 * @param name 文件名
 */
case class TextFile(name: String) extends AbstractFile {
    override def killVirus(): Unit = println(s"------对文本文件$name 进行杀毒")
}

/**
 * 视频文件样例类
 * @param name 文件名
 */
case class VideoFile(name: String) extends AbstractFile {
    override def killVirus(): Unit = println(s"------对视频文件$name 进行杀毒")
}


/**
 * 文件夹样例类
 * @param name 文件夹名
 */
case class Folder(name: String) extends AbstractFile {
    /**
     * 队列，存放文件或文件夹
     */
    var fileList = new ArrayBuffer[AbstractFile]()


    /**
     * 插入
     * @param abstractFile 抽象构件
     */
    override def add(abstractFile: AbstractFile) = fileList += abstractFile


    /**
     * 删除
     * @param abstractFile 抽象构件
     */
    override def remove(abstractFile: AbstractFile) = fileList -= abstractFile

    /**
     * 查找孩子
     * @param i 索引
     * @return 子对象
     */
    override def child(i: Int) = fileList(i)

    /**
     * 递归调用
     */
    override def killVirus(): Unit = {
        println(s"******对文件夹$name 进行杀毒")
        for (file <- fileList) file.killVirus()
    }
}
