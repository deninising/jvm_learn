package com.dennis.classloader;
import java.util.concurrent.TimeUnit;

/**
 * 描述：手动调用gc()方法卸载一个类:-XX：+TraceClassUnloading
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/25 19:02
 */
public class ClassLoaderTest06 {
    public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
        ClassLoaderTest05 loader01 = new ClassLoaderTest05("loader01");
        loader01.loadClass("com.dennis.classloader.ClassLoaderTest01"); //【注意】需将.class文件手动剪切到自定义类加载的类加载路径

        // 【1】栈空间变量“loader01"放弃对自定义加载器对象的引用,此时类加载器对象到根路径不可达，
        // 【2】类加载器对象对它所加载的Class对象也有一个引用，此时对于这些Class对象而言到根路径也不可达
        // 因此调用system.gc()时，已加载的Class对象： ClassLoaderTest01 将被卸载
        loader01 = null;
        System.gc();
        TimeUnit.SECONDS.sleep(50);
    }
}
