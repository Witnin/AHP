package com.wsy.ahp.thread;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;

public class ThreadTest {

    private static final String TAG = "MyAsyncTask";
    private static final int MSG_WHAT_FLAG_1 = 1;
    private static volatile boolean hasNotify = false;

    public static void test() throws InterruptedException {

        class MyAsyncTask extends AsyncTask<String, Integer, String> {

            @Override
            protected String doInBackground(String... strings) {
                for (int i = 0; i < 10; i++) {
                    publishProgress(i * 10);

                }
                return strings[0];
            }

//            @Override
//            protected void onPostExecute(String result) {
//                Log.e(TAG, "onPostExecute:" + result);
//            }
//
//            @Override
//            protected void onProgressUpdate(Integer... values) {
//                Log.e(TAG, "onProgressUpdate:" + values[0].intValue());
//            }
        }
//        //适用于需要知道任务执行进度并更新UI的场景
//        MyAsyncTask asyncTask = new MyAsyncTask();
//        asyncTask.execute("execute MyAsyncTask");
//
        //
        //      asyncTask.execute0nExecutor(AsyncTask.THREAD_POOL_EXECUTOR,"execute MyAsyncTask");
//        //串行执行
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                Log.e(TAG, "run: AsyncTask execute");
//            }
//        });
//
//        for (int i = 0; i < 10; i++) {
//            AsyncTask.execute(new Runnable() {
//                @Override
//                public void run() {
//                    Log.e(TAG, "run: " + System.currentTimeMillis());
//                }
//
//            });
//        }
        //内置线程池THREAD_POOL_EXECUTOR，并发执行
//        for (int i = 0; i < 10; i++) {
//            AsyncTask.THREAD_POOL_EXECUTOR.execute(new Runnable() {
//                @Override
//                public void run() {
//                    Log.e(TAG, "run: " + System.currentTimeMillis());
//                }
//
//            });
//        }

        //适用于主线程需要和子线程通信的场景，
        //应用于持续性任务，比如轮询
//        HandlerThread thread = new HandlerThread("concurrent-thread");
//        thread.start();
//        ThreadHandler handler = new ThreadHandler(thread.getLooper());
//
//
//        handler.sendEmptyMessage(MSG_WHAT_FLAG_1);
//        thread.quitSafely();
//wait-notify适用于多线程同步，一个线程需要等待另一个线程的执行结果，或者部分结果
//------------------保证wait先执行，notify后执行,添加等待时间或可配合volite使用-----------------------------------
//        final Object object = new Object();
//
//        class Runnable1 implements Runnable {
//            @Override
//            public void run() {
//                Log.e(TAG, "run: thread1 start");
//                synchronized (object) {
//                    try {
//                        if(!hasNotify){
//                            object.wait(1000);
//                        }
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Log.e(TAG, "run: thread1 end");
//            }
//        }
//
//        class Runnable2 implements Runnable {
//            @Override
//            public void run() {
//                Log.e(TAG, "run: thread2 start");
//                synchronized (object) {
//                    object.notify();
//                    hasNotify = true;
//                }
//                Log.e(TAG, "run: thread2 end");
//            }
//        }
//
//        Thread thread1 = new Thread(new Runnable1());
//        Thread thread2 = new Thread(new Runnable2());
//        thread1.start();
//        thread2.start();
//-----------------------------------------------------

//---------------------join等待目标函数执行完后再执行-----------------------------------
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                Log.e(TAG,"run1:"+System.currentTimeMillis());
//                try {
//                    Thread.sleep(1000);}
//                catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                    Log.e(TAG,"run2:"+System.currentTimeMillis());
//            }
//        });
//
//        thread.start();
//        thread.join();
//        Log.e(TAG,"test:"+System.currentTimeMillis());

        //---------------------------join end------------------------


//------------------sleep-----------------------------------
//        final Object object = new Object();
//
//        class Runnable1 implements Runnable {
//            @Override
//            public void run() {
//                Log.e(TAG, "run: thread1 start");
//                synchronized (object) {
//                    try {
//                     Thread.sleep(1000);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                Log.e(TAG, "run: thread1 end");
//            }
//        }
//
//        class Runnable2 implements Runnable {
//            @Override
//            public void run() {
//                synchronized (object){
//                    Log.e(TAG, "run: thread2 start");
//                    Log.e(TAG, "run: thread2 end");
//                }
//
//            }
//        }
//
//        Thread thread1 = new Thread(new Runnable1());
//        thread1.start();
//        Thread thread2 = new Thread(new Runnable2());
//        thread2.start();
//-----------------------------------------------------

        //-----------------LooperThread主线程向子线程发送消息------------------------------------
        class LooperThread extends Thread {
            private Looper looper;

            public LooperThread(String name){
                super(name);
            }

            public Looper getLooper() {
                synchronized (this) {
                    if (looper == null && isAlive()) {
                        try {
                            wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                return looper;
            }

            @Override
            public void run() {
                Looper.prepare();
                synchronized (this) {
                    looper = Looper.myLooper();
                    notifyAll();
                }
                Looper.loop();
            }

        }

        LooperThread looperThread = new LooperThread("LooperThread");
        looperThread.start();
        Handler handler = new Handler(looperThread.getLooper()) {
            @Override
            public void handleMessage(@NonNull Message msg) {
                super.handleMessage(msg);
                Log.e(TAG, "handleMessage: " + msg.what);
                Log.e(TAG, "handleMessage:" + Thread.currentThread().getName());
            }
            };
            handler.sendEmptyMessage(MSG_WHAT_FLAG_1);


    }

//    //定义成静态，防止内存泄漏
//    static class ThreadHandler extends Handler {
//        public ThreadHandler(Looper looper) {
//            super(looper);
//        }
//
//        @Override
//        public void handleMessage(@NonNull Message msg) {
//            super.handleMessage(msg);
//            Log.e(TAG, "handleMessage: " + msg.what);
//            Log.e(TAG, "handleMessage: " + Thread.currentThread().getName());
//
//        }
//    }

}
