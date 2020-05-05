package com.dennis.classloader;

import java.lang.reflect.Method;

/**
 * 描述：命名空间和双亲委托机制的作用及意义
 * 【1】确保java核心库的类型安全：所有java应用都至少会引用java.lang.Object类，运行期间，这个类将被加载到虚拟机的内存中去。如果这个类被自定义的
 * 类加载器所加载了，那么很可能在java虚拟机中存在多个版本的java.lang.Object类，且这些类是互相不兼容的，互相不可见的(类加载器的命名空间使然)
 * 因此，加载类加载器的双亲委托机制的作用下，java核心类库中的类的加载都是由启动类加载器同一完成的，从而确保了java应用程序所使用的都是同一个版本
 * 的Java核心类库，他们之间相互兼容，从而保证java核心库的类型安全
 * 【2】可以确保java核心类库的类不被自定义的类所代替
 * 【3】互为平级的类加载器或是不存在包含等级的类加载器，是可以为相同binaryName的类创建各自的命名空间，如此以来在jvm中这些类之间互相不可访问的（即使
 * 他们的binaryName相同看似为同一个类）互不兼容的。到这类技术可在多框架中得到很好的实际应用
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/26 15:25
 */
public class ClassLoaderTest08 {
    public static void main(String[] args) throws Exception {
        ClassLoaderTest05 myLoader01 = new ClassLoaderTest05("myLoader01");
        ClassLoaderTest05 myLoader02 = new ClassLoaderTest05("myLoader02");

        // 当使用自定义类加载加载时,以下两个类对象将位于两个不同的命名空间中,
        // 若通过委托机制被appClassLoader加载时,则位于同一个命名空间中,
        // 若为于同一命名空间中,相同binaryName的类只会被加载一次,即pigClazz01 = pigClazz02
        Class<?> pigClazz01 = myLoader01.loadClass("com.dennis.classloader.Pig");
        Class<?> pigClazz02 = myLoader02.loadClass("com.dennis.classloader.Pig");

        System.out.println(pigClazz01 == pigClazz02);

        Object pig01 = pigClazz01.newInstance(); // 用于调用方法
        Object pig02 = pigClazz02.newInstance(); // 用于传参

        Method setPig = pigClazz01.getMethod("setPig", Object.class);
        setPig.invoke(pig01, pig02);// 调用pig01的setPig方法,传递的参数为pig02
    }
}
