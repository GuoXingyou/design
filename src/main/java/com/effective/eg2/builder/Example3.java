package com.effective.eg2.builder;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/22/15:28
 * @Desc: 构建器解决了前面两种的问题，但是本身也有缺陷：1.构造器写起来很复杂2.创建对象开销比较大
 * 所以构建器模式只适用于需要传入很多种情况参数的时候，比如大于4种参数的配合，才比较划算。
 * 而且值得注意的是：最好在类的设计之初就考虑是否使用构建器，否则日后扩展起来新构建器旧构造器一起用维护起来不方便。
 **/
public class Example3 {

    private int a;
    private int b;
    private int c;

    public static class Builder{
        private int a;
        private int b;
        private int c;

        public Builder A(int a){this.a = a;return this;}
        public Builder B(int b){this.b = b;return this;}
        public Builder C(int c){this.c = c;return this;}

        public Example3 build(){
            //可以在此方法中对参数校验来保证构造出的对象参数正确
            if(0 == this.a){
                throw new IllegalStateException("a can't be 0!");
            }
            return new Example3(this);
        }
    }

    private Example3(Builder builder){
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
    }

    public static void main(String[] args) {
        Example3 example3 =new Example3.Builder().A(1).B(2).C(3).build();
    }
}
