package com.study.decorator.demo.progress;

import com.study.decorator.demo.cook.FryHandsCookies;

import javax.sound.midi.Soundbank;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/13/17:24
 * @Desc: 手抓饼屌丝版制作程序 装饰者超类
 **/
public abstract class AbstractHandsCookies implements FryHandsCookies {

    private FryHandsCookies fryHandsCookies;

    public AbstractHandsCookies(FryHandsCookies fryHandsCookies) {
        this.fryHandsCookies = fryHandsCookies;
    }

    @Override
    public void cookCookies() {
        fryHandsCookies.cookCookies();
    }

    @Override
    public void finish() {
        fryHandsCookies.finish();
    }
}
