package com.study.strategy.demo.solution;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/8/16:08
 * @Desc: 销售方案
 **/
public interface ISaleSolution {

    /**
     * 优惠计算
     * @param normalRes 正常结果
     * @return
     */
   double discount(Double normalRes);
}
