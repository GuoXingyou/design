package com.study.template.client;

import com.study.template.answer.AnswerByHath;
import com.study.template.answer.AnswerByPeople;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/18/13:55
 * @Desc:
 **/
public class TestClient {


    public static void main(String[] args) throws InterruptedException {
        System.out.println("Let's view the answer by young people.");
        AnswerByPeople young = new AnswerByPeople();
        young.theResult();

        Thread.sleep(2000);

        System.out.println("Let's view the answer by intellectual(知识分子).");
        AnswerByHath hath = new AnswerByHath();
        hath.theResult();

        //太屎公曰：模板方法模式算比较常用的模式，（我用的时候挺多），通过把不变行为（子类中的重复多做）搬移到
        //超类中达到去重的目的同时又保持个性化设置，是一个很好的代码复用平台
    }
}
