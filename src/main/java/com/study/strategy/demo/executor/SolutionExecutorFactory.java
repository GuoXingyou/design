package com.study.strategy.demo.executor;

import com.study.strategy.demo.enums.Solution;
import com.study.strategy.demo.solution.impl.BuyMoreSolution;
import com.study.strategy.demo.solution.impl.FixedDiscountSolution;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/12/14:42
 * @Desc:
 **/
public class SolutionExecutorFactory {

    public static SolutionExecutor createExecutor(Solution solution){
        SolutionExecutor executor = null;

        switch (solution){
            case BUY_MORE_1 :
                executor = new SolutionExecutor(new BuyMoreSolution());
                break;
            case FIXED_DISCOUNT_75 :
                executor = new SolutionExecutor(new FixedDiscountSolution());
                break;
            default:
                break;
        }

        return executor;
    }

}
