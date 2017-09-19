package com.study.decorator.demo.progress;

import com.study.decorator.demo.cook.FryHandsCookies;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/13/17:31
 * @Desc: 肉松手抓饼 具体装饰者
 **/
public class HandsCookiesWithMeatFloss extends AbstractHandsCookies {

    public HandsCookiesWithMeatFloss(FryHandsCookies fryHandsCookies) {
        super(fryHandsCookies);
    }

    public void addMeatFloss(){
        System.out.println("添加肉松；");
    }

    @Override
    public void cookCookies() {
        super.cookCookies();
        addMeatFloss();
    }
}
