package com.dennis.classloader;

/**
 * 描述：跟加载器与其它类加载器的区别
 *
 * 內建于JVM中的启动类加载器会加载java.lang.ClassLoader以及其他的Java平台类，
 * 当JVM启动时，一块特殊的机器码会运行，它会加载扩展类加载器与系统类加载器，
 * 这块特殊的机器码叫做启动类加载器(Bootstrap)。
 *
 * 启动类加载器并不是Java类，而其他的加载器则都是Java类，
 * 启动类加载器是特定于平台的机器指令（特殊的机器码），它负责开启整个加载过程。
 *
 * 所有类加载器(除了启动类加载器)都被实现为Java类。不过，总归要有一个组件来加载第一 个Java类加载器,从而让整个加载过程能够顺利执行
 * 进行下去，加载第一个纯Java类加载器就是启动类加载器的职责。
 *
 * 启动类加载器还会负责加载供JRE正常运行所需要的基本组件，这包括java . util与java. lang包中的类等等。
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/26 20:45
 */
public class ClassLoaderTest09 {
    public static void main(String[] args) throws Exception {
        ClassLoaderTest05 myClassLoader = new ClassLoaderTest05("myClassLoader");
        Class<?> test01 = myClassLoader.loadClass("com.dennis.classloader.ClassLoaderTest01");

        ClassLoader loader01 = test01.getClassLoader();
        System.out.println(loader01); // com.dennis.classloader.ClassLoaderTest05@74a14482
        ClassLoader loader02 = loader01.getClass().getClassLoader();
        System.out.println(loader02); //sun.misc.Launcher$AppClassLoader@18b4aac2
        ClassLoader loader03 = loader02.getClass().getClassLoader();
        System.out.println(loader03); //null ==> 跟加载器
    }
}
