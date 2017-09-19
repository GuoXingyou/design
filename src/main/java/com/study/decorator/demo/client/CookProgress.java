package com.study.decorator.demo.client;

import com.study.decorator.demo.chef.Chef;
import com.study.decorator.demo.cook.FryHandsCookies;
import com.study.decorator.demo.progress.AbstractHandsCookies;
import com.study.decorator.demo.progress.HandsCookiesWithHam;
import com.study.decorator.demo.progress.HandsCookiesWithMeatFloss;
import com.study.decorator.demo.progress.SingleCookies;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/13/17:36
 * @Desc:
 **/
public class CookProgress {

    public static void main(String[] args) {
        FryHandsCookies chef = new Chef();


        AbstractHandsCookies meatFloss = new HandsCookiesWithMeatFloss(new SingleCookies(chef));

        meatFloss.cookCookies();
        meatFloss.finish();

        System.out.println("*******************************");

        AbstractHandsCookies ham = new HandsCookiesWithHam(new SingleCookies(chef));

        ham.cookCookies();
        ham.finish();

        System.out.println("*******************************");

        AbstractHandsCookies meatWithHam = new HandsCookiesWithHam(new HandsCookiesWithMeatFloss(new
                SingleCookies(chef)));

        meatWithHam.cookCookies();
        meatWithHam.finish();

        System.out.println("*******************************");

        AbstractHandsCookies hamWithMeat = new HandsCookiesWithHam(new HandsCookiesWithMeatFloss
                (new SingleCookies(chef)));

        hamWithMeat.cookCookies();
        hamWithMeat.finish();

        System.out.println("*******************************");
    }
}
