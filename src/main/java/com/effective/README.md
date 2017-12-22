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
> * 构成基于接口的框架，参考**_eg1代码_**，以服务提供者框架（Service Provider Framework）为例讲解，该框架的代表是JDBC API

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
构造一个builder对象，最后通过无参的build方法生成不可变对象，参考**_eg2代码_**。
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
####5.1重用不可变量
> 通常使用静态工厂方法是优于构造器的，例如Boolean.valueOf(String)是优于Boolean(String)
的，构造器在每次被调用的时候会创建一个新的对象，静态工厂方法是不会这么做的（重用不可变或者已知
不会修改的对象）。
####5.2重用一直不可变量
````
public class Person{
    private final Date birttday;
    public boolean isBabyBoomer(){
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946,Calendar.JANUARY,1,0,0,0);
        Date boomStart = gmtCal.getTime();
        gmtCal.set(1965,Calendar.JANUARY,1,0,0,0);
        Date boomEnd = gmtCal.getTime();
        
        return bithday.compareTo(boomStart) >= 0 && birthday.compareTo(boomEnd) < 0;
    }
}
````
> 上面的代码主要是检验这个人是否是1946年至1965年（生育高峰期）出生的。isBabyBoomer每次被调用都会
创建一个Calendar、一个TimeZone和两个Date的实例，只写都是不必要的。
````
public class Person{
    private final Date birthday;
    private static final Date BOOM_START;
    private static final Date BOOM_END;
    
    static{
        Calendar gmtCal = Calendar.getInstance(TimeZone.getTimeZone("GMT"));
        gmtCal.set(1946,Calendar.JANUARY,1,0,0,0);
        BOOM_START = gmtCal.getTime();
        gmtCal.set(1965,Calendar.JANUARY,1,0,0,0);
        BOOM_END = gmtCal.getTime();
    }
    
    public boolean isBabyBoomer(){
        return bithday.compareTo(BOOM_START) >= 0 && birthday.compareTo(BOOM_END) < 0;
    }
}
````
> 改进后的代码只会在初始化的时候创建Calendar、TimeZone和Date。如果isBabyBoomer被频繁调用，这种
方法就会显著提升性能。而且BOOM_START和BOOM_END变为了final静态域，读者知道这两个日期被当做常量处理，
提高了代码的可读性。~~但是，这种优化带来的优化并不是总是那么明显，因为Calendar实例的代价比较昂贵。~~
####5.3适配器（adapter）
> 适配器把功能委托给了一个后备对象（backing object），从而为后备对象可替代接口。由于适配器除了后备对
象之外，没有其他的状态信息，所以针对某个给定对象的特定适配器而言，它不需要创建多个适配器实例。例如：Map
的keySet方法的Set视图（key），每次调用实际上返回的是同一个实例。因为是由同一个Map支撑，所以即使Set发
生变化，其余返回对象的功能也会对等的随之变化。
####5.4自动装箱（autoboxing）
> 自动装箱和拆箱使得基本类型和装箱基本类型之间的差距模糊起来，但是并没有完全消除。
````
public static void main(String[] args){
    Long sum = 0L;
    for(long i = 0; i < Integer.MAX_VALUE; i++){
        sum += i;
    }
    System.out.println(sum);
}
````
> 上面的程序没有问题，但是执行速度比实际慢了一些。变量sum被申明为Long而不是long，意味着程序需要多构造大
约2^31个多余的实例（每次往Long sum中增加long时构建一个）。将sun申明改为long，运行时间会明显减少：**_优先
使用基本类型而不是装箱基本类型，注意无意识的自动装箱。_**
####5.5不要因为上面的举例而错误的以为对象的创建和回收代价很昂贵，小对象的创建和回收非常廉价
> 重用对象可能存在潜在的错误和安全漏洞，善用保护性拷贝（defensive copying），而不必要的创建对象会影响程序
风格和性能。
***********************************************
###6.消除过期对象的引用
````
public class Stack{
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    
    public Stack(){
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }
    
