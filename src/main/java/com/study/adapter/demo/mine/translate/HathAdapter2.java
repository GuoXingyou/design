package com.study.adapter.demo.mine.translate;

import com.study.adapter.demo.mine.inter.Hath;
import com.study.adapter.demo.third.entity.StandardOutHath;
import com.study.adapter.demo.third.inter.OutHath;

import java.util.Map;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/23/9:34
 * @Desc:
 **/
public class HathAdapter2 implements Hath {

    private OutHath outHath = new StandardOutHath();

    private Map<String, String> speak = outHath.speak();
    private Map<String, String> study = outHath.study();
    private Map<String, String> play = outHath.play();
    private Map<String, String> sing = outHath.sing();


    @Override
    public String makeBigNews() {
        return study.get("news");
    }

    @Override
    public String reciteHaThreePage() {
        return speak.get("ha3Page");
    }

    @Override
    public String readPoetry() {
        return speak.get("readPoetry");
    }

    @Override
    public String inRed() {
        return play.get("red");
    }

    @Override
    public String inBlackGlasses() {
        return play.get("black");
    }

    @Override
    public String inHighWaistedTrousers() {
        return play.get("high");
    }

    @Override
    public String playGuitar() {
        return sing.get("guitar");
    }

    @Override
    public String singAlohaOe() {
        return sing.get("sing");
    }
}
