package com.effective.eg2.telescopingconstructor;

import lombok.Data;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/22/15:10
 * @Desc: 重叠构造器，根据不同的参数调用不同的构造器，但是当参数
 * 多了的时候，这种方式不够灵活,所以会实现动态传参的方法
 **/
public class Example1 {
    private int a;
    private int b;
    private int c;




    public Example1(int a) {
        this.a = a;
    }

    public Example1(int a, int b) {
        this.a = a;
        this.b = b;
    }

    public Example1(int a, int b, int c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static void main(String[] args) {
        Example1 example1 = new Example1(1,2,3);
        //参数一旦多了就会很麻烦
    }
}
