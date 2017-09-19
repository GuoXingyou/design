package com.study.observer.demo.subject;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/14:57
 * @Desc: 被观察的蛤丝
 **/
public class ObservedHath extends AbstractSubject {

    private String name;


    public void readPoetry(){
        System.out.println(this.name + "（" + this.hashCode() + "）开始念诗，苟...");

        notifyWatcher();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ObservedHath(String name) {
        this.name = name;
    }
}
