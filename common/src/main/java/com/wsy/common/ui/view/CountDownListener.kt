package com.wsy.common.ui.view

interface CountDownListener {
    fun countDown(time:Long)

    fun isOverTime(isOverTime:Boolean)
}