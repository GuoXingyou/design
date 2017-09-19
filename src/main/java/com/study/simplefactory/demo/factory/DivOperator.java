package com.study.simplefactory.demo.factory;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/8/14:53
 * @Desc:
 **/
public class DivOperator extends Operator {
    @Override
    protected double doOperate(double numA, double numB) {
        if(0 == numB){
            throw new RuntimeException("除数不能为0！");
        }


        return numA/numB;    }
}
