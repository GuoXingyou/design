package com.study.simplefactory.demo.factory;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/8/14:44
 * @Desc:
 **/
public class AddOperator extends Operator {
    @Override
    protected double doOperate(double numA, double numB) {
        return numA + numB;
    }
}
