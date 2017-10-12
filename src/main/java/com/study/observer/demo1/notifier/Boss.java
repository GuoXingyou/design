package com.study.observer.demo1.notifier;

import com.google.common.collect.Lists;
import com.study.observer.demo1.observer.Observer;

import java.util.List;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/9/10:57
 * @Desc:
 **/
public class Boss implements Notifier {

    private List<Observer> observers = Lists.newArrayList();

    @Override
    public void doNotify() {

    }

    @Override
    public void doAttach(Observer observer) {

    }

    @Override
    public void doDetach(Observer observer) {

    }
}
