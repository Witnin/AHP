package com.wsy.ahp.coroutine;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;


public class CountDownLatchDemo {
    private static final String TAG = "CountDownLatch";

    /**
     * CountDownLatch多人过山车，五个人都准备好了之后才能发车
     */
    public static void test() throws InterruptedException {
        final CountDownLatch downLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(new Random().nextInt(4000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    Log.e(TAG, Thread.currentThread().getName() + "准备好了");
                    downLatch.countDown();
                }
            }).start();
        }
        downLatch.await();
        Log.e(TAG, "所有人都准备好了，准备发车..." );
    }

    /**
     * Semphore多人游玩，同一时刻限流三人
     */
    public static void test2() throws InterruptedException {
        Semaphore semaphore = new Semaphore(3,true);
        for (int i = 0; i < 10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
//                        semaphore.acquire(2);
                        Log.e(TAG, Thread.currentThread().getName()+"获取到许可证，进去游玩了" );
                        Thread.sleep(new Random().nextInt(4000));
                        semaphore.release();
//                        semaphore.release(2);
                        Log.e(TAG, Thread.currentThread().getName()+"归还了许可证" );
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

}

