package com.wsy.ahp.thread;

import android.util.Log;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 多个线程去竞争锁的用法，ReentrantLock有效避免死锁
 */
public class ReentrantLockDemo {

    public static void test() {
        ReentrantLockTask reentrantLockTask = new ReentrantLockTask();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                reentrantLockTask.buyTicket();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

    private static final String TAG = "ReentrantLock";

    static class ReentrantLockTask{
        ReentrantLock reentrantLock = new ReentrantLock();

        void buyTicket(){
            String name = Thread.currentThread().getName();
            try {
                reentrantLock.lock();
                Log.e(TAG, name+"准备好了" );
                Thread.sleep(100);
                Log.e(TAG, name+"买好了" );

                reentrantLock.lock();
                Log.e(TAG, name+"二次准备好了" );
                Thread.sleep(100);
                Log.e(TAG, name+"二次买好了" );

                reentrantLock.lock();
                Log.e(TAG, name+"三次准备好了" );
                Thread.sleep(100);
                Log.e(TAG, name+"三次买好了" );
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                reentrantLock.unlock();
                reentrantLock.unlock();
                reentrantLock.unlock();
            }
        }
    }
}
