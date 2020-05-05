package com.dennis.classloader;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * 描述：对ClassLoaderTest04的改进,新定义一个类加载器加载指定目录
 * （classPath:folder/classes/com/dennis/classloader/）下
 * 的.class文件(当父加载器无法通过binaryName成功加载指定类文件时)
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/25 11:06
 */
public class ClassLoaderTest05 extends ClassLoader {
    private static final String CLAZZ_PATH = "folder/classes/";
    private static final String FILE_EXTENSION = ".class";
    private String classLoaderName = null;

    public ClassLoaderTest05(String classLoaderName) {
        super();
        this.classLoaderName = classLoaderName;
    }

    public ClassLoaderTest05(ClassLoader parent, String classLoaderName) {
        super(parent);
        this.classLoaderName = classLoaderName;
    }

    // 从规定好了目录中加载指定的.class文件
    private byte[] loadClassData(String binaryName) {
        FileInputStream fis = null;
        ByteArrayOutputStream baos = null;
        byte[] resultData = null;
        int data;

        binaryName = binaryName.replace(".", "/");
        File file = new File(CLAZZ_PATH + binaryName + FILE_EXTENSION);

        try {
            baos = new ByteArrayOutputStream();
            fis = new FileInputStream(file);
            while ((data = fis.read()) != -1) {
                baos.write(data);
            }
            resultData = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
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

    //重写父类的findClass方法，当父类加载器在原有的类文件加载路径中无法加载到.class文件时，便会调用自定义的类加载器中的findClass方法加载类文件
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        System.out.println("method findClass() override in ClassLoadTest05 is invoked !!!");
        byte[] data = loadClassData(name);
        return defineClass(name, data, 0, data.length);
    }

    // 测试：【1】将classpath下的.class文件剪切到自定义类指定的.class存放目录下
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {

        System.out.println("==========================用自定义类加载器实例加载器指定类文件===========================");
        ClassLoaderTest05 loader01 = new ClassLoaderTest05("loader01");
        Class<?> clazz01 = loader01.loadClass("com.dennis.classloader.ClassLoaderTest01");
        System.out.println(clazz01 + ":" + clazz01.hashCode()); // 被加载类的Class 对象：class com.dennis.classloader.ClassLoaderTest01:356573597
        System.out.println(clazz01.getClassLoader()); // 获取定义类加载器ClassLoader 对象：com.dennis.classloader.ClassLoaderTest05@4554617c

        System.out.println("==========================用同一个类加载器实例对同一个类进行第二次加载===========================");
        Class<?> clazz02 = loader01.loadClass("com.dennis.classloader.ClassLoaderTest01");
        System.out.println(clazz02 + ":" + clazz02.hashCode()); // 被加载类的Class 对象：class com.dennis.classloader.ClassLoaderTest01:356573597
        System.out.println(clazz02.getClassLoader());// 获取定义类加载器ClassLoader 对象：com.dennis.classloader.ClassLoaderTest05@4554617c

        System.out.println("==========================用另一个类加载器实例对同一个类进行第二次加载===========================");
        ClassLoaderTest05 loader02 = new ClassLoaderTest05("loader02");
        Class<?> clazz03 = loader02.loadClass("com.dennis.classloader.ClassLoaderTest01");
        System.out.println(clazz03 + ":" + clazz03.hashCode()); // 被加载类的Class 对象：class com.dennis.classloader.ClassLoaderTest01:2133927002
        System.out.println(clazz03.getClassLoader());// 获取定义类加载器ClassLoader 对象：com.dennis.classloader.ClassLoaderTest05@677327b6

        System.out.println("==========================将load01做为loader03的父加载器且同一个类进行第二次加载===========================");
        ClassLoaderTest05 loader03 = new ClassLoaderTest05(loader01, "loader03");
        Class<?> loader04 = loader03.loadClass("com.dennis.classloader.ClassLoaderTest01");
        System.out.println(loader04 + ":" + loader04.hashCode()); // 被加载类的Class 对象：class com.dennis.classloader.ClassLoaderTest01:356573597
        System.out.println(loader04.getClassLoader());// 获取定义类加载器ClassLoader 对象：com.dennis.classloader.ClassLoaderTest05@4554617c

        //结论：
        // 在同一个命名空间中：同一个类文件只能加载一次
        // 在不同的命名空间中：同一个类文件可被加载多次
        // 类加载器的命名空间由所有父类加载器实例和当前类加载实例所确定
    }

}
