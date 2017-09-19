package com.study.prototype.demo.entity;

import com.alibaba.fastjson.JSON;

import java.util.List;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/18/10:43
 * @Desc:
 **/
public class Life implements Cloneable {

    private String hath;

    private Integer time;

    private List<Exp> exps;

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

    @Override
    public Life clone() throws CloneNotSupportedException {
        Life life = null;

        try {
            life = (Life)super.clone();
            life.setExps(this.exps);

        }catch (Exception e){
            e.printStackTrace();
        }

        return life;
    }


    public String getHath() {
        return hath;
    }

    public void setHath(String hath) {
        this.hath = hath;
    }

    public Integer getTime() {
        return time;
    }

    public void setTime(Integer time) {
        this.time = time;
    }

    public List<Exp> getExps() {
        return exps;
    }

    public void setExps(List<Exp> exps) {
        this.exps = exps;
    }
}
