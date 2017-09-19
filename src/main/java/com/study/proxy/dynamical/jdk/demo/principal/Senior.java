package com.study.proxy.dynamical.jdk.demo.principal;

import com.study.proxy.dynamical.jdk.demo.behavior.Appoint;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/14/11:16
 * @Desc: 长者
 **/
public class Senior implements Appoint{

    @Override
    public void appointAllBefore() {
        System.out.println("董先生连任特首；");
    }
}
