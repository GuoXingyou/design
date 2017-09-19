package com.study.factorymthod.demo.factory.instance;

import com.study.factorymthod.demo.drift.DriveTool;
import com.study.factorymthod.demo.drift.instance.TorrentDriveTool;
import com.study.factorymthod.demo.factory.DriveToolFactory;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/15/10:56
 * @Desc:
 **/
public class TorrentDriveToolFactory implements DriveToolFactory {
    @Override
    public DriveTool generateTool() {
        return new TorrentDriveTool();
    }
}
