package com.wsy.ahp.thread;

import android.util.Log;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 利用ReetrantReadWriteLockDemo来实现在线文档查看和编辑的功能
 */
public class ReentrantReadWriteLockDemo {

    public static void test() {
        ReentrantReadWriteLockTask reentrantReadWriteLockTask = new ReentrantReadWriteLockTask();

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        reentrantReadWriteLockTask.read();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }

        for (int i = 0; i < 3; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        reentrantReadWriteLockTask.write();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
    }

    static class ReentrantReadWriteLockTask{

        private final ReentrantReadWriteLock.ReadLock readLock;

        private static final String TAG = "ReentrantReadWriteLock";
        private final ReentrantReadWriteLock.WriteLock writeLock;
        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLockTask(){
            readLock = reentrantReadWriteLock.readLock();
            writeLock = reentrantReadWriteLock.writeLock();
        }


        void read() throws InterruptedException {
            String name = Thread.currentThread().getName();
            try{
              readLock.lock();
                Log.e(TAG, "线程"+name+"正在读取数据。。。" );
                Thread.sleep(1000);
            }finally {
                readLock.unlock();
                Log.e(TAG, "线程"+name+"释放了读锁。。。" );
            }
        }

        void write() throws InterruptedException {
            String name = Thread.currentThread().getName();
            try{
                writeLock.lock();
                Log.e(TAG, "线程"+name+"正在写入数据。。。" );
                Thread.sleep(1000);
            }finally {
                writeLock.unlock();
                Log.e(TAG, "线程"+name+"释放了写锁。。。" );
            }
        }
    }
}
