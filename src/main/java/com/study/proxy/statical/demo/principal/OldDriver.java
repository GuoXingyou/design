package com.study.proxy.statical.demo.principal;

import com.study.proxy.statical.demo.behavior.DriveCar;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/14/10:52
 * @Desc: 老司机
 **/
public class OldDriver implements DriveCar {
    @Override
    public void drive() {
        System.out.println("老司机开车，magnet:?xt=urn:btih:");
    }
}
