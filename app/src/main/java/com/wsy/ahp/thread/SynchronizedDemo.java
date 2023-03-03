package com.wsy.ahp.thread;

import java.util.ArrayList;
import java.util.List;

/**
 * 卖票
 *     /**
 *      * synchronized添加方法上，为了获取到对象锁的线程，只能排队，不能访问，准备好了就可以买
 *      *
 *      * synchronized添加代码块上，为了获取到对象锁的线程，可以访问同步代码块之外的代码，所有准备好了可以开始
 *      *
 *      * 加在static 方法上面，就相对是给CLass对象加锁，由于在m中只会存在一份Clas对象。
 *      * 所以此时无论是不是同-个iava对象，去访间同步访问，都只能排队
 *      */

/**
 * Synchronized的优势是什么呢?
 * 。哪怕我们一个同步方法中出现了异常，那么jvm也能够为我们自动释放锁，能主动从而规避死锁不需要开发者手动释放锁
 * 劣势是什么呢?
 * 。必须要等到获取锁对象的线程执行完成，或者出现异常，才能释放掉。不能中途释放锁，不能中断个正在试图获得锁的线程
 * 另外咱们也不知道多个线程竞争锁的时候，获取锁成功与否，所以不够灵活,每个锁仅有单一的条件(某个对象)不能设定超时0
 */
public class SynchronizedDemo {

    static List<String> tickets = new ArrayList<>();

    public static void test() {
        for (int i = 0; i < 5; i++) {
            tickets.add("票" + (i + 1));
        }
        sellTickets();

    }


    static void sellTickets() {
        final SynchronizedTestDemo testDemo = new SynchronizedTestDemo();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    testDemo.printThreadName(); //同一个对象实例
//                    new SynchronizedTestDemo().printThreadName();  //不同的对象实例
                }
            }).start();
        }

    }


    static class SynchronizedTestDemo {
//        static synchronized void printThreadName() {
         void printThreadName() {
            String name = Thread.currentThread().getName();
            System.out.println("买票人: " + name + "准备好了...");
                synchronized (this) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("买票人: " + name + "正在卖票" );
                }
                System.out.println("买票人: " + name + "买到的票是..." + tickets.remove(0));

            }

    }

}