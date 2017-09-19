package com.study.observer.demo.watcher;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/14:25
 * @Desc:
 **/
public class SeniorWatcher implements Watcher {
    @Override
    public void update(int hash) {

        System.out.println("长者暗中观察:蛤丝念诗+1s");
        System.out.println("蛤丝id："  + hash);
    }

    @Override
    public void show() {
        System.out.println("长者");
    }
}
