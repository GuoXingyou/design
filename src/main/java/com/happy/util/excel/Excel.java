package com.happy.util.excel;

import java.lang.annotation.*;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/18/16:48
 * @Desc:
 **/
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {

    int index() default 0;//序列

    String name();//字段名

    String dateFmt() default "yyyy-MM-dd HH:ss:mm";//时间格式

}