    public void push(Object e){
        ensureCapacity();
        elements[size++] = e;
    }
    
    public Object pop(){
        if(0 == size)
            throw new EmptyStackException();
        return elements[--size];
    }
    
    /**
    * Ensure space for at least one more element, roughly doubling the capacity each time
    * the array needs to grow.(确保空间至少有一个元素，每次阵列需要增长一杯容量)
    **/
    private void ensureCapacity(){
        if(elements.length == size)
            elements = Array.copyOf(elements, 2 * size + 1);
    }
}
````
> 如果一个栈先是增长，然后再收缩，那么，从栈中弹出来的对象是不会被当做垃圾回收的，即是使用栈的程序不再使用
这些对象，原因是栈内部对这些过期引用（obsolete reference）的维护。过期引用，指的是永远不会再被解除的引用。
上面的代码里面，elements中下标小于size的元素都是属于活动部分（active portion）之外的过期引用。修复这类问
题方法很简单：一旦对象引用过期，清空这些引用即可。对于上面代码做出以下修改：
````
public Object pop(){
    if(0 == size)
        throw new EmptyStackException();
    Object result = elements[--size];
    elements[size] = null;//Eliminate obsolete reference （消除过期引用）
    return result;
}
````
> 清空过期引用的另一个好处在于，如果他们又被错误地接触引用，程序会跑出NPE而不是错误地执行下去。愈早发现程序的
错误总是好的。但是我们不需要总是如此小心翼翼，清空对象引用只是一种例外而不应该是一种规范。消除过期引用最好的方
式是让包含该引用的变量结束其生命周期，如果你是在最紧凑的作用域范围内定义的每一个变量，这种情形就会自然而然的发
生。
> * 通常，Stack类自己管理内存（manage its own memory），存储池（storage pool）包含了elements数组（对象引用单
元而非对象本身）的元素。活动区域的元素是已分配的（allocated），而数组其余元素则是自由的（free）。但是垃圾回收
器是不知道的；对它来说，elements数组中所有引用都是同等有效。只有开发者自己知道非活动部分是没用的。总的来说，只
要类自己管理内存，我们就应该警惕内存泄露的问题。

> 另一个常见来源是缓存。我们很容易以往缓存中的对象引用，从而使得它不再使用之后很长一段时间任然留在缓存中。
> * 如果需要实现这样的缓存：只要缓存之外有对某个项的键的引用，该项就有意义，那么可以用WeakHashMap代表缓存；当
缓存中的项过期后，它们会自动被删除。注意的是，只有当所要的缓存项生命周期是由外部引用觉得而不是值决定时，WeakHashMap
才有作用。
> * 更常见的是，缓存项的生命周期是否有意义并不容易确定。这种情况下，应该时不时地清楚没用的项。可以是一个后台线
程来完成，或者添加新缓存时处理。LinkedHashMap利用它removeEldestEntry可以很容易实现后一种方案。对于更加复杂的，
必须使用java.lang.ref。

> 第三种情况是监听器和其他回调。例如实现一个API，客户端在这个API中注册回调，却没有显示地取消注册，如果不采取某
些动作，它们就会积聚。解决方法可以是只保存它们的弱引用（weak reference），例如只将它们存为WeakHashMap中的键。

