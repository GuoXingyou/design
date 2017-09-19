package com.study.simplefactory.demo.factory;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/8/14:49
 * @Desc:
 **/
public class SubOperator extends Operator {


    @Override
    protected double doOperate(double numA, double numB) {

        return numA - numB;
    }
}
