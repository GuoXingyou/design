package com.study.adapter.demo.mine.client;

import com.google.common.collect.Lists;
import com.study.adapter.demo.mine.entity.StandardHath;
import com.study.adapter.demo.mine.inter.Hath;
import com.study.adapter.demo.mine.translate.HathAdapter;
import com.study.adapter.demo.mine.translate.HathAdapter2;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/12/10:42
 * @Desc:
 **/
public class Client {


    public static void main(String[] args) {

        Lists.newArrayList();



        /*场景：原本只是用内部资源，但是现在业务需求接入外部资源，但是由于内外标准不一致，数据不能直接使用，
        * 需要适配到我们自己内部的系统标准，产生了适配器类
        *
        * */

        //原本只用内部蛤丝标准
        Hath inHath = new StandardHath();
        System.out.println(inHath.inRed());

        System.out.println("===========================");


        //现在需要接入第三方蛤丝，适配外来蛤丝接口，膜法无界限
        Hath outHath = new HathAdapter();//只修改了这里别的地方不做修改
        System.out.println(outHath.inRed());

        System.out.println("===========================");

        Hath outHath2 = new HathAdapter2();
        System.out.println(outHath2.inRed());


    }
}