> 内存泄露一般不会有明显的失败，可能会在一个系统潜伏多年，只有通过检查代码或者借助Heap剖析工具（Heap Profiler）
才能找到，因此，能在发生之前预测并阻止是坠吼的。
***********************************************
###7.避免使用终结方法（finalizer）
> 终结方法呐，就不可预料了，也很危险，一般情况是不必要的。使用可能会导致行为不稳定，降低性能以及移植性问题。
 *  终结方法不能保证会被及时执行，而且JVM有延迟终结方法的机制，很可能会造成程序错误地执行。及时执行终结方
     法是垃圾回收算法的一个主要功能，这种算法在不同的JVM中实现大相径庭，一个程序在你的JVM上能正确执行，可能
     到了别人那就会出问题。很少见的情况下，为类提供终结方法，可能会随意地延迟其实例的回收过程，即延迟终结过
     程。
 *  终结方法的执行呐，也要按照基本法，按照Java的法去产生执行。也就是说Java语言规范不仅不保证终结方法执行，
     而且根本不保证他们会被执行。因此不能依赖终结方法来更新重要的持久态。即使System.gc和System.runFinalization
     这两个方法也不保证终结方法一定被执行。
 *  如果未被捕获的异常在终结过程中被抛出，那么异常可以被忽略，同时终结过程也会终止，异常会使对象处于破坏的
     状态（a corrupt state）,一旦另一个线程企图使用破坏的对象，就会发生不可预料的行为。在终结方法中的异常不
     会打印栈，甚至没有警告。
 *  坠痛苦的是，就是终结方法的性能损失非常严重（Severe），创建和销毁对象的时候一下子就......而且这个效率，
     efficiency。
   
> 正确终止的知识呢，我们还是要学习一个，就是这个显示终结啊。Java有个InputStream、OutputStream和java.sql.Connection
的close方法，啊，不知道比你们高到哪里去了！
  *  显示终结方法通常是客户端在每个实例不再有用的时候调用这个方法，而且，该实例必须记录自身终结的状态在一个
  私有域，一旦对象是在终止后调用就检查私有域，并抛出一个IllegalStateException的大新闻。
  *  为确保及时执行，显示终结一般是和try...finally结合使用。
  
> 终结方法的主要就是两个事：
   *  1、当对象的所有者忘记调用前面段落中建议调用的显示终止方法时，可以充当一层安全网（safe net）。尽管不能及时
   调用，但是好过不调用。一旦在终结方法中发现资源仍未关闭，则应采取日志形式给予警告处分。
   *  2、本地对等体（native peer）是一个本地对象，普通对象通过本地方法（native method）委托给本地的一个对象。当
   他的Java对等体被回收的时候，他并不会被回收，因此如果本地对等体拥有必须被及时关闭释放的资源时就需要一个显示终结
   方法。
   
> 终结方法链（finalizer chaining）不会被自动执行，子类覆盖了超类的finalizer，就必须手动执行超类的finalizer，在try
中调用子类的finalizer，在finally中调用超类的finalizer。避免忘记这个操作，可以为每个被终结对象创建一个附加对象，把终
结方法放在一个匿名类中，作用就是终结它的外围实例（enclosing instance）。该匿名类的单个实例就是终结方法守护者（finalizer 
guardian），外围类的每一个实例都会创建一个这样的守卫者。外围实例在它的私有实例域中保存着一个对其终结方法守卫者的唯一
引用，因此守卫者和外围实例可同时启动终结。
````
public class Foo{
    private final Object finalizerGuardian = new Object(){
        @Override
        protected void finalize() throw Throwable(){
            ...//Finalize outer Foo object
        }
    };
}
````
> 公有类Foo并没有终结方法，所以子类是否调用super.finalize不重要了。对于每一个带终结方法的非final公有类，都应该考虑使
用这种方式。
> Generally speaking，除非是safe net（用了记得记录非法用法），或者本地资源，否则尽量不要使用finalizer。即使用了，记得
super.finalize；如果需要结合公有非final类，考虑finalizer guardian。
***************************************
###8.覆盖equals时请遵守通用约定
* 类的每个实例本质是唯一的。eg：Thread。
* 不关心类是否提供了逻辑相等（logical equality）的测试功能。eg：java.util.Random。
* 超类已经覆盖了equals，超类的行为对于子类同样适用。eg：大部分Set继承AbstractSet。
* 类是私有的或是包级私有的，可以确保它的equals永远不会被调用。+
> JavaSE6中equals的规范，equals实现了等价关系（equivalence relation）：
* 自反性，对于非null的引用值x，x.equals(x) == true;
* 对称性，对于非null的引用值x和y，x.equals(y) == y.equals(x) == true;
* 传递性，对于非null的引用值x、y、z，if（x.equals(y) == true && y.equals(z) == true）x.equals(z) == true;
* 一致性，对于非null的引用值x和y，只要没有修改对象中的任何信息，无论多少次x.equals(y)都必须一致地返回true或者一致地返回false;
* 非空性，对于非null的引用值x，x.equals(null) == false;

