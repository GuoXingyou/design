package com.jdk8;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/12/19/10:43
 * @Desc: streamAPI test code
 **/
public class CollectorsTest {

    public static List<Integer> list = Arrays.asList(1,2,3,4);

    public static List<String> listStr = Arrays.asList("A","B","C","D");

    public static List<Human> humanList = Lists.newArrayList();

    static {
        Human human = new Human();
        human.setAge(23);
        human.setName("xx");
        human.setSex("man");

        Human human1 = new Human();
        human1.setAge(35);
        human1.setName("cc");
        human1.setSex("man");

        Human human2 = new Human();
        human2.setAge(65);
        human2.setName("vv");
        human2.setSex("woman");

        humanList.add(human);
        humanList.add(human1);
        humanList.add(human2);
    }



    public static void main(String[] args) {

//        CollectorsTest.averagingDoubleTest();

//        CollectorsTest.collectingAndThenTest();

//        CollectorsTest.countingTest();

//        CollectorsTest.joiningTest();

//        CollectorsTest.sumingTest();

//        CollectorsTest.compareTest();

//        CollectorsTest.summarizingTest();

//        CollectorsTest.reduceTest();

        CollectorsTest.groupByTest();
    }

    // Collectors.averagingDouble() 求平均值 (1+2+3+4)/4,末尾的Double后缀可以是Int，Long等数据类型的变形方法
    public static void averagingDoubleTest(){
        // Collectors.averagingDouble() 求平均值 (1+2+3+4)/4
        Double result = list.stream().collect(Collectors.averagingDouble(value -> value));
        System.out.println(result);
    }

    //先执行一个Collectors的操作之后再执行一个function操作，例如例子中先执行求均值，之后求均值的二次方
    public static void collectingAndThenTest(){
        Double result = list.stream().collect(Collectors.collectingAndThen(Collectors.averagingLong(v->v), s-> s*s));
        System.out.println(result);
    }


    //求出集合中的元素数量，相当于.size()方法
    public static void countingTest(){
        long result=  list.stream().collect(counting());
        //也可以不用收集器
        result = list.stream().count();
        System.out.println(result);
    }

    //字符串拼接 字符串集合
    public static void joiningTest(){
        String result=  listStr.stream().collect(Collectors.joining("|","[","]"));
        System.out.println(result);
    }

    //求和
    public static void sumingTest(){
        double result = list.stream().collect(Collectors.summingDouble(value -> value));
        System.out.println(result);
    }

    //optional 求最值
    public static void compareTest(){
        Optional<Human> optional = humanList.stream().collect(Collectors.maxBy(Comparator.comparing(Human::getAge)));
        System.out.println("eldest human is " + optional.get().getName());
    }

    //归约操作，极值、均值、求和、数量、string串
    public static void summarizingTest(){
        DoubleSummaryStatistics doubleSummaryStatistics =  list.stream().collect(Collectors
                .summarizingDouble(value -> value));

        System.out.println("average:" + doubleSummaryStatistics.getAverage());
        System.out.println("count:" + doubleSummaryStatistics.getCount());
        System.out.println("max:" + doubleSummaryStatistics.getMax());
        System.out.println("min:" + doubleSummaryStatistics.getMin());
        System.out.println("sum:" + doubleSummaryStatistics.getSum());
        System.out.println("string:" + doubleSummaryStatistics.toString());
    }

    //一般归约
    public static void reduceTest(){
        //相当于代码 double num= 0；for（Human h : humanList）{ num+=h.getAge();}
        double result = humanList.stream().collect(Collectors.reducing(0,Human::getAge,(integer,
                                                                               integer2) -> integer + integer2));
        System.out.println(result);
    }

    //分组操作
    public static void groupByTest(){
        //一级分组
        Map<String,List<Human>> map = humanList.stream().collect(groupingBy((Human human)
                -> { return hathGroup(human); }));
        System.out.println("一级分组：" + map.toString());

        Map<String,Map<String,List<Human>>> map1 = humanList.stream().collect(groupingBy((Human human)
                -> { return hathGroup(human); },groupingBy(Human::getSex)));
        System.out.println("二级分组：" + map1.toString());

        Map<String,Long> map2 = humanList.stream().collect(groupingBy((Human human)
                -> { return hathGroup(human); },counting()));
        System.out.println("分组统计：" + map2.toString());
    }

    private static String hathGroup(Human human){
        if (human.getAge() > 60) {
            return "elder";
        } else if (human.getAge() > 30) {
            return "hath";
        } else {
            return "naive";
        }
    }
}
