package com.study.template.question;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/9/18/11:25
 * @Desc: 蛤三篇
 **/
public abstract class HaThreePart {

    protected abstract String ansFirst();

    protected abstract String ansSecond();

    protected abstract String ansThird();

    public void theFirst(){
        System.out.println
                ("2009年4月23日，退休的长者到访____时，为其作出“求真，务实，自强，创新”的题词，并回顾自己担任国家领导人期间的工作成绩。");
        System.out.println("A.中国联合工程公司;B.重庆浪沃培训机构;C.上海交通大学");
        System.out.println("选择答案" + ansFirst());
    }

    public void theSecond(){
        System.out.println
                ("1998年，美国CBS记者麦克·华莱士所在的哥伦比亚广播公司《___》栏目，通过公关公司向中国驻纽约总领事馆递交了采访长者的申请。");
        System.out.println("A.中国有习蛤;B.60秒钟;C.60分钟");
        System.out.println("选择答案" + ansSecond());
    }

    public void theThird(){
        System.out.println("\"这辈子听过女人最大的谎言是什么？\"\"是彭定康说的。\"这句谎言是谁说的？");
        System.out.println("A.大桥未久;B.张宝蛤;C.张宝华");
        System.out.println("选择答案" + ansThird());
    }


    public void theResult() throws InterruptedException {
        theFirst();

        System.out.println("===========================");
        Thread.sleep(2000);

        theSecond();

        System.out.println("===========================");
        Thread.sleep(2000);

        theThird();
    }

}
