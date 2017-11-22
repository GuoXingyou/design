package com.effective.eg2.javabean;

import lombok.Data;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/22/15:25
 * @Desc: javaBean模式，这种方式提高了传参的可读性，
 * 也提高了传参的灵活性，但是会增加代码行数，同时在多线程异步执行的时候导致奇怪的错误
 **/
@Data
public class Example2 {

    private int a;
    private int b;
    private int c;

    public static void main(String[] args) {
        Example2 example2 = new Example2();

        example2.setA(1);
        example2.setB(2);
        example2.setC(3);
        //可读性和参数准确性得到保证，但是代码整洁程度下降，并且因为分割了构建过程多线程可能会出现参数偏差
    }

}
