package com.study.factorymthod.demo.factory;

import com.study.factorymthod.demo.drift.DriveTool;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/15/10:44
 * @Desc: 开车工具工厂-生产工具
 **/
public interface DriveToolFactory {

    DriveTool generateTool();
}
