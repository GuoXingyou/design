package com.study.factorymthod.demo.factory.instance;

import com.study.factorymthod.demo.drift.DriveTool;
import com.study.factorymthod.demo.drift.instance.MagnetDriveTool;
import com.study.factorymthod.demo.factory.DriveToolFactory;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/15/10:55
 * @Desc:
 **/
public class MagnetDriveToolFactory implements DriveToolFactory {
    @Override
    public DriveTool generateTool() {
        return new MagnetDriveTool();
    }
}
