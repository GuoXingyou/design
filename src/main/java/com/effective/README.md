#effective java

###1.考虑用静态工厂方法代替构造器
####静态工厂方法类 vs. 构造器
#####优点：
> * 有名称
>   > 可读性高
> * 单例模式的运用
> * 可返回原返回类型的任何子类型对象 
>   > 可参考Java Collections Framework的集合接口实现,Collection Framework 
API相比导出32
个独立公有类来达到目的的实现方式要小得多，每一个被返回的对象都是被相关接口方法精确定义；同时使用这种静态工厂方法时，甚至要
求客户端通过接口来引用被返回的对象，而不是通过的他的实现类来引用
> * 创建泛型实例代码更加简洁，可参考guava中Lists中的方法
````
    //使用构造器创建
    Map<String, List<String>> m1 = new HashMap<String, List<String>>();
    //使用静态工厂方法创建
    Map<String, List<String>> m2 = HashMap.newInstance();
    
    public static <K, V> HashMap<K, V> newInstance() {
      return new HashMap<K, V>();
    }
````
> * 构成基于接口的框架，参考eg1代码，以服务提供者框架（Service Provider Framework）为例讲解，该框架的代表是JDBC API

[参考链接](http://www.jianshu.com/p/4d7a0cd36a82)

#####缺点：
> * 类如果不含有公有或受保护的构造器，就不能被子类化
> * 与其余静态方法实际上没有任何区别，在API文档中，它们没有像构造器那样被明确标识，因此对于提供静态工厂方法而没有构造器的类
来说，想要明确快速的找到如何实例化一个类很麻烦。所以，写好注释和标准的方法命名。 

***************************************************
###2.遇到多个构造器参数时考虑用构建器
> 无论是静态工厂还是构建器都有一个局限性，不能很好的扩建到大量的可选参数，我曾经遇到过通过构建器来进行约束的代码，过多的
入参导致代码整洁度和可读性不高；虽然对于有些场景可以使用重叠构造器，但是一旦参数增加，代码就会开始向着不可控的方向发展，
客户端代码也难以编写；另一种常见的javaBean模式，利用setter来避免入参错误，但是这会使得构造过程被分割，在构造过程可能出
现不一致，而且，JavaBean模式阻止了把类做成不可变的可能，就需要为他的线程安全额外付出
####Builder模式
* > builder模式保证了重叠构造器模式那样的安全性的同时也保证了JavaBean模式的可读性，不直接生成对象，让客户端调用必要的参数
构造一个builder对象，最后通过无参的build方法生成不可变对象，参考eg2代码。
* > builder模式也有自身不足，为了创建对象会先创建一个构建器，增加了开销，虽然实际中可能影响不大，但是某些需要性能的场景下可
能会带来问题。同时编写构建器比起重叠构造器更加冗长，IDEA搞的自动生成构造器的功能啊，excited！所以只有在参数较多的时候使用，
但是设计之初最好能考虑清楚，是否字段繁多，因为需要多个参数才添加构建器时，可能会使的代码无法控制，那些过时的构造器和静态工厂
就会显得不协调，所以我们创建类时一定要考虑历史的行程。
* > Generally speaking，如果类的构造器或者静态工厂中具有过多入参的时候，可以考虑Builder模式。

[参考链接](http://blog.csdn.net/h3c4lenovo/article/details/38687023)

***************************************************
###3.用私有构造器或者枚举类型强化Singleton属性（？）
* 懒汉，线程不安全
````
public class Singleton{
    private static Singleton instance;
    private Singleton (){}
    public static Singleton getInstance() {
        if (instance == null) {
          instance = new Singleton();
        } 
        return instance;
     }
 }
````
> 这种写法主要是懒加载，但是多线程不能正常使用
* 懒汉，线程不安全
````
public class Singleton {  
    private static Singleton instance;  
    private Singleton (){}  
    public static synchronized Singleton getInstance() {  
    if (instance == null) {  
        instance = new Singleton();  
    }  
    return instance;  
    }  
}  
````
> 这种写法能够在多线程中很好的工作，而且看起来它也具备很好的lazy loading，
但是，遗憾的是，效率很低，99%情况下不需要同步；该方法只需要在第一次创建实例
时进行同步，减少了系统的开销。但是，当第二个线程进入synchronized同步块时，
没有检查实例是否为空，会存在线程安全问题。由此引出下一个方法，即双重检查锁定
方法。
* 懒汉，线程安全v2（双重校验）
````
public class Singleton {  
    private volatile static Singleton singleton;  
    private Singleton (){}  
    public static Singleton getSingleton() {  
    if (singleton == null) {  
        synchronized (Singleton.class) {  
        if (singleton == null) {  
            singleton = new Singleton();  
        }  
        }  
    }  
    return singleton;  
    }  
}  
````
> 上一种方式的升级版，该方法减少了线程同步引起的开销，同时不存在线程安全问题。
根据Java的内存模型，将instance引用类型声明为volatile，该双重检查锁定方法可
以正常运行。
* 饿汉
````
public class Singleton {  
    private static Singleton instance = new Singleton();  
    private Singleton (){}  
    public static Singleton getInstance() {  
    return instance;  
    }  
}  
````
> 这种方式基于classloder机制避免了多线程的同步问题，不过，instance在类装载时
就实例化，虽然导致类装载的原因有很多种，在单例模式中大多数都是调用getInstance
方法，这样就增加了内存开销。
* 饿汉v2
````
public class Singleton {  
    private Singleton instance = null;  
    static {  
    instance = new Singleton();  
    }  
    private Singleton (){}  
    public static Singleton getInstance() {  
    return this.instance;  
    }  
}  
````
> 和上一种方式差不多，都是在类初始化即实例化instance。
* IoDH（静态内部类）
````
public class Singleton {  
    private static class SingletonHolder {  
        private static final Singleton INSTANCE = new Singleton();  
    }  
    private Singleton (){}  
    public static final Singleton getInstance() {  
        return SingletonHolder.INSTANCE;  
    }  
}  
````
* > IoDH，即Initializationon on Demand Holder的缩写。IoDH技术单例类中增加一
个静态(static)内部类，在该内部类中创建单例对象，再将该单例对象通过getInstance()
方法返回给外部使用。通过使用IoDH，既可以实现延迟加载，又可以保证线程安全，不影响系
统性能，是一种最好的Java语言单例模式实现方式。其缺点是与编程语言本身的特性相关，很
多面向对象语言不支持IoDH。
* > 这种方式同样利用了classloder的机制来保证初始化instance时只有一个线程，它跟饿汉
方式不同的是（很细微的差别）：饿汉方式是只要Singleton类被装载了，那么instance就会
被实例化（没有达到lazy loading效果），而这种方式是Singleton类被装载了，instance
不一定被初始化。
* ENUM 
````
public enum Singleton {  
    INSTANCE;  
    public void whateverMethod() {  
    }  
}  
````
> 作者Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还能防止反序列化重新创
建新的对象。
*******************************************
###4.通过私有构造器强化不可实例化的能力
> 当我们不需要实例化一个类的时候，例如util工具类，但是又因为该类不包含显式的构造器，编
译器会为我们默认创建无参的缺省构造器，可以用私有构造器的方式来保证类不被实例化。~~不要企
图通过抽象类来强制使类不能实例化，你们这样是不行的！~~
````
public class Util{
    //suppress default consturctor for noninstantiability(为不可实例化的类抑制默认构造器)
    private Util(){ throw new AssertionError(); }
}
````
> 这种做法的副作用就是不能被子类化。
********************************************
###5.避免创建不必要的对象
> 通常使用静态工厂方法是优于构造器的，例如Boolean.valueOf(String)是优于Boolean(String)
的，构造器在每次被调用的时候会创建一个新的对象，静态工厂方法是不会这么做的（重用不可变或者已知
不会修改的对象）。
