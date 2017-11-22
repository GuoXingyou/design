package com.concurrence.demo;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/15/10:08
 * @Desc:
 **/
public class MyThread extends Thread {


    @Override
    public void run() {
        super.run();
        System.out.println("override my thread.");
    }

    public MyThread(Runnable target) {
        super(target);
    }

    public MyThread() {
    }
}
