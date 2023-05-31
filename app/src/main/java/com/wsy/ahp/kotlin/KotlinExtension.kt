package com.wsy.ahp.kotlin

import android.app.Activity
import android.view.View
import androidx.annotation.IdRes

/**
 * kotlin扩展
 */
fun String.lettersCount(): Int {
    var count = 0
    for (char in this) {
        if (char.isLetter()) {
            count++
        }
    }
    return count
}

operator fun String.times(n: Int): String {
    val builder = StringBuilder()
    repeat(n) {
        builder.append(this)
    }
    return builder.toString()
}


fun main() {
//    val list = mutableListOf(1, 2, 3)
//    list.swap(0, 2)
//    println("list.swap(0,2):$list")
//
//    val listString = mutableListOf("A", "B", "C")
//    listString.swap2(0, 2)
//    println("listString.swap2(0,2):$listString")
//    println("lastChar:${"1,2,3,7".lastChar}")
//    Jump.print("1,2,3")
//
//    testRun(Room("hangzhou", 20f, 1.2f))
//    testApply()

    //---------------------main end------------------------------
}


fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

fun <T> MutableList<T>.swap2(index1: Int, index2: Int) {
    val temp = this[index1]
    this[index1] = this[index2]
    this[index2] = temp
}

/**
 * 扩展属性,获取字符串最后一个字符
 */
val String.lastChar: Char get() = this.get(this.length - 1)

class Jump {
    companion object {}

}

fun Jump.Companion.print(str: String) {
    println(str)
}

//* let
fun testLet(str: String?) {
    //避免为null
    str?.let {
        println(it.length)
    }
    //限制作用域
    str?.let {
        val str2 = "let作用域"
        println(it + str2)
    }
}

/**
 * run扩展-run函数只接收一个lambda函数为参数，
 * 以闭包形式返回，返回值为最后一行的值或者指定的return的表达式，
 * 在run函数中可以直接访问实例的公有属性和方法。
 */

data class Room(val address: String, val price: Float, val size: Float)

fun testRun(room: Room) {
    room.run {
        println("Room:$address,$price,$size")
    }

}

/**
 * apply函数的作用是: 调用某对象的apply函数，在函数范围内，
 * 可以任意调用该对象的任意方法，并返回该对象。
从结构上来看apply函数和run函数很像，
唯一不同点就是它们各自返回的值不-样，
run函数是以闭包形式返回最后一行代码的值，
而apply函数的返回的是传入对象的本身。
apply一般用于一个对象实例初始化的时候，
需要对对象中的属性进行赋值。
或者动态inflate出一个XML的View的时候需要给View绑定数据也会用到，
这种情景非常常见。
 */
fun testApply() {
    ArrayList<String>().apply {
        add("1")
        add("2")
        add("3")
        println("$this")
    }.let { println(it) }
}

/**
-案例:使用Kotlin扩展为控件绑定监听器减少模板代码**/
//为Activity添加find扩展方法，用于通过资源id获取控件
fun <T : View> Activity.find(@IdRes id: Int): T {
    return findViewById(id)
}

//为Int添加onClick扩展方法，用于为资源id对应的控件添加onclick监听
fun Int.onClick(activity: Activity, click: () -> Unit) {
    activity.find<View>(this).apply {
        setOnClickListener {
            click()
        }
    }
}
