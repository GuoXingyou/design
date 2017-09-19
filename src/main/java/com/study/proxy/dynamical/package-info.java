package com.study.proxy.dynamical;

//动态代理中，代理类并不是在Java代码中实现，而是在运行时期生成，相比静态代理，动态代理可以很方便
// 的对委托类的方法进行统一处理，如添加方法调用次数、添加日志功能等等，动态代理分为jdk（反射）动态代理和
// cglib动态代理，下面通过一个例子看看如何实现jdk动态代理。