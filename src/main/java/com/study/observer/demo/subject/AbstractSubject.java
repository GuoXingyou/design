package com.study.observer.demo.subject;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.study.observer.demo.watcher.SeniorWatcher;
import com.study.observer.demo.watcher.VerneyWatcher;
import com.study.observer.demo.watcher.Watcher;

import java.util.List;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/14:00
 * @Desc:
 **/
public abstract class AbstractSubject {


    private List<Watcher> watchers = Lists.newArrayList();


    public void attach(Watcher watcher){
        this.watchers.add(watcher);
    }

    public void detach(Watcher target){

        this.watchers.forEach(watcher -> {
            if(target.getClass() == watcher.getClass()){
                this.watchers.remove(watcher);
            }
        });


    }

    public void show(){
        this.watchers.forEach(watcher -> watcher.show());
    }


    public void notifyWatcher(){
        this.watchers.forEach(watcher -> watcher.update(this.hashCode()));
    }
}
