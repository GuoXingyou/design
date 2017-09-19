package com.study.proxy.dynamical.jdk.demo.principal;

import com.study.proxy.dynamical.jdk.demo.behavior.Ask;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/14/17:21
 * @Desc:
 **/
public class Reporter implements Ask {


    @Override
    public void question() {
        System.out.println("说中央会通过一些手段干预香港的政治。");
    }
}
