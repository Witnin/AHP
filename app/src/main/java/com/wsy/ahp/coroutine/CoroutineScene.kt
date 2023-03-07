package com.wsy.ahp.coroutine

import android.util.Log
import kotlinx.coroutines.*

/**
 * kotlin协程测试
 */
 object CoroutineScene {
    private val TAG: String = "CoroutineScene"

    /**
     * 以此启动三个子线程，并且同步的方式拿到他们的返回值，进而更新UI*/
     fun startScene1() {
        GlobalScope.launch(Dispatchers.Main) {
            Log.e(TAG, "coroutine is running")
            val result1 = request1()
            val result2 = request2(result1);
            val result3 = request3(result2);

            updateUI(result3)
        }
        Log.e(TAG, "coroutine has launched")
    }

    /**
     * 启动一个线程，先执行request1,然后同时运行request2,request3,这两并发结束后进而更新UI*/
    fun startScene2() {
        GlobalScope.launch(Dispatchers.Main) {
            Log.e(TAG, "coroutine is running")
            val result1 = request1()
           val deferred2 = GlobalScope.async { request2(result1) }
           val deferred3 = GlobalScope.async { request3(result1) }

            updateUI(deferred2.await(),deferred3.await())
        }
        Log.e(TAG, "coroutine has launched")
    }

    private fun updateUI(result2: String, result3: String) {
        Log.e(TAG, "updateUI work on ${Thread.currentThread().name} ")
        Log.e(TAG, "paramter:$result2------$result3")
    }

    private fun updateUI(result3: String) {
        Log.e(TAG, "updateUI work on ${Thread.currentThread().name} ")
        Log.e(TAG, "paramter:$result3")
    }

    //suspend关键字的作用--->
    //delay既是IO异步任务，是如何做到延迟协程中的代码向下执行的？
    private suspend fun request1(): String {
        delay(2 * 1000)  //不会暂停线程，但是会暂停当前所在的协程
        Log.e(TAG, "request1 work on ${Thread.currentThread().name} ")
        return "result from request1"
    }

    //suspend关键字的作用--->
    private suspend fun request2(result1: String): String {
        delay(2 * 1000)
        Log.e(TAG, "request2 work on ${Thread.currentThread().name} ")
        return "result from request2"
    }

    //suspend关键字的作用--->
    private suspend fun request3(result2: String): String {
        delay(2 * 1000)
        Log.e(TAG, "request3 work on ${Thread.currentThread().name} ")
        return "result from request3"
    }
}