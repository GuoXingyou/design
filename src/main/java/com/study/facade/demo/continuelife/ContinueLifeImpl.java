package com.study.facade.demo.continuelife;

import com.study.facade.demo.poetry.Fu;
import com.study.facade.demo.poetry.Gou;
import com.study.facade.demo.poetry.Li;
import com.study.facade.demo.poetry.Read;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/18/16:08
 * @Desc:
 **/
public class ContinueLifeImpl implements ContinueLife {

    private Read head = new Gou();

    private Read body;

    @Override
    public void doContinue() {
        body = new Li();
        readPoetry();
    }

    @Override
    public void doAvoid() {
        body = new Fu();
        readPoetry();
    }


    private void readPoetry(){
        head.read();
        body.read();
    }
}
