###官方jdk10特性
> ~~本文根据原文档翻译以及笔者自己理解所写，如果编辑中出现了偏差，各位老铁可以给我提出来我来负责任。~~  

286：局部变量类型推断  
296：将JDK的多个代码仓库合并到一个代码仓库中
304：垃圾收集器接口  
307：并行全GC G1  
310：应用程序类数据共享  
312：线程本地的握手  
313：移除头生成工具（javah）  
314：额外的Unicode语言标签扩展 
316：替代内存设备上的堆分配  
317：基于java的JIT编译器  
319：根证书  
322：基于时间的版本控制  

*  286: 
    * 总结：增强java语言初始化局部变量申明的拓展类型推断
    * 目标：
    > 希望通过允许开发者省略不必要的局部变量类型的显示声明，以此减少java代码相关的必要操作
    来提高java开发体验（增强UX？），同时承诺保持静态语言的安全性。例如：
    ````
        var list = new ArrayList<String>();  // infers ArrayList<String>
        var stream = list.stream();          // infers Stream<String>
    ````
    > 这种解决方式江局限于初始化局部变量，增强循环的index和局部for循环的申明（不然想想就感觉要爆炸。。。），
    emmm......我的理解应该是这样的：
    ````
        var list = new ArrayList<String>();
        for(var s : list){
            System.out.println(s);
        }
        for(var i = 0;i<list.length();i++){
            System.out.println(list[i]);
        }
    ````
    > 这江不会影响到方法、构造器、catch等规范，也不会对方法返回类型还有任何别的变量申明有影响。
    * 成功标准：
    > 数量上，希望正式代码库中的局部变量声明有很大一部分可以转换成使用这种功能而获取一个适当的类型；质量上，
    希望局部变量推断的局限性以及这些局限性的原因能被一个典型的用户所接受。（显然这个不会很容易达到，我们不仅
    不能为所有的局部变量的类型推断合理，而且有些用户理想的类型推断完全取决于他们自己的意思，他们自己心里的B数比
    强制的算法更能准确机智地解释这个变量），但是我们任然在寻求一个方法，能清晰地推断出在这一行上这个特殊的构造
    （应该是指推断的语法）出现的原因并且通过编译器诊断可以有效地把此处出现的这个语法与用户代码中的复杂性相结合，
    而不是因语言而随意限制该语法。
    * 动机：
    > 开发者经常抱怨Java编程中太过模板化导致显得陈词滥调的代码，（代码模板化给人一种钦定的感觉？蛤？），对于局
    部变量的显示声明通常认为不是必要的，甚至是多余的；优秀的代码，我们应该能够从变量名就做到见名知意。  
    为每一个显示类型变量提供声明的必要无意中促使开发者使用过于复杂的表达式，这些意义不大的声明语法，对于把复杂
    的链式或是嵌套表达式分成简单的表达式产生了一些阻碍。  
    几乎所有其他流行的静态语言，都已经支持了局部变量推断：C艹（auto），C井（var），Scala（var/val），Go（declaration
    with : =）,emmm......那么所以java没有所谓的典范，不按照静态语言的基本法，所以我们要根据......去产生。  
    然后嘛，从7开始集合类开始提供的类型推理（List<String> list = new ArrayList<>();）到8利用lambda形式展开的嵌
    套拓展推理和链式泛型方法调用，使得很多开发人员开始习惯中间类型的推断，真正的粉丝哪怕只是看到lambda代码
    也知道他后面代表的是什么:
    ````
        int maxWeight = blocks.stream()
                              .filter(b -> b.getColor() == BLUE)
                              .mapToInt(Block::getWeight)
                              .max();
    ````
    > 坠吼就是我什么也不说，中间件类型的Stream<Block>和IntStream以及lambda的b规范，没有明确清晰地出现在源代码中
    我们也知道那代表什么类型；局部变量类型推断也允许在不太紧密的结构化API中产生类似的效果，本地变量的很多用法本质
    上是chains（链结构），这有助于类型的推断：
    ````
        var path = Paths.get(fileName);
        var bytes = Files.readAllBytes(path);
    ````
    * 描述：
    > 关于标识符var，不是一个单纯的关键字，相反，它是一个保留类型名或者上下文敏感的关键字；这意味着使用var做变量名、
    方法名、包名的代码不会受影响，作为类或者接口名才会受到影响（但是这种命名很少见）。缺少初始化的局部变量、多个声明
    的变量，有额外数组维度层次（？？？）或者引用为正在初始化的变量是不能使用的。Rejecting locals without initializers 
    narrows the scope of the feature, avoiding "action at a distance" inference errors, and only excludes a small 
    portion of locals in typical programs.（emmm......？？？）
    
* 296 
    > emmm......JDK的不同功能分布在不同代码仓库中，jdk9就有root，corba，hotspot，jaxp，jaxws，jdk，langtools和
     nashorn；其中hotspot是虚拟机实现代码，jdk是Java类库和相关工具，langtools是javac等工具，nashorn是JavaScript
     引擎。把众多库整合到一个库其实就是简化开发，增加FX来源的JDK的分支库不在该计划中。
     
* 304
    * 使热点内部GC编码更易模块化
    * 在不干扰当前代码基础的情况下更加容易地添加一个新GC到热点中
    * 从JDK的建立中更容易地排除GC
    
    > 通过引入干净的垃圾收集器（GC）接口来改进不同垃圾收集器的源代码隔离性。
    
* 307
    > 通过使GC完全并行来改善G1最坏情况的延迟
    
* 310
    > 拓展类数据共享（CDS）特性，允许应用程序类共享的存档中来改善启动和占用。简单来说，Java 安装程序会把 
    rt.jar中的核心类提前转化成内部表示，转储到一个共享的文件中（shared archive）。多个Java进程（或者说 
    JVM实例)可以共享这部分数据。现在，希望更近一步，支持应用类的数据共享。
    
* 312
    > 介绍一种方法（安全点机制修改），在没有执行全局的虚拟机安全点的线程中执行一个回调线程。使它尽可能地低
    损耗来阻止单个的线程，而不是所有线程或不阻止。
    
* 313
    > 从JDK8开始，javah的功能已经集成到了javac中。所以，javah可以删掉了。关于[javah的作用](https://baike.baidu.com/item/javah/10711498?fr=aladdin)
    
* 314
    > 增强 java.util.Locale和相关API，实现BCP 47语言标签中额外的 Unicode 扩展（最初在jdk7加入）
    
* 316
    > 使HotSpot虚拟机在另一个存储设备配置java对象堆，如nv-dimm，由用户指定。随着 NV-DIMM 越来越便宜，
    未来的系统可能会搭载异构内存架构。
    
* 317
    > 使基于java的JIT编译器，Graal，作为在Linux/x64平台实验JIT编译器使用。Graal 也是 Java 9中引入的AOT编译器的基础。
   
* 319
    > 提供一组默认的根证书颁发机构（CA）在JDK的证书。为了使OpenJDK构建更吸引开发者，并减少差异，建立与Oracle的JDK版本。
    
* 322
    > 修改java SE平台版本字符串格式和JDK，以及相关的版本信息，为现在和未来的时间释放模型。  
    > 最终的命名机制：$FEATURE.$INTERIM.$UPDATE.$PATCH；$FEATURE，每次版本发布加 1，不考
    虑具体的版本内容。（之前的主版本号部分）2018年3月的版本是JDK 10，9月的版本是JDK11，依此类推。
    $INTERIM，中间版本号，在大版本中间发布的，包含问题修复和增强的版本，不会引入非兼容性修改。