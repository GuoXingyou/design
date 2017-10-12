package com.study.observer.demo1.notifier;

import com.study.observer.demo1.observer.Observer;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/9/10:51
 * @Desc: 通知者服务
 **/
public interface Notifier {

    void doNotify();

    void doAttach(Observer observer);

    void doDetach(Observer observer);
}
