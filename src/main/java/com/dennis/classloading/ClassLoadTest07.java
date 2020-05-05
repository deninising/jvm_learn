package com.dennis.classloading;

/**
 * 描述： 反射是对类的一种主动使用，而ClassLoader.load("xxx.xxx.xxx")只是对类的.class文件的一种加载而非主动使用，所以不会初始化
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/23 17:51
 */
public class ClassLoadTest07 {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        // 只是显示的加载类的.class文件到虚拟,并不是对类的主动使用,因此类不会初始化
        Class<?> myClazz = systemClassLoader.loadClass("com.dennis.classloading.MyClass");
        System.out.println(myClazz);
        System.out.println("===================");
        // 反射获取类的class object,是对类的主动使用,会使类进行初始化
        Class<?> aClass = Class.forName("com.dennis.classloading.MyClass");
        System.out.println(aClass);
    }
}

class MyClass {
    static {
        System.out.println("MyClass has been initialized");
    }
}
