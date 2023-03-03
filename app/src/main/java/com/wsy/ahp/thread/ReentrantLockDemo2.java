package com.wsy.ahp.thread;

import android.util.Log;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 演示 多个线程 去打印纸张,每个线程 打印张(ReentrantLock 公平锁true，非公平锁false),非公平锁性能高于公平锁;
 * 公平锁：交易
 * 非公平锁：synchronized 比比皆是
 *
 * 公平锁与非公平锁
 * 公平锁，所有进入阻塞的线程排队依次均有机会执行
 * 默认非公平锁，允许线程插队，避免每一个线程都进入阻塞，再唤醒，性能高。因为线程可以插队，
 * 导致队列中可能会存在线程饿死的情况，一直得不到锁，一直得不到执行。
 */
public class ReentrantLockDemo2 {

    public static void test() {
        ReentrantLockTask reentrantLockTask = new ReentrantLockTask();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                reentrantLockTask.print();
            }
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

    private static final String TAG = "ReentrantLock2";

    static class ReentrantLockTask{
        ReentrantLock reentrantLock = new ReentrantLock(true);

        void print(){
            String name = Thread.currentThread().getName();
            try {
                reentrantLock.lock();
                Log.e(TAG, name+"第一次打印" );
                Thread.sleep(100);
                reentrantLock.unlock();

                reentrantLock.lock();
                Log.e(TAG, name+"第二次打印" );


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                reentrantLock.unlock();
            }
        }
    }
}
