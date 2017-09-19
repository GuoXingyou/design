package com.study.decorator.demo.chef;

import com.study.decorator.demo.cook.FryHandsCookies;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/13/17:39
 * @Desc: 厨师 被装饰者
 **/
public class Chef implements FryHandsCookies {
    @Override
    public void cookCookies() {
        System.out.println("吃个啥饼？");
    }

    @Override
    public void finish() {
        System.out.println("你要的饼。");
    }

}
