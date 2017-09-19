package com.study.observer.demo.watcher;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/14:26
 * @Desc:
 **/
public class VerneyWatcher implements Watcher {
    @Override
    public void update(int hash) {

        System.out.println("维尼熊暗中观察:蛤丝坐等查水表");
        System.out.println("蛤丝id："  + hash);
    }

    @Override
    public void show() {
        System.out.println("维尼熊");
    }
}
