package com.study.proxy.statical.demo.client;

import com.study.proxy.statical.demo.principal.OldDriver;
import com.study.proxy.statical.demo.proxyer.DogGroupOwner;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/14/10:56
 * @Desc:
 **/
public class Dream {


    public static void main(String[] args) {
        System.out.println("苟群员找苟群主发车。。");
        System.out.println("苟群主没车，去舔老司机，获取了车♂");

        DogGroupOwner dogGroupOwner = new DogGroupOwner(new OldDriver());

        System.out.println("苟群主开车啦（实际是老司机的车）");

        dogGroupOwner.drive();
    }
}
