package com.study.strategy.demo.solution.impl;

import com.study.strategy.demo.solution.ISaleSolution;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/12/14:07
 * @Desc: 多买多送方案
 **/
public class BuyMoreSolution implements ISaleSolution {


    @Override
    public double discount(Double normalRes) {

        if(100 < normalRes && normalRes <=200)
            return normalRes - 50;

        //......

        return normalRes;
    }
}
