package com.study.strategy.demo.executor;

import com.study.strategy.demo.solution.ISaleSolution;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/12/14:14
 * @Desc: 方案选择执行对象
 **/
public class SolutionExecutor {

    private ISaleSolution solution;


    public Double execute(Double money){
        return this.solution.discount(money);
    }

    public SolutionExecutor(ISaleSolution solution) {
        this.solution = solution;
    }

    public ISaleSolution getSolution() {
        return solution;
    }

    public void setSolution(ISaleSolution solution) {
        this.solution = solution;
    }
}
