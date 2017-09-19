package com.study.factorymthod.demo.drift.instance;

import com.study.factorymthod.demo.drift.DriveTool;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/15/10:47
 * @Desc:
 **/
public class TorrentDriveTool implements DriveTool {

    @Override
    public void drive() {
        System.out.println(".torrent种子车！");
    }
}
