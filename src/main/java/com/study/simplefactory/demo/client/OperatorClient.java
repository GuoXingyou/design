package com.study.simplefactory.demo.client;

import com.study.simplefactory.demo.factory.Operator;
import com.study.simplefactory.demo.factory.OperatorFactory;
import com.study.simplefactory.demo.enums.OperateEnum;

import java.util.Scanner;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/8/14:57
 * @Desc:
 **/
public class OperatorClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("请输入数字A:");
        String a = scanner.next();

        System.out.println("请输入数字B:");
        String b = scanner.next();

        System.out.println("请输入算法：（'+', '-', '*', '/'）");
        String type = scanner.next();
        OperateEnum operateEnum = OperateEnum.getByCode(type);

        Operator operator = OperatorFactory.newInstance(operateEnum);

        double res = operator.execute(a, b);

        System.out.println("计算结果：" + res);
    }

}
