package com.study.builder.demo.dresser;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/9:55
 * @Desc: 搜查官化妆师--ConcreteBuilder
 **/
public class AvSearcherStyleDresser extends AbstractDresser {
    @Override
    public String head() {
        return "金色长发";
    }

    @Override
    public String face() {
        return "烈焰红唇";
    }

    @Override
    public String jacket() {
        return "紧身黑色皮衣，拉链可以完全拉开";
    }

    @Override
    public String trousers() {
        return "黑色紧身皮裤";
    }
}
