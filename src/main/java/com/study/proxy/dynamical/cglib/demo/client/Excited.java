package com.study.proxy.dynamical.cglib.demo.client;

import com.study.proxy.dynamical.cglib.demo.proxyer.CglibDingkangPeng;
import com.study.proxy.dynamical.jdk.demo.principal.Senior;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/14/13:25
 * @Desc:
 **/
public class Excited {


    public static void main(String[] args) {
        CglibDingkangPeng government = new CglibDingkangPeng();
        Senior senior = (Senior)government.hkBaseLaw(Senior.class);

        senior.appointAllBefore();
    }
}
