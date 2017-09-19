package com.study.builder.demo.dresser;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/19/9:53
 * @Desc: 蛤丝化妆师
 **/
public class HathStyleDresser extends AbstractDresser {
    @Override
    public String head() {
        return "梳一个大背头";
    }

    @Override
    public String face() {
        return "戴上黑框眼镜";
    }

    @Override
    public String jacket() {
        return "着最闪的衫，扮十分感慨";
    }

    @Override
    public String trousers() {
        return "提起高腰裤，自信出国门";
    }
}
