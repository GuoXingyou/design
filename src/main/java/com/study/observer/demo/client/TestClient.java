package com.study.observer.demo.client;

import com.study.observer.demo.subject.AbstractSubject;
import com.study.observer.demo.subject.ObservedHath;
import com.study.observer.demo.watcher.SeniorWatcher;
import com.study.observer.demo.watcher.VerneyWatcher;
import com.study.observer.demo.watcher.Watcher;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/15:04
 * @Desc:
 **/
public class TestClient {

    public static void main(String[] args) {
        ObservedHath xiaoMin = new ObservedHath("小民");
        System.out.println("小民上了youtube，进入新蛤社学习人生经验...");

        Watcher ha = new SeniorWatcher();
        xiaoMin.attach(ha);
        System.out.println("小民被蛤派暗中观察...");

        Watcher xi = new VerneyWatcher();
        xiaoMin.attach(xi);
        System.out.println("小民被蜥派暗中观察...");

        xiaoMin.readPoetry();

    }
}
