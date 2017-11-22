package com.effective.eg2.best;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/11/22/15:48
 * @Desc:
 **/
public class Example4 {

    private int a;
    private int b;
    private int c;


    public static class Builder implements com.effective.eg2.best.Builder<Example4>{
        @Override
        public Example4 build() {
            //可以在此方法中对参数校验来保证构造出的对象参数正确
            if(0 == this.a){
                throw new IllegalStateException("a can't be 0!");
            }
            return new Example4(this);
        }

        private int a;
        private int b;
        private int c;


        public Builder A(int a){this.a = a;return this;}
        public Builder B(int b){this.b = b;return this;}
        public Builder C(int c){this.c = c;return this;}

    }

    private Example4(Builder builder){
        this.a = builder.a;
        this.b = builder.b;
        this.c = builder.c;
    }
}
