package com.study.factorymthod.demo.drift.instance;

import com.study.factorymthod.demo.drift.DriveTool;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/15/10:48
 * @Desc:
 **/
public class BaiduYunDriveTool implements DriveTool {
    @Override
    public void drive() {
        System.out.println("百度云车！");
    }
}
