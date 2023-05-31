package com.wsy.ahp.kotlin

import android.view.View
import java.util.*
import kotlin.jvm.JvmStatic

object KotlinBase {
    @JvmStatic
    fun main(args: Array<String>) {
//        println("你好KOtlin")
//        test6()
//
//        val list = listOf(1, 2, 3)
//        val result = list.sum {
//            println("it:${it}")
//        }
//        println("result${result}")
//
//        val listString = listOf("1", "2", "3")
//        val result2 = listString.toIntSum()(2)
//        println("result2:${result2}")
//
//        testClosure(22)(32) {
//            println("testClosure:${it}")
//        }
//
//        test11()
//
//        listeral()
//
//        Cat(11).eat()
//
//        println(Shop().isClose)
//        println(Shop().scroe)
//
//        var test = Test()
//        test.setUp()
//        test.test()
//
//        testDataUtil()
//        testStudent()

//        val count = "ABD21219./x222".lettersCount()
//        println(count)

        val randomLengthString = getRandomLengthString("qwer")
        println(randomLengthString)
        //-----------------main end--------------------------------

    }

    fun getRandomLengthString(str: String) = str * (1..20).random()


    /**
     * 字符串判空
     */
    fun testEmpty(str: String?){
        if(str.isNullOrEmpty()){
            println("isNullOrEmpty:${str.isNullOrEmpty()}")
        }else  if(str.isNullOrBlank()){
            println("isNullOrBlank:${str.isNullOrBlank()}")
        }
    }

    fun test(listString: List<String>, s: String) {
        println("无参数")
    }

    //lambda

    val test1 = { println("无参数") }
    //----------lambda---------------------

    fun test2(a: Int, b: Int): Int {
        return a + b
    }

    val test3: (Int, Int) -> Int = { a, b -> a + b }
    val test4 = { a: Int, b: Int -> a + b }

    //--------------it-----------------


    fun test5() {
        val arr = arrayOf(1, 3, 5, 7, 9)
        println(arr.filter { it < 5 }.component1())

    }

    //--------------下划线隐藏参数-----------------
    fun test6() {
        val map = mapOf("key1" to "value1", "key2" to "value2", "key3" to "value3")
        map.forEach { (key, value) ->
            println(value)
        }

        map.forEach { (_, value) ->
            println(value)
        }
    }

    /**
     * 函数求和
     */
    fun List<Int>.sum(callback: (Int) -> Unit): Int {
        var result = 0
        for (v in this) {
            result += v
            callback(v)
        }
        return result
    }

    /**
     * 函数作为返回值
     */
    fun List<String>.toIntSum(): (scale: Int) -> Float {
        println("第一层函数")
        return fun(scale): Float {
            var result = 0f
            for (v in this) {
                result += v.toInt() * scale
            }
            return result
        }
    }

    /**
     * 闭包： 需求: 实现一个接受一个testClosure方法，
     * 该方法要接受一个Int类型的v1参数，同时能够返回一个声明为(v2: Int，(Int) -> Unit)的函数，
     * 并且这个函数能够计算v1与v2的和
     */
    fun testClosure(v1: Int): (v2: Int, (Int) -> Unit) -> Unit {
        return fun(v2: Int, printer: (Int) -> Unit) {
            printer(v1 + v2)
        }
    }

    /**
     * 方法解构
     *
     */
    data class Result(val message: String, val code: Int)

    fun test11() {
        var result = Result(message = "操作成功！", code = 0)
        val (message, code) = result
        println("message:${message} code:${code}")
    }

    val fun1 = fun(x: Int, y: Int): Int { return x + y }


    fun listeral() {
        //定义了一个变量 temp，而该变量的类型就是(Int) -> Boolean
        var temp: ((Int) -> Boolean)? = null
        temp = { num -> (num > 10) }
        println("temp${temp(11)}")
    }


    /**
     * 构造方法-主构造方法、次构造方法
     */
    class KotlinClass constructor(name: String) {}

    class kotlinClass2(name: String) {}

    /**
     * 次构造方法
     */

    class KotlinClass3 constructor(name: String) {
        constructor(view: View, name: String) : this(name) {
            println("name:${name}")
        }

        constructor(view: View, name: String, index: Int) : this(name) {
            println("name:${name}")
        }
    }

    /**
     * 继承
     */
    open class Animal(age: Int) {
        init {
            println(age)
        }

        open val foot: Int = 0
        open fun eat() {
            println("eat:食物")
        }
    }

    class Dog(age: Int) : Animal(11) {}

    class Cat : Animal {
        val simple: Int?
            get() {
                return 1
            }

        constructor(age: Int) : super(age)

        override val foot = 4
        override fun eat() {
            println("eat:小鱼")
        }
    }

    /**
     * 属性的声明
     */
    class Shop {
        val name: String = "Android"
        var address: String? = null
        val isClose: Boolean
            get() = Calendar.getInstance().get(Calendar.HOUR_OF_DAY) > 11
        var scroe: Float = 0.0f
            get() = if (field < 0.2f) 0.2f else field * 1.5f
            set(value) {
                println(value)
            }
    }

    /**
     * 延迟初始化
     */
    class Test {
        lateinit var shop: Shop
        fun setUp() {
            shop = Shop()
            shop.address = "hangzhou"
        }

        fun test() {
            //::表示创建成员引用或者类引用
            if (::shop.isInitialized)
                println(shop.address)
        }
    }

    /**
     * 抽象方法
     */
    abstract class Printer {
        abstract fun print()
    }

    class FilePrinter : Printer() {
        override fun print() {
            println("print file")
        }

    }

    /**
     * 接口
     */
    interface Study {
        val time: Int //抽象的
        fun discuss()
        fun learnCourses() {
            println("Android 架构师")
        }
    }

    class StudyAs(override val time: Int) : Study {
        override fun discuss() {

        }

    }

    interface A {
        fun foo() {
            println("A")
        }
    }

    interface B {
        fun foo() {
            println("B")
        }
    }

    class C : A, B {
        override fun foo() {
            super<A>.foo()
        }

    }

    /**
     * 数据类，必须要有一个参数
     */
    data class Address(val name: String, val number: Int) {
        var city: String = ""
        open fun print() {
            println(city)
        }
    }

    open class Address2(name: String) {
        open fun print() {
            println("")
        }
    }

    class Shop2 {
        var address: Address2? = null
        fun addAddress(address2: Address2) {
            this.address = address2
        }
    }

    fun test3() {
        //如果超类型有一个构造方法，则必须传递适当的构造方法参数给他
        Shop2().addAddress(object : Address2("Android") {
            override fun print() {
                super.print()
            }
        })
    }

    fun foo() {
        val adHoc = object {
            var x: Int = 0
            var y: Int = 0
        }
        println(adHoc.x + adHoc.y)
    }

    object DataUtil {
        fun <T> isEmpty(list: ArrayList<T>): Boolean {
            return list?.isEmpty() ?: false
        }
    }

    private fun testDataUtil() {
        val list = arrayListOf("1")
        println(DataUtil.isEmpty(list))
    }

    //伴生对象
    class Student(val name:String){
        companion object{
            val student = Student("Android")
            fun study(){
                println("IOS")
            }
        }
    }

    private fun testStudent(){
        println(Student.student.name)
        Student.study()
    }
}