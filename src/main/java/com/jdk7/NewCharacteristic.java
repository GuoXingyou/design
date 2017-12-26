package com.jdk7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/12/25/14:54
 * @Desc:
 **/
public class NewCharacteristic {



    public static void main(String[] args) {
        //1、switch支持string
        String str = "java";
        switch (str){
            case "java":
                System.out.println(str);
                break;
            default:throw new IllegalArgumentException("no arg can match.");
        }

        //2、泛型实例化自动推断 idea自动就会选择这种格式，当然guava包里的构建方法当然坠吼啦
        List<String> stringList = new ArrayList<String>();//before 1.7
        stringList = new ArrayList<>();//with 1.7

        //3、二进制字面量
        System.out.println(0x11);//十六进制
        System.out.println(011);//八进制
        System.out.println(0b11);//二进制

        //4、数字字面量可以出现下划线 下划线只能存在于数字之间，不能在标识符前后
        long idCardNo = 500_224_19920819_0010L;//好处肯定是便于识别和阅读
        long a = 0xffff_ffffl;
        byte b = 0b110;

        //5、在可变参数方法中传递非具体化参数（Non-Reifiable Formal Parameters）,改进编译警告和错误
        addToList(stringList,"hello");

        //6、try-with-resource
        /**
         * 这个更应该说是一个语法糖，jdk7提供了try-with-resources,可以自动关闭相关的
         * 资源（只要该资源实现了AutoCloseable接口，jdk7为绝大部分资源对象都实现了这个接口）
         */
        try {
            readFile("path");//before 1.7
            readFileNew("path");//with 1.7
        } catch (IOException e) {
            e.printStackTrace();
        }

        //7、catch多个exception，rethrow exception 改进了类型检测
        //before 1.7
        try {
            readFile("path");
        }catch (IOException e1){
            //...
        }catch (NullPointerException e2){
            //...
        }
        //with 1.7
        try {
            readFile("path");
        }catch (IOException|NullPointerException e){
            //...
        }





    }

    @SafeVarargs
    public static <T> void addToList(List<T> list, T... element){
        /**
         * 有些参数类型，例如ArrayList<Number> 和 List<String>,是非具体化的（non-reifiable）.
         * 在编译阶段，编译器会擦除该类型信息。
         *  在jdk7之前，当你调用一个含有非具体化参数的可变参数方法，你必须自行保证不会发生“heappollution”。
         *  这有一个问题，如果调用者对方法不熟悉，他根本无法判断。JDK7对此做了改进，在该方法被定义时久发出警告,
         *  要消除警告，可以有三种方式:
         *  1.加 annotation @SafeVarargs
            2.加 annotation @SuppressWarnings({"unchecked", "varargs"})
            3.使用编译器参数 –Xlint:varargs;
         */

        for (T x : element) {
            list.add(x);
        }
    }

    public static String readFile(String path) throws IOException {
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(path));
            return br.readLine();
        }  finally {// before 1.7
            br.close();
        }
    }

    public static String readFileNew(String path) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))){// with 1.7
            return br.readLine();
        }
    }
}
