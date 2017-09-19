package com.study.factorymthod.demo.factory.instance;

import com.study.factorymthod.demo.drift.DriveTool;
import com.study.factorymthod.demo.drift.instance.BaiduYunDriveTool;
import com.study.factorymthod.demo.factory.DriveToolFactory;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/15/10:53
 * @Desc:
 **/
public class BaiduYunDriveToolFactory implements DriveToolFactory {
    @Override
    public DriveTool generateTool() {
        return new BaiduYunDriveTool();
    }
}
