package com.study.decorator.demo.progress;

import com.study.decorator.demo.cook.FryHandsCookies;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/13/17:34
 * @Desc: 火腿肉松饼 具体装饰者
 **/
public class HandsCookiesWithHam extends AbstractHandsCookies{
    public HandsCookiesWithHam(FryHandsCookies fryHandsCookies) {
        super(fryHandsCookies);
    }

    public void addHam(){
        System.out.println("添加火腿；");
    }

    @Override
    public void cookCookies() {
        super.cookCookies();
        addHam();
    }
}
