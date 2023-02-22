package com.wsy.ahp.kotlin



object KotlinGeneric {

    @JvmStatic
    fun main(args: Array<String>) {
        println(Coke().taste().price)
        println(BlueColor(Blue()).printColor())
        test12()
    }

    interface Drinks<T> {
        fun taste(): T
        fun price(t: T)
    }

    class Sweet {
        val price = 5
    }

    class Coke : Drinks<Sweet> {
        override fun taste(): Sweet {
            println("Sweet")
            return Sweet()
        }

        override fun price(t: Sweet) {
            println("Coke price:${t.price}")
        }

    }

    /**
     * 泛型类
     */
    abstract class Color<T>(var t: T/*泛型字段*/) {
        abstract fun printColor()
    }
}

class Blue {
    val color = "Blue"
}

class BlueColor(t: Blue) : KotlinGeneric.Color<Blue>(t) {
    override fun printColor() {
        println("color:${t.color}")
    }
}

/**
 * 泛型方法
 */
//泛型方法
fun <T> fromJson(json: String, tClass: Class<T>): T? {
//    获取T的实例
    val t: T? = tClass.newInstance()
    return t
}

//泛型约束
fun <T : Comparable<T>?> sort(list: List<T>?): Unit {}



fun test12() {
    sort(listOf(1, 2, 3))
    // 0K，Int 是 Comparable<Int> 的子类型
    // sort(listof(Blue()))
    // 错误: Blue 不是 Comparable<Blue> 的子类型
    val listString = listOf("A", "B", "C")
    val list = test(listString, "A")
    println(list)
    //多个上界的情况

}

fun <T> test(list: List<T>, threshold: T): List<T>
        where T : CharSequence,
              T : Comparable<T> {

    return list.filter { it > threshold }.map { it }
}
