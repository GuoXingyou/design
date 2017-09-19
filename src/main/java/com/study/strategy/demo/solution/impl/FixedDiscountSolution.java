package com.study.strategy.demo.solution.impl;

import com.study.strategy.demo.solution.ISaleSolution;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/12/14:04
 * @Desc: 固定折扣方案
 **/
public class FixedDiscountSolution implements ISaleSolution {

    private static final double dis = 0.75;//75折

    @Override
    public double discount(Double normalRes) {
        //只取两位小数 四舍五入（人民币最小单位 分）
        BigDecimal bg = new BigDecimal(normalRes * dis).setScale(2, RoundingMode.UP);

        return  bg.doubleValue();
    }
}
