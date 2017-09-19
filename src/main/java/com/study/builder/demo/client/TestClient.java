package com.study.builder.demo.client;

import com.study.builder.demo.actor.Actor;
import com.study.builder.demo.director.Director;
import com.study.builder.demo.dresser.AvSearcherStyleDresser;
import com.study.builder.demo.dresser.HathStyleDresser;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/10:08
 * @Desc:
 **/
public class TestClient {


    public static void main(String[] args) {
        Director director = new Director();


        System.out.println("那年十八，人大开会，站着如喽啰...");
        Actor gentleMan = director.construct(new HathStyleDresser());
        gentleMan.showMe();


        System.out.println("大桥未久..");

        Actor searcher = director.construct(new AvSearcherStyleDresser());
        searcher.showMe();
    }
}
