package com.wsy.wsy_library.executor

import android.os.Handler
import android.os.Looper
import androidx.annotation.IntRange
import com.wsy.wsy_library.log.HiLog
import java.util.concurrent.*
import java.util.concurrent.atomic.AtomicLong
import java.util.concurrent.locks.Condition
import java.util.concurrent.locks.ReentrantLock

/**
 * 全局通用object
 * 支持按任务的优先级去执行,
 * 支持线程池暂停.恢复(批量文件下载，上传) ，
 * 异步结果主动回调主线程
 * todo 线程池能力监控,耗时任务检测,定时,延迟,
 *
 *
 */

object HiExecutor {
    private const val TAG: String = "Wsy-Executor-"
    private var isPaused: Boolean = false
    private var hiExecutor: ThreadPoolExecutor
    private var lock: ReentrantLock = ReentrantLock()
    private var pauseCondition: Condition
    private val mainHandler = Handler(Looper.getMainLooper());

    init {
        pauseCondition = lock.newCondition()

        val cpuCount = Runtime.getRuntime().availableProcessors()  //cpu

        //线程池中核心线程数量
        val corePoolSize = cpuCount + 1
        //最大能创建的线程数量
        val maxPoolSize = cpuCount * 2 + 1

        val blockingQueue: PriorityBlockingQueue<out Runnable> = PriorityBlockingQueue()

        //非核心线程最大存活时间
        val keepAliveTime = 30L
        //keepAliveTime的单位时间
        val unit = TimeUnit.SECONDS
        //使用原子类获取序列号
        val seq = AtomicLong()
        //线程创建工厂
        val threadFactory = ThreadFactory {
            val thread = Thread(it)
            //hi-executor-0
            thread.name = "Wsy-Executor-" + seq.getAndIncrement()
            return@ThreadFactory thread
        }

        hiExecutor = object : ThreadPoolExecutor(
            corePoolSize,
            maxPoolSize,
            keepAliveTime,
            unit,
            blockingQueue as BlockingQueue<Runnable>,
            threadFactory
        ) {
            override fun beforeExecute(t: Thread?, r: Runnable?) {
                if (isPaused) {
                    lock.lock()
                    try {
                        pauseCondition.await()
                    } finally {
                        lock.unlock()
                    }
                }
            }

            override fun afterExecute(r: Runnable?, t: Throwable?) {
                //监控线程池耗时任务,线程创建数量,正在运行的数量
                HiLog.e(TAG, "已执行完的任务的优先级是：" + (r as PriorityRunnable).priority)
            }
        }
    }

    @JvmOverloads
    fun execute(@IntRange(from = 0, to = 10) priority: Int = 0, runnable: Runnable) {
        hiExecutor.execute(PriorityRunnable(priority, runnable))
    }

    @JvmOverloads
    fun execute(@IntRange(from = 0, to = 10) priority: Int = 0, runnable: Callable<*>) {
        hiExecutor.execute(PriorityRunnable(priority, runnable))
    }

    abstract class Callable<T> : Runnable {
        override fun run() {
            mainHandler.post { onPrepare() }

            val t: T = onBackground();

            //移除所有消息.防止需要执行onCompleted了，onPrepare还没被执行，那就不需要执行了
            mainHandler.removeCallbacksAndMessages(null)
            mainHandler.post { onCompleted(t) }
        }

        open fun onPrepare() {
            //转菊花
        }

        abstract fun onBackground(): T
        abstract fun onCompleted(t: T)
    }

    class PriorityRunnable(val priority: Int, private val runnable: Runnable) : Runnable,
        Comparable<PriorityRunnable> {
        override fun compareTo(other: PriorityRunnable): Int {
            return if (this.priority < other.priority) 1 else if (this.priority > other.priority) -1 else 0
        }

        override fun run() {
            runnable.run()
        }

    }

    @Synchronized
    fun pause() {
        lock.lock()
        try {
            if (isPaused) return
            isPaused = true
        } finally {
            lock.unlock()
        }
        HiLog.e(TAG, "Wsy-Executor- is paused")
    }

    @Synchronized
    fun resume() {
        lock.lock()
        try {
            if (!isPaused) return
            isPaused = false
            pauseCondition.signalAll()
        } finally {
            lock.unlock()
        }
        HiLog.e(TAG, "Wsy-Executor- is resumed")
    }
}