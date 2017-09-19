package com.study.facade.demo.client;

import com.study.facade.demo.continuelife.ContinueLife;
import com.study.facade.demo.continuelife.ContinueLifeImpl;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/18/16:12
 * @Desc:
 **/
public class TestClient {


    public static void main(String[] args) {
        ContinueLife continueLife = new ContinueLifeImpl();

        continueLife.doContinue();
        continueLife.doAvoid();


        //此处continueLife就是一个门面，他把read进行业务组装成两个更易使用的接口，客户端只需要根据业务进行判断使用
        //哪一个方法就好，而不用去后台再根据接口来拼装业务逻辑
    }
}
