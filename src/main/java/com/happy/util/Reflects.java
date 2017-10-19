package com.happy.util;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/10/19/14:14
 * @Desc: 反射工具类
 **/
public class Reflects {

    /**
     * 反射取值
     * @param field
     * @param obj
     * @return
     * @throws IllegalAccessException
     */
    public static String getValueByReflect(Field field, Object obj) throws IllegalAccessException {
        field.setAccessible(true);

        String type = field.getType().getName();

        if("java.util.Date".equals(type)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format((Date)field.get(obj));
        }
        return field.get(obj).toString();

    }


    public static void setValueByReflect(Field field, Object obj, String value, String... fmt) throws ParseException, IllegalAccessException {
        field.setAccessible(true);

        String type = field.getType().getName();

        if(type.endsWith("String")){
            field.set(obj, value);
        }
        if("java.util.Date".equals(type)){
            SimpleDateFormat sdf = new SimpleDateFormat(fmt[0]);
            field.set(obj, sdf.parse(value));
        }
        if(type.endsWith("int") || type.endsWith("Integer")){
            field.set(obj, Integer.valueOf(value));
        }
        if(type.endsWith("double") || type.endsWith("Double")){
            field.set(obj, Double.valueOf(value));
        }


    }

}
