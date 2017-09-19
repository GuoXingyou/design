package com.study.strategy.demo.client;

import com.study.strategy.demo.enums.Solution;
import com.study.strategy.demo.executor.SolutionExecutor;
import com.study.strategy.demo.executor.SolutionExecutorFactory;
import com.study.strategy.demo.solution.impl.BuyMoreSolution;
import com.study.strategy.demo.solution.impl.FixedDiscountSolution;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/12/14:16
 * @Desc:
 **/
public class StrategyClient {



    public static void main(String[] args) {

        Double normal = 523.50;

        SolutionExecutor executor = new SolutionExecutor(new FixedDiscountSolution());
        System.out.println("固定折扣应付：" + executor.execute(normal));

        SolutionExecutor executor1 = new SolutionExecutor(new BuyMoreSolution());
        System.out.println("多买多送应付：" +  executor1.execute(normal));

        /************************************************************************/
        //结合简单工厂模式

        System.out.println("固定折扣应付：" + SolutionExecutorFactory.createExecutor(Solution
                .FIXED_DISCOUNT_75).execute(normal));

        System.out.println("多买多送应付：" +  SolutionExecutorFactory.createExecutor(Solution
                .BUY_MORE_1).execute(normal));


        /*
        * 单纯策略模式，会暴露更多内部对象到客户端，
        * 对比下面的方式，结合简单工厂模式想外暴露的只有SolutionExecutorFactory 和一个Solution枚举，
        * 也就是说结合起来的方式的隐蔽工作做得太吼啦O..O!
        *
        *
        * 实际上就是把选择算法这个职责由客户端转移到了算法执行对象，客户端只需要提出需求（枚举 Solution）给工厂类就可以
        *
        *
        * */
    }
}
