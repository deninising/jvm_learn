package com.dennis.classloading;

/**
 * 描述：类的加载，连接（验证二进制文件正确性、准备静态属性设置默认值、解析符号引用改为直接地址引用）、初始化（静态变量赋初始值）
 *      -XX：+TraceClassLoading,用于追踪类的加载信息并打印
 *      -XX：-<options> 关闭jvm中options选项
 *      -XX：+<options> 开启jvm中options选项
 *      -XX：<options>=<value> 开启jvm中options选项赋值
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/22 9:53
 */
public class ClassLoadTest01 {
    public static void main(String[] args) {
        /*
         *  1、对静态属性的调用，只有直接定义了它的类才会被初始化
         *  2、当一个类被初始化时，其所父类也将被初始化
         *  3、父类static块中的代码优先执行
         */
//        System.out.println(Child.str); //问题：Child类没有被初始化但是其类信息有没有被加载呢？-XX：+TraceClassLoading,用于追踪类的加载信息并打印
        System.out.println(Child.str1);
    }
}

class Child extends Parent01 {

    public static String str1 = "hello child!";

    // 类的静态代码块在类的初始化阶段执行
    static {
        System.out.println("child has been initialized!");
    }
}

class Parent01 {
    public static String str = "hello parent!";

    static {
        System.out.println("parent has been initialized!");
    }
}
