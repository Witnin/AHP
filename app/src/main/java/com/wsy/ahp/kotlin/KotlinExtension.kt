package com.wsy.ahp.kotlin

import android.app.Activity
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.view.View
import androidx.annotation.IdRes
import com.google.android.material.snackbar.Snackbar
import java.io.BufferedWriter
import java.io.IOException
import java.io.OutputStreamWriter

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

fun View.showSnackbar(text: String, actionText: String? = null,
                      duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, text, duration)
    if (actionText != null && block != null) {
        snackbar.setAction(actionText) {
            block()
        }
    }
    snackbar.show()
}
fun View.showSnackbar(resId: Int, actionResId: Int? = null,
                      duration: Int = Snackbar.LENGTH_SHORT, block: (() -> Unit)? = null) {
    val snackbar = Snackbar.make(this, resId, duration)
    if (actionResId != null && block != null) {
        snackbar.setAction(actionResId) {
            block()
        }
    }
    snackbar.show()
}

/**
 * kotlin泛型实化简化Intent跳转，传参
 *
 * startActivity<TestActivity>(context)


 * startActivity<TestActivity>(context) {
 * putExtra("param1", "data")
 * putExtra("param2", 123)
}
 */
inline fun <reified T> startActivity(context: Context) {
    val intent = Intent(context, T::class.java)
    context.startActivity(intent)
}

inline fun <reified T> startActivity(context: Context, block: Intent.() -> Unit) {
    val intent = Intent(context, T::class.java)
    intent.block()
    context.startActivity(intent)
}


fun cvOf(vararg pairs: Pair<String, Any?>): ContentValues {
    val cv = ContentValues()
    for (pair in pairs) {
        val key = pair.first
        val value = pair.second
        when (value) {
            is Int -> cv.put(key, value)
            is Long -> cv.put(key, value)
            is Short -> cv.put(key, value)
            is Float -> cv.put(key, value)
            is Double -> cv.put(key, value)
            is Boolean -> cv.put(key, value)
            is String -> cv.put(key, value)
            is Byte -> cv.put(key, value)
            is ByteArray -> cv.put(key, value)
            null -> cv.putNull(key)
        }
    }
    return cv
}

fun cvOf2(vararg pairs: Pair<String, Any?>) = ContentValues().apply {
    for (pair in pairs) {
        val key = pair.first
        val value = pair.second
        when (value) {
            is Int -> put(key, value)
            is Long -> put(key, value)
            is Short -> put(key, value)
            is Float -> put(key, value)
            is Double -> put(key, value)
            is Boolean -> put(key, value)
            is String -> put(key, value)
            is Byte -> put(key, value)
            is ByteArray -> put(key, value)
            null -> putNull(key)
        }
    }
}

fun SharedPreferences.open(block: SharedPreferences.Editor.() -> Unit) {
    val editor = edit()
    editor.block()
    editor.apply()
}

//fun save(inputText: String) {
//    try {
//        val output = openFileOutput("data", Context.MODE_PRIVATE)
//        val writer = BufferedWriter(OutputStreamWriter(output))
//        writer.use {
//            it.write(inputText)
//        }
//    } catch (e: IOException) {
//        e.printStackTrace()
//    }
//}

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
