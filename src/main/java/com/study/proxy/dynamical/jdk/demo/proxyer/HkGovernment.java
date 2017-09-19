package com.study.proxy.dynamical.jdk.demo.proxyer;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/14/11:32
 * @Desc: 香港政府 民意
 **/
public class HkGovernment implements InvocationHandler{

    /**
     * 需要动态代理的实现类
     */
    private Object target;

    public HkGovernment(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("还是要按照HK的基本法、选举的法......去产生；");
        Object result = method.invoke(target, args);
        System.out.println("当然，我们的决定也很重要，到那个时候我们会表态的。");
        return result;
    }


    //生成一个代理对象 基本法
    public Object hkBaseLaw(){
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Class<?>[] interfaces = target.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }
}