> 如果说还有一点什么就是 **_复合优于继承_**！这个对拓展的命运有很大的关系。>高质量实现equals的五可奉告：

     1、使用==检查参数是否为该对象的引用；
     2、使用instanceof校验参数是否为正确的类型；
     3、把参数转换为正确的类型；
     4、对于该类中的每个关键域，检查参数中的域是否与该对象中对应的域匹配（说白了就是比较必要的字段= =！），但是这个比较的顺序啊
     还有非空判断啊这些很重要，会对方法的性能有影响；
     5、编写完equals后请检查对称、传递、一致性；
> 三个代表的告诫：

    1、覆盖equals时总是要覆盖hashCode；
    2、不要想着让equals智能到考虑得很完美；
    3、不要把equals声明的Object对象替换为别的类型；
    
**************************************************
###9.重写equals时总要重写hashCode
> 如果只是重写了equals而没有重写hashCode，就会违背hashCode的通用约定，从而导致该类无法结合所有基于散列的集合一起正常运作，
例如HashMap，HashTable。

>JavaSE6中的规范：
* 在应用程序执行期间，只要对象的equals方法的比较操作所用到的信息没被修改，那么对着同一个对象调用多次，返回的hashCode都必须是同一个
整数。~~在同一个应用程序的多次执行过程中，每次执行所返回的整数可以不一致~~（excuse me？？？）。
* 如果两个对象的equals比较是相等的，那么他们的hashCode必定是同一个整数。
* 接上一条，如果两个对象equals不是相等的，他们的hashCode不一定不同，可能任然是同一个整数。但是我们应该知道，给不相等的对象不同的整
数结果，有可能提高散列表（hash table）的性能。

> 一个好的散列函数倾向于为不相等的对象产生不相等的散列码。同时，对于不可变的类并且散列码计算开销较大的时候，就应该考虑把散列
码缓存在对象内部，而不是每次请求都重新计算。如果，你觉得这种类型的大多数对象会被用作散列键，就因该创建实例的时候创建散列码。
否则，可以使用“延迟初始化”散列码，一直到hashCode第一次被调用的时候才初始化，如下：
````
private volatile int hashCode;
@Override public int hashCode(){
    int result = hashCode;
    if(0 == result){
        ...//produce a hashCode by rule
    }
    return result;
}
````
> 不要试图从散列码计算中排除掉一个对象的关键部分来提高性能。效果不一定好，还可能导致散列表失效。

*********************************************
###9.1 踩坑
> 今天踩了个坑，newInstance的方法在没无参构造器下使用报错，顺便搜了一波new和newInstance，两者创建对象方式不一样，从jvm的角度看，
我们使用new的时候，这个new的类可以没有被加载；但是使用newInstance必须保证1、这个类已经被加载；2、这个类已经连接。这两个步骤可以用
Class.forName达成。可以输newInstance是把new分成两步，先加载，后实例化；同时，newInstance是弱类型，效率低，只能调用无参构造，而new
是强类型，相对效率高，可以调用任何public构造。

************************************************
###10.是重要覆盖toString
####简介，信息丰富，易于阅读
> 提供好的toString实现可以使类用起来更加舒服，toString应该返回最值得关注的信息

*************************************************
###11.谨慎覆盖clone


  



