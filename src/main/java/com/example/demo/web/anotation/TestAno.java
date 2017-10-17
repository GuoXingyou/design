package com.example.demo.web.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/17/9:47
 * @Desc:
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface TestAno {

    String value() default "";
}
