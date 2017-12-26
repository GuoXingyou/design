package com.jdk.jdk8;

import com.google.common.collect.Lists;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author: Jax
 * @Email: guoxingyou@xjiye.com
 * @Date: 2017/12/22/10:52
 * @Desc:
 **/
public class StreamAPITest {

    private static List<Integer> integerList = Arrays.asList(18, 28, 38, 48, 58, 68);

    private static List<String> stringList = Arrays.asList("hello", "world", "effective", "java", "excited");

    public static void main(String[] args) {
        integerList.forEach(integer -> System.out.println(integer));
    }

    /**
     * 中间件，一个Stream可以调用0到多个Intermediate类型操作，每次调用会对Stream做一定的处理，返回一个新的Stream，
     * 这类操作都是惰性化的（lazy），就是说，并没有真正开始流的遍历。
     * 常用操作：map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel
     */
    public static class Intermediate {
        public static void main(String[] args) {
            //filter 过滤操作
            stringList.stream().filter(s -> s.startsWith("j"));

            //map 遍历和转换操作，实用性很高
            stringList.stream().map(String::toLowerCase);

            //flatMap 将流展开
            List<String> list1 = Lists.newArrayList();
            list1.add("aa");
            list1.add("bb");
            List<String> list2 = Lists.newArrayList();
            list2.add("cc");
            list2.add("dd");
            Stream.of(list1,list2).flatMap(str -> str.stream()).collect(Collectors.toList()).forEach(s -> System.out.println(s));

            //limit 提取子流，入参为顺位个数
            stringList.stream().limit(3).forEach(s -> System.out.println(s));

            //skip 跳过 入参为需要跳过的顺位元素个数
            stringList.stream().skip(3).forEach(s -> System.out.println(s));

            //peek 官方的例子，peek，偷瞄的意思，
            //This method exists mainly to support debugging, where you want to see the elements as they flow past a certain point in a pipeline:
            //这个方法存在的意义主要是支持调试，当这些元素在传递中流过某一点时你想看到他们的位置
            //说白了就是看当前流操作的过程，
            Stream.of("one", "two", "three", "four")
                    .filter(e -> e.length() > 3)
                    .peek(e -> System.out.println("Filtered value: " + e))
                    .map(String::toUpperCase)
                    .peek(e -> System.out.println("Mapped value: " + e))
                    .collect(Collectors.toList());


            //去重
            Stream.of("one", "two", "three", "one").distinct().forEach(s -> System.out.println(s));

            //排序 无参，默认自然排序方式，也可以自己定义Comparator入参排序
            Stream.of(567,254,875,666).sorted().forEach(integer -> System.out.println(integer));
            Stream.of("one", "two", "three", "four").sorted(Comparator.comparing(String::length)
                    .reversed()).forEach(s -> System.out.printf(s));

            /****************************************************************************************/
            //parallel 转为并行流,谨慎使用
            //使用串行方式去遍历时，每个 item 读完后再读下一个 item，可以参考上面peek的例子，能看到是一个元素处理完所有操作再去处理下一个元素；
            // 而使用并行去遍历时，数据会被分成多个段，其中每一个都在不同的线程中处理，然后将结果一起输出。
            //parallel具有平行处理能力，采用分治的方式，江大任务切成多个小任务

            /**
             * 想要深入了解parallel需要了解ForkJoinPool
             * ForkJoin框架是从jdk7中新特性,它同ThreadPoolExecutor一样，也实现了Executor和ExecutorService接口。
             * 它使用了一个无限队列来保存需要执行的任务，而线程的数量则是通过构造函数传入，如果没有向构造函数中
             * 传入希望的线程数量，那么当前计算机可用的CPU数量会被设置为线程数量作为默认值。
             * ForkJoinPool主要用来使用分治(Divide-and-Conquer Algorithm)来解决问题，充分利用多核CPU，其中还有
             * 工作窃取（work-stealing）算法【指某个线程从其他队列里窃取任务来执行】
             *
             * 坑：
             * lambda的执行并不是瞬间完成的,所有使用parallel streams的程序都有可能成为阻塞程序的源头,并且在执行过
             * 程中程序中的其他部分将无法访问这些workers,这意味着任何依赖parallel streams的程序在什么别的东西占用
             * 着common ForkJoinPool时将会变得不可预知并且暗藏危机。
             *
             * 对于parallel需要考虑到：
             * 1、面对的问题是什么，数据量的大小，计算的特点，并非所有问题都用并行就能提高性能
             * 2、对于任务独立，且不会对公有对象的变量或状态进行修改操作，就可以考虑并行
             * 3、并行任务的顺序是不确定的
             */
            stringList.parallelStream();
            Stream.of("one", "two", "three", "four").parallel()
                    .filter(e -> e.length() > 3)
                    .peek(e -> System.out.println("Filtered value: " + e))
                    .map(String::toUpperCase)
                    .peek(e -> System.out.println("Mapped value: " + e))
                    .collect(Collectors.toList());
        }
    }


