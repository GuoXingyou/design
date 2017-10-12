package com.study.adapter.demo.third.entity;

import com.google.common.collect.Maps;
import com.study.adapter.demo.third.inter.OutHath;

import java.util.Map;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/12/10:29
 * @Desc:
 **/
public class StandardOutHath implements OutHath {
    @Override
    public Map<String, String> speak() {
        Map<String, String> map = Maps.newHashMap();
        map.put("readPoetry", "苟利国家生死以，岂因祸福避趋之");
        map.put("ha3Page","怒斥港记、接受华莱士采访、二院访谈");




        return map;
    }

    @Override
    public Map<String, String> study() {
        Map<String, String> map = Maps.newHashMap();
        map.put("news", "见得风是的雨，搞个大新闻");


        return map;
    }

    @Override
    public Map<String, String> play() {
        Map<String, String> map = Maps.newHashMap();
        map.put("red","着最红的衫，扮十分感慨");
        map.put("black","戴上心灵的窗户，黑框蛤蟆镜");
        map.put("high","提起高腰裤，自信出国门");



        return map;
    }

    @Override
    public Map<String, String> sing() {
        Map<String, String> map = Maps.newHashMap();
        map.put("guitar","为了使得我们今天晚上的这个环境更加的轻松一点，我回忆起我曾经在1945年、46年的大学年代，经常我们喜欢玩儿“Hawaii guitar”");
        map.put("sing","经常弹奏的《Aloha 'Oe》这个歌曲，请州长夫人演唱");

        return map;
    }
}
