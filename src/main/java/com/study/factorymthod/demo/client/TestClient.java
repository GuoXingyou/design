package com.study.factorymthod.demo.client;

import com.study.factorymthod.demo.drift.DriveTool;
import com.study.factorymthod.demo.factory.DriveToolFactory;
import com.study.factorymthod.demo.factory.instance.BaiduYunDriveToolFactory;
import com.study.factorymthod.demo.factory.instance.MagnetDriveToolFactory;
import com.study.factorymthod.demo.factory.instance.TorrentDriveToolFactory;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/15/10:56
 * @Desc:
 **/
public class TestClient {


    public static void main(String[] args) {

        DriveToolFactory toolFactory;
        DriveTool tool;


        System.out.println("种子车准备！");

        toolFactory = new TorrentDriveToolFactory();//***修改处

        tool =toolFactory.generateTool();
        tool.drive();

        System.out.println("百度云车准备！");

        toolFactory = new BaiduYunDriveToolFactory();//***修改处

        tool =toolFactory.generateTool();
        tool.drive();

        System.out.println("磁悬浮车准备！");

        toolFactory = new MagnetDriveToolFactory();//***修改处

        tool =toolFactory.generateTool();
        tool.drive();


        //相比较于之前的简单工厂模式，工厂方法模式把生产工程的选择逻辑丢给了客户端；当需求增加的时候，
        //不用再修改简单工厂的工厂类逻辑，而是只做新增的工厂类和所需对象类，一定程度上遵循了ocp和srp，
        //修改的地方只存在于客户端（逻辑判断，即代码中标注的修改处）
    }
}
