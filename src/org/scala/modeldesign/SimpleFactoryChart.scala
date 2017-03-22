package org.scala.modeldesign

/**
 * 简单工厂模式
 * 工厂方法模式的特例
 */
abstract  class Chart {
    def display():Unit
}

/**
 * 柱状图，继承抽象图
 */
class  HistogramChart extends  Chart{
    println("初始化柱状图")

    /**
     * 展示方法
     */
    override def display(): Unit = {
        println("显示柱状图")
    }
}

/**
 * 饼状图，继承抽象图
 */
class  PieChart extends  Chart{
    println("初始化饼状图")

    /**
     * 展示方法
     */
    override def display(): Unit = {
        println("显示饼状图")
    }
}


/**
 * 折线图，继承抽象图
 */
class LineChart extends  Chart{
    println("初始化折线图")

    /**
     * 展示方法
     */
    override def display(): Unit = {
        println("显示折线图")
    }
}


/**
 * 工厂方法，用于产出具体产品
 */
object  Chart {
    /**
     * 通过模式匹配更加优雅实现
     * @param style 产品类型
     * @return 具体产品
     */
    def apply(style:String) = style match {
        case "histogram" => println("创建化柱状图");new HistogramChart
        case "pie" => println("创建饼状图");new PieChart
        case "line" =>println("创建折线图"); new LineChart
    }
}