package com.jdk.jdk8;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/12/21/14:44
 * @Desc:
 **/
public class LambdaTest {


    public static void main(String[] args) {

        //新建线程
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                System.out.println("hello");
//            }
//        });

//        new Thread(() -> System.out.println("hello"));

        //迭代操作
//        List<String> list = Arrays.asList("hello","world","java","lambda");
//        for (String s : list){
//            System.out.println(s);
//        }
//
//        list.forEach(s -> System.out.println(s));
//        list.forEach(System.out::println);
//
//        filter(list,o -> o.indexOf("j") != -1);

        //创建Predicate
//        Predicate<String> predicate = s -> s.startsWith("j");


        List<Integer> integerList = Arrays.asList(100,200,300,400);
//        integerList.stream().map(integer -> .12*integer + integer).forEach(System.out::println);
        double res = integerList.stream().map(integer -> .12*integer + integer).
                reduce((sum, elem) -> sum + elem).get();
        System.out.println(res);
    }


    public static void filter(List<String> list, Predicate<String> predicate){
        list.stream().filter(s -> predicate.test(s)).forEach(s -> System.out.println(s));
    }

}
