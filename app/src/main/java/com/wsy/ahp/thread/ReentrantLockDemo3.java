package com.wsy.ahp.thread;

import android.util.Log;

import java.util.Random;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
*生产者，消费者，ReentrantLock condition条件对象，能够指定唤醒某个线程去工作
 *
 * 生产者：一个boss,生产砖，砖的序列号为奇数一号搬，序列号偶数二号工人搬
 */
public class ReentrantLockDemo3 {

    public static void test() {
        ReentrantLockTask reentrantLockTask = new ReentrantLockTask();
        new Thread( new Runnable() {
            @Override
            public void run() {

               while (true){
                   reentrantLockTask.work1();
               }
            }
        });

        new Thread( new Runnable() {
            @Override
            public void run() {

                while (true){
                    reentrantLockTask.work2();
                }
            }
        });
        for (int i = 0; i < 10; i++) {
            reentrantLockTask.boss();
        }
    }

    private static final String TAG = "ReentrantLock_condition";

    static class ReentrantLockTask{
        private Condition worker1Condition,worker2Condition;
        ReentrantLock reentrantLock = new ReentrantLock(true);

        volatile int flag = 0;//砖的序列号

        public ReentrantLockTask() {
            worker1Condition = reentrantLock.newCondition();
            worker2Condition = reentrantLock.newCondition();
        }
        void work1(){
            try {
                reentrantLock.lock();
                if(flag == 0 ||flag%2==0){
                    Log.e(TAG, "worker1无砖可搬" );
                  worker1Condition.await();
                }
                Log.e(TAG, "worker1 搬到的砖"+flag );
                flag = 0;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                reentrantLock.unlock();
            }
        }

        void work2(){
            try {
                reentrantLock.lock();
                if(flag == 0 ||flag%2!=0){
                    Log.e(TAG, "worker2无砖可搬" );
                    worker2Condition.await();
                }
                Log.e(TAG, "worker2 搬到的砖"+flag );
                flag = 0;


            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                reentrantLock.unlock();
            }
        }

        void boss(){
            try {
                reentrantLock.lock();
                flag = new Random().nextInt(100);
                if(flag%2==0){
                    worker2Condition.signal();
                    Log.e(TAG, "worker2搬砖："+flag );

                }else{
                    worker1Condition.signal();
                    Log.e(TAG, "worker1搬砖："+flag );

                }
            }finally {
                reentrantLock.unlock();
            }
        }
    }
}
