package com.jdk.jdk8;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/12/22/15:30
 * @Desc:
 **/
public class SimpleInterfaceImpl implements SimpleInterface {

    /**
     * 如果实现了多个接口，且不同接口中有相同default方法，jvm会抛出编译异常
     */

    @Override
    public void doSomeThings() {
        System.out.println("Class do some things.");
    }

    public static void main(String[] args) {
        SimpleInterface simpleInterface = new SimpleInterfaceImpl();
        simpleInterface.doSomeThings();
    }
}
