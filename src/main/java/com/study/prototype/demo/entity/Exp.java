package com.study.prototype.demo.entity;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/18/11:01
 * @Desc:
 **/
public class Exp {


    private String memo;

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    @Override
    public String toString() {
        return this.memo;
    }
}
