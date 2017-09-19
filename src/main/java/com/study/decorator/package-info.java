package com.study.decorator;

//6.装饰模式
//动态地给一个对象添加一些额外的职责，就增加功能来说，装饰模式优于生成子类

/*
*
* 适用环境

（1）在不影响其他对象的情况下，以动态、透明的方式给单个对象添加职责。

（2）处理那些可以撤消的职责。

（3）当不能采用生成子类的方法进行扩充时。一种情况是，可能有大量独立的扩展，为支持每一种组合将产生大量的 子类，
        使得子类数目呈爆炸性增长。另一种情况可能是因为类定义被隐藏，或类定义不能用于生成子类。




        java中 I/O使用的即是装饰模式
*
* */


//在接口中通过实现与继承添加一些 多的职能


//系统需要新功能，旧类添加新代码，新代码装饰了原有类的核心职能和行为，如果给类增加新的字段、新的逻辑和方法就会增加
//原有类的复杂度；而新增的这些东西只会在一些特定情况才在主要职能和行为中使用；装饰模式就是提供了一个相当ocp的解决方案
//把需要增加的新职能作为一个装饰对象独立出来，并使得这个对象可以装饰旧类，那么，只需要在需要特定情况执行的时候，可以选择的
//装饰旧类就能达到目的。（解耦）

//优点很明显，把类中的装饰功能独立为装饰类，简化分离了旧类，让得两者各司其职；把核心职能和装饰职能有效的分开，可以去除相关
//类中重复装饰逻辑