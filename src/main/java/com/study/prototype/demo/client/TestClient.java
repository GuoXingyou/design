package com.study.prototype.demo.client;

import com.google.common.collect.Lists;
import com.study.prototype.demo.entity.Exp;
import com.study.prototype.demo.entity.Life;

import java.util.List;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/18/10:50
 * @Desc:
 **/
public class TestClient {


    public static void main(String[] args) throws CloneNotSupportedException {
        Life life = new Life();
        life.setHath("东尼杰伦");
        life.setTime(1);

        List<Exp> exps = Lists.newArrayList();
        Exp exp1 = new Exp();
        exp1.setMemo("我的这个经历就是到了上海");
        Exp exp2 = new Exp();
        exp2.setMemo("中央都决定啦，你来当总书记");
        Exp exp3 = new Exp();
        exp3.setMemo("那么所以我就到了北京");
        exps.add(exp1);
        exps.add(exp2);
        exps.add(exp3);
        life.setExps(exps);


        for(int i=0;i<3;i++){
            Life clone = life.clone();

            System.out.println(clone.toString());
        }
    }
}
