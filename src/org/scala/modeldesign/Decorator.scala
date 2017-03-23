package org.scala.modeldesign

/**
 * 装饰器模式
 */
 /*被装饰器类规范
 * 抽象界面构件类
 */
trait Component {
    def display(): Unit
}

/**
 * 窗口具体构件类
 * 具体的被装饰器类
 */
class Window extends Component {
    override def display(): Unit = println("显示窗口")
}

/**
 * 文本框具体构件类
 * 具体的被装饰器类
 */
class TextBox extends Component {
    override def display(): Unit = println("显示文本框")
}

/**
 * 列表具体构件类
 * 具体的被装饰器类
 */
class ListBox extends Component {
    override def display(): Unit = println("显示列表框")
}

/**
 * 装饰器规范
 * 构件装饰样例类
 * 
 * @param component 抽象构件
 */
case class ComponentDecorator(component: Component) extends Component {
    /**
     * 复写display方法，调用抽象构建的方法
     */
    override def display(): Unit = component.display()
}

/**
 * 具体装饰器类
 * 滚动条装饰类
 * @param component 抽象构件
 */
class ScrollBarDecorator(component: Component) extends ComponentDecorator(component) {

    /**
     * 复写父类方法，在复写的方法中调用自己的独有方法
     */
    override def display() = {
        scrollBar()
        super.display()
    }

    /**
     * 自己独有方法
     */
    def scrollBar() = println("为构件增加滚动条")
}

/**
 * 具体装饰器类
 * 黑色边框装饰类
 * @param component 抽象构件
 */
class BlackBorderDecorator(component: Component) extends ComponentDecorator(component) {

    override def display() = {
        blackBorder()
        super.display()
    }

    def blackBorder() = println("为构件增加黑色边框")
}