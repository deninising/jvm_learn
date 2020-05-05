package com.dennis.classloader;

import java.io.IOException;
import java.net.URL;
import java.util.Enumeration;

/**
* 描述：通过资源名称resourceName获取到.class文件所在位置url
* @author   Dennis
* @date     2020/4/23 21:04
* @version  1.0
*/
public class ClassLoaderTest03 {
    public static void main(String[] args) throws IOException {
        // 通过调用类加载器加载.class文件和资源文件的线程来获取上下文类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        // 资源名称
        String resourceName = "com/dennis/classloader/ClassLoaderTest03.class";
        // 获取定位资源文件的URLs
        Enumeration<URL> urls = contextClassLoader.getResources(resourceName);
        while (urls.hasMoreElements()){
            System.out.println(urls.nextElement());// file:/D:/Code/github/jvm_learn/target/classes/com/dennis/classloader/ClassLoaderTest03.class
        }
    }
}