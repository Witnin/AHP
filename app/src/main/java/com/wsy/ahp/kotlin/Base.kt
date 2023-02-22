package com.wsy.ahp.kotlin

import kotlin.jvm.JvmStatic
import com.wsy.ahp.kotlin.Base

object Base {
    @JvmStatic
    fun main(args: Array<String>) {
        baseType()
    }

    private fun baseType() {
        val num1 = 1
        val num2 = 1.6
        val num3 = 2.5f
        val array = Array(4) { i -> (i * i) }
        println("num1:$num1")
        println("num2:$num2")
        println("num3:$num3")
        println("array:${array[0]}")
        println("array:${array[1]}")
        println("array:${array[2]}")
        println("array:${array[3]}")
        println("array:${array.size}")
    }
}