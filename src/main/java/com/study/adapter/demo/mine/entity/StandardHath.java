package com.study.adapter.demo.mine.entity;

import com.study.adapter.demo.mine.inter.Hath;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/12/10:24
 * @Desc: 标准蛤丝
 **/
public class StandardHath implements Hath {
    @Override
    public String makeBigNews() {
        return "见得风是的雨，搞个大新闻";
    }

    @Override
    public String reciteHaThreePage() {
        return "怒斥港记、接受华莱士采访、二院访谈";
    }

    @Override
    public String readPoetry() {
        return "苟利国家生死以，岂因祸福避趋之";
    }

    @Override
    public String inRed() {
        return "着最红的衫，扮十分感慨";
    }

    @Override
    public String inBlackGlasses() {
        return "戴上心灵的窗户，黑框蛤蟆镜";
    }

    @Override
    public String inHighWaistedTrousers() {
        return "提起高腰裤，自信出国门";
    }

    @Override
    public String playGuitar() {
        return "为了使得我们今天晚上的这个环境更加的轻松一点，我回忆起我曾经在1945年、46年的大学年代，经常我们喜欢玩儿“Hawaii guitar”";
    }

    @Override
    public String singAlohaOe() {
        return "经常弹奏的《Aloha 'Oe》这个歌曲，请州长夫人演唱";
    }
}
