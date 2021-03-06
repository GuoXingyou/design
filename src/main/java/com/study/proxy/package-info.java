package com.study.proxy;

//7. 代理模式 为其他对象提供一种代理以控制对这个对象的访问

//在某些情况下，一个对象不适合或者不能直接引用另一个对象，而代理对象可以在客户端和目标对象之间起到中介的作用；


//代理类主要负责为委托类预处理消息、过滤消息、把消息转发给委托类，以及事后处理消息等。代理类与委托类之间通常
// 会存在关联关系，一个代理类的对象与一个委托类的对象关联，代理类的对象本身并不真正实现服务，而是通过调用委
// 托类的对象的相关方法，来提供特定的服务。


//分类：按生成时期：静态代理、动态代理。


 /*
 静态代理：由程序员创建或特定工具自动生成源代码，再对其编译。在程序运行前，代理类的.class文件就已经存在了。

 动态代理：在程序运行时，运用反射机制动态创建而程。
 */


// cglib动态代理  因为Java只允许单继承，而JDK生成的代理类本身就继承了Proxy类，因此，使用JDK实现的动态代
// 理不能完成继承式的动态代理，但是我们可以使用cglib来实现继承式的动态代理。（Spring中就含有cglib动态代理）


/*
* 远程代理，一个对象在不同的地址空间提供局部代表，从而隐藏一个对象在不同地址空间的事实
* 虚拟代理，需求创建开销极大的对象，通过它存放实例化需要很长时间的真是对象，eg：网页上过多的图片展示，都是一张
*   一张下载后才能看到
* 安全代理，用来控制真实对象访问时的权限，用于对象有不同访问权限的时候
* 智能指引，调用真实对象时，代理处理另外一些事情，eg：获取一个参数时通过JSR303检验参数
*
*
* 代理实际就是真实对象的代表，在访问真实对象时引入一定程度的间接性，在引入间接性时附加多种用途
*
* */