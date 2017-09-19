package com.study.simplefactory.demo.factory;

import com.study.simplefactory.demo.enums.OperateEnum;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/8/14:30
 * @Desc:
 **/
public class OperatorFactory {

    public static Operator newInstance(OperateEnum operate){

        switch (operate){
            case ADD: return new AddOperator();
            case SUB: return new SubOperator();
            case MUL: return new MulOperator();
            case DIV: return new DivOperator();


        }

        return new Operator() {
            @Override
            protected double doOperate(double numA, double numB) {
                return 0;
            }
        };
    };

}
