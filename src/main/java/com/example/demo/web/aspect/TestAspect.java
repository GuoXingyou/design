package com.example.demo.web.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/17/9:49
 * @Desc:
 **/
@Aspect
@Component
public class TestAspect {


    @Pointcut("@annotation(com.example.demo.web.anotation.TestAno)")
//    @Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
    private void advice() {
    }

    @Before("advice()")
    public void doBefore(JoinPoint jp) {
        System.out.println(".............................................");
        System.out.println("I'm into!");
    }

    /*关于自定义注解的aop拦截，事出有因，朋友问及利用spring的aop拦截项目中的struts的action，考虑到用注解
    * 的方式更加灵活美观，尝试自己定义一个注解来拦截action方法，但是并拦截不到，我猜测原因有二，一是spring
    * 容器使用的实现都是动态代理，嵌套方法是拦截不到的；第二则是可能是struts的方法不在容器中，所以拦截无效？
    * 感觉第二个说法有点勉强*/
}
