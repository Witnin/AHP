package com.wsy.ahp.thread;

import android.util.Log;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic原子类操作
 */
public class AtomicDemo {

    private static final String TAG = "Atomic";

    public static void test() throws InterruptedException {
        AtomicTask task = new AtomicTask();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {

                    task.incrementVolatile();
                    task.incrementAtomic();
                }
            }
        };

        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
        Log.e(TAG, "原子类结果:" + task.atomicInteger.get());
        Log.e(TAG, "volatile结果:" + task.atomicInteger.get());

    }
    static class AtomicTask{
        AtomicInteger atomicInteger = new AtomicInteger();
        volatile int volatileCount = 0;
        void incrementAtomic() {
            atomicInteger.getAndIncrement();
        }
        void incrementVolatile() {
//            volatileCount++;
            volatileCount=volatileCount+1;
        }
    }

}
