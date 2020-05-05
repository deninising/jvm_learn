package com.dennis.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 描述： 自定义类加载器
 * <p>
 * 重要概念：定义类加载器：是指真真正正上将当前类加载到了当前jvm虚拟机中的那个加载器（定义类加载器只能有一个）
 * 初始类加载器：是指原理上能够将当前类加载到jvm虚拟机中的所有加载器（可以有多个）
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/24 17:27
 */
public class ClassLoaderTest04 extends ClassLoader {
    private String classLoaderName;
    private final String fileExtension = ".class";

    public ClassLoaderTest04(String classLoaderName) {
        super(); // 默认parentClassLoader为systemClassLoader
        this.classLoaderName = classLoaderName;
    }

    public ClassLoaderTest04(ClassLoader parent, String classLoaderName) {
        super(parent); //显示指定父类加载器
        this.classLoaderName = classLoaderName;
    }

    @Override
    public String toString() {
        return "[" + this.classLoaderName + "]";
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] bytes = this.loadClassData(name);
        return this.defineClass(name, bytes, 0, bytes.length);
    }

    // 将.class文件加载成byte[]资源
    private byte[] loadClassData(String resourceName) {
        System.out.println("method loadClassData defined in ClassLoaderTest04 has been invoked");
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        byte[] resultData = null;
        int data;
        resourceName = resourceName.replace(".", "/");
        try {
            fis = new FileInputStream(new File(resourceName + fileExtension));
            baos = new ByteArrayOutputStream();
            while ((data = fis.read()) != -1) {
                baos.write(data);
            }
            resultData = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null && baos != null) {
                try {
                    fis.close();
                    baos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return resultData;
    }

    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoaderTest04 myClassLoader = new ClassLoaderTest04("ClassLoaderTest04");
        Class<?> clazz = myClassLoader.loadClass("com.dennis.classloader.ClassLoaderTest01");
        ClassLoader classLoader = clazz.getClassLoader(); // 由定义类加载加载该类，本例中用的时appClassLoader(双亲委托机制决定的)
        System.out.println(classLoader); // AppClassLoader
        System.out.println(clazz);
        Object instance = clazz.newInstance();
        if (instance instanceof ClassLoaderTest01) {
            System.out.println(instance);
        } else {
            System.out.println("类型不匹配");
        }
    }

}

