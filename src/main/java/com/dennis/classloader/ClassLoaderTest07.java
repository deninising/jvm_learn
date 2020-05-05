package com.dennis.classloader;

/**
 * 描述：
 * 测试一：将Cat.class置于子加载器的指定的.class文件目录下，使用自定义类加载器加载Cat类,将Dog.class置于classpath下将有appClassLoader加载
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/25 21:26
 */
public class ClassLoaderTest07 {

    public static void main(String[] args) throws Exception {
        ClassLoaderTest05 loader = new ClassLoaderTest05("loader");
        Class<?> clazzCat = loader.loadClass("com.dennis.classloader.Cat");
        clazzCat.newInstance();// 主动使用cat类,其构造器中会去访问Dog类的Class对象

        System.out.println("========================");
        Class<?> clazzDog = loader.loadClass("com.dennis.classloader.Dog");
        clazzDog.newInstance(); // 主动使用Dog类,其构造器中会去访问Cat类的Class对象
    }
}

