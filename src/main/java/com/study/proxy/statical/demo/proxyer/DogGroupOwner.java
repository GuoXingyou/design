package com.study.proxy.statical.demo.proxyer;

import com.study.proxy.statical.demo.behavior.DriveCar;
import com.study.proxy.statical.demo.principal.OldDriver;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/14/10:54
 * @Desc: 伸手党苟群主
 **/
public class DogGroupOwner implements DriveCar {

    private OldDriver oldDriver;

    //聚合♂
    public DogGroupOwner(OldDriver oldDriver) {
        this.oldDriver = oldDriver;
    }

    @Override
    public void drive() {
        oldDriver.drive();
    }
}
