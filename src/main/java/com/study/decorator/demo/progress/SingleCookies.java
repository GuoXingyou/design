package com.study.decorator.demo.progress;

import com.study.decorator.demo.cook.FryHandsCookies;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/13/17:59
 * @Desc:
 **/
public class SingleCookies extends AbstractHandsCookies {
    public SingleCookies(FryHandsCookies fryHandsCookies) {
        super(fryHandsCookies);
    }

    public void fryCookies(){
        System.out.println("开始煎饼；");
    }

    @Override
    public void cookCookies() {
        super.cookCookies();
        fryCookies();
    }
}
