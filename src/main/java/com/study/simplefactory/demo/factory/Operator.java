package com.study.simplefactory.demo.factory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/8/14:25
 * @Desc: 计算操作超类
 **/
public abstract class Operator {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    protected abstract double doOperate(double numA, double numB);

    public double execute(String strNumA, String strNumB){

        logger.info("开始执行操作========>>>> 数字A:" + strNumA + ", 数字B:" +strNumB );

        double a = Double.valueOf(strNumA);
        double b = Double.valueOf(strNumB);
        double res = 0d;


        long startTime = System.currentTimeMillis();

        try {

            res = doOperate(a, b);

        }catch (Exception e){

            logger.error("执行异常：", e);
        }


        logger.info("执行结束，所用时间：" + String.valueOf(System.currentTimeMillis() - startTime)  + "毫秒");

        return res;
    }
}
