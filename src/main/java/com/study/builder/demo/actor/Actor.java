package com.study.builder.demo.actor;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/9:47
 * @Desc: 演员--product
 **/
public class Actor {

    //造型装扮
    private List<String> sculpt = Lists.newArrayList();


    public void showMe(){
        sculpt.forEach(s -> {
            System.out.println(s);
        });
    }

    public List<String> getSculpt() {
        return sculpt;
    }

    public void setSculpt(List<String> sculpt) {
        this.sculpt = sculpt;
    }
}
