package com.jdk.jdk8;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/12/22/15:28
 * @Desc:
 **/
public interface SimpleInterface {

    void doSomeThings();

    default void doOtherThings(){
        System.out.println("Interface do other things.");
    }


}
