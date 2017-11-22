package com.concurrence.demo;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/14/15:30
 * @Desc:
 **/
public class ConcurrencyTest {
    private static final long count = 10000l;

    public static void main(String[] args) throws InterruptedException {
        serial();

        concurrency();

        MyThread myThread = new MyThread();//利用继承的子对象创建线程对象，缺点无法再继承其余类

        Thread thread = new Thread(new MyRunnable());//分开定义一个runnable并作为构造参数引入

    }

    private static void serial(){
        long start = System.currentTimeMillis();
        int a = 0;
        for(long i = 0;i <= count; i++){
            a += 5;
        }
        int b = 0;
        for(long i = 0;i <= count; i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;

        System.out.println("serial:" + time+"ms, b=" + b + ", a=" + a);

    }

    private static void concurrency() throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread = new Thread(() -> {
            int a = 0;
            for(long i = 0;i <= count; i++){
                a += 5;
            }
        });


        thread.start();
        int b = 0;
        for(long i = 0;i <= count; i++){
            b--;
        }
        long time = System.currentTimeMillis() - start;
        thread.join();

        System.out.println("concurrency:" + time+"ms, b=" + b);

    }

}
