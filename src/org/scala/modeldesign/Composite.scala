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

object Composite{
    def testComposite() {
    println("--------------组合模式：--------------")
    /**
     * 文件夹
     */
    val folder1 = Folder("李宇彬的资料")
    val folder2 = Folder("图像资料")
    val folder3 = Folder("文本文件")
    val folder4 = Folder("视频资料")

    /**
     * 文件
     */
    val file1 = ImageFile("qq截图.jpg")
    val file2 = ImageFile("美图.png")
    val file3 = TextFile("solr.log")
    val file4 = TextFile("迭代.doc")
    val file5 = VideoFile("测试.avi")

    folder2.add(file1)
    folder2.add(file2)
    folder3.add(file3)
    folder3.add(file4)
    folder4.add(file5)
    folder1.add(folder2)
    folder1.add(folder3)
    folder1.add(folder4)

    folder1.remove(folder3)
    //    folder1.child(2).killVirus()
    /**
     * 对根文件夹杀毒，递归调用
     */
    folder1.killVirus()
  }
}
