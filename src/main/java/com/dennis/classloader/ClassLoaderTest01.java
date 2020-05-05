package com.dennis.classloader;

/**
 * 描述：查看一个类被加载时，所用到的具体类加载器
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/23 16:47
 */
public class ClassLoaderTest01 {

    public ClassLoaderTest01() {
    }

    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> stringClazz = Class.forName("java.lang.String");
        System.out.println(stringClazz.getClassLoader());       // 采用根加载器加载String类

        Class<?> cClazz = Class.forName("com.dennis.classloader.C");
        System.out.println(cClazz.getClassLoader());            //采用AppClassLoader加载C类
    }
}
class C {

}

