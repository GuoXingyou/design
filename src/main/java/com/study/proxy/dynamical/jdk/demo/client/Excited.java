package com.study.proxy.dynamical.jdk.demo.client;

import com.study.proxy.dynamical.jdk.demo.behavior.Appoint;
import com.study.proxy.dynamical.jdk.demo.behavior.Ask;
import com.study.proxy.dynamical.jdk.demo.principal.Reporter;
import com.study.proxy.dynamical.jdk.demo.principal.Senior;
import com.study.proxy.dynamical.jdk.demo.proxyer.HkGovernment;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/14/11:38
 * @Desc:
 **/
public class Excited {

    public static void main(String[] args) {
        Appoint senior = new Senior();//实际钦定人 长者
        HkGovernment hkGovernment = new HkGovernment(senior); // 香港政府

        // 根据基本法。。（中央的代理，在香港政府动态生成，O..O）
        Appoint centerProxy = (Appoint)hkGovernment.hkBaseLaw();


        //董先生现在是特首，我们怎么能不兹词他啊？
        centerProxy.appointAllBefore();


        Ask reporter = new Reporter();
        HkGovernment pengdingkang = new HkGovernment(reporter);

        Ask zhangbaohua = (Ask)pengdingkang.hkBaseLaw();

        zhangbaohua.question();


        /*如果此处使用静态代理，我们就还需要去构造一个代理类，而此
        处使用一个 代理生成类HkGovernment 就可以构造*/
    }
}
