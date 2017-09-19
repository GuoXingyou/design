package com.study.proxy.dynamical.cglib.demo.proxyer;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.invoke.MethodHandleInfo;
import java.lang.reflect.Method;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/14/13:21
 * @Desc: 彭定康说的
 **/
public class CglibDingkangPeng implements MethodInterceptor {

    private Enhancer enhancer = new Enhancer();


    public Object hkBaseLaw(Class<?> clazz){
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("还是要按照HK的基本法、选举的法......去产生；");
        methodProxy.invokeSuper(o,objects);
        System.out.println("当然，我们的决定也很重要，到那个时候我们会表态的。");
        return null;
    }
}
