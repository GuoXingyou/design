package com.study.factorymthod.demo.drift.instance;

import com.study.factorymthod.demo.drift.DriveTool;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/15/10:52
 * @Desc:
 **/
public class MagnetDriveTool implements DriveTool {
    @Override
    public void drive() {
        System.out.println("magnet:?xt=urn:btih磁链车！");
    }
}
