package com.study.adapter.demo.mine.translate;

import com.study.adapter.demo.mine.inter.Hath;
import com.study.adapter.demo.third.entity.StandardOutHath;

import java.util.Map;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/12/10:37
 * @Desc: 蛤丝适配器
 **/
public class HathAdapter extends StandardOutHath implements Hath {
    private Map<String, String> speak = speak();
    private Map<String, String> study = study();
    private Map<String, String> play = play();
    private Map<String, String> sing = sing();



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