    /**
     * 终端，一个Stream只能执行一次terminal 操作，而且只能是最后一个操作，执行terminal操作之后，Stream就被消费掉了，
     * 并且产生一个结果。
     * 常用操作：forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、
     * noneMatch、 findFirst、 findAny
     */
    public static class Terminal {
        public static void main(String[] args) {
            //forEach
            integerList.stream().forEach(integer -> System.out.println(integer));

            //forEachOrdered 如果希望顺序执行并行流，请使用该方法
            /**
             * 如果forEachOrdered()中间有其他如filter()的中介操作，会试着平行化处理，然后最终forEachOrdered()会以原
             * 数据顺序处理，因此，使用forEachOrdered()这类的有序处理,可能会（或完全失去）失去平行化的一些优势，实
             * 际上中介操作亦有可能如此，例如sorted()方法。
             */
            integerList.stream().parallel().forEachOrdered(integer -> System.out.println(integer));

            //转换数组
            integerList.stream().toArray();

            /**聚合操作 1、没指定初始值，从数组第一个数开始执行;2、有初始值，从初始值开始执行;
            * 3、第三个函数，为了避免竞争，对于reduce线程都会有独立的result，combiner的作用在于合并每个线程的result
            * 得到最终结果，因为第三个参数用来处理并发操作，如何处理数据的重复性，应多做考虑，否则会出现重复数据。
            */
            System.out.println(integerList.stream().reduce((acc, item) -> acc + item).get());
            System.out.println(integerList.stream().reduce(-8,(acc, item) -> acc + item).intValue());
            System.out.println(integerList.parallelStream().reduce(-8,(acc, item) -> acc + item,(acc, item) -> acc + item).intValue());

            //转list 同理还有set
            integerList.stream().collect(Collectors.toList());

            //求极值
            integerList.stream().collect(Collectors.minBy(Comparator.comparing(Integer::intValue)));
            IntStream.of(1,2,3,4).min();

            //统计
            integerList.stream().count();

            //判断是1、否存在匹配...2、全部是否匹配...3、是否不存在匹配...
            boolean b = stringList.stream().anyMatch(s -> s.startsWith("j"));
            b = stringList.stream().allMatch(s -> s.startsWith("j"));
            b = stringList.stream().noneMatch(s -> s.length()>5);

            //1、找到条件符合的第一个就返回；2、找到任意一个匹配的就返回
            String tag = stringList.stream().filter(s -> s.indexOf("a") > -1).findFirst().get();
            tag = stringList.stream().filter(s -> s.startsWith("j")).findAny().get();


        }
    }

    /**
     * 关于Stream的适用场景，网上也有很多比较Stream和for loop的，看得出来，简单场景下，stream因为自身的一些创建及传输
     * 损耗，效率不会比for loop高，它更适合存在对于集合进行多操作，任务较重，逻辑复杂，需要并发和函数式编程提高代码可
     * 读性的场景
     */

}
