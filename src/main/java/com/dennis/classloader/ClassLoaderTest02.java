package com.dennis.classloader;

/**
 * 描述： 通过当前类加载器应获取所有父加载器
 * sun.misc.Launcher$AppClassLoader@18b4aac2 =====> 系统加载器
 * sun.misc.Launcher$ExtClassLoader@4554617c =====> 扩展加载器
 * null                                      =====> 根加载器
 * @author Dennis
 * @version 1.0
 * @date 2020/4/23 20:13
 */
public class ClassLoaderTest02 {
    /**
     * 通过结果可以看出，当前类的类加载器为AppClassLoader,即：加载该类的类加载器为AppClassLoader
     * 虽然有父加载器ExtClassLoader,甚至祖父加载器BootClassLoader,但是当前类位于classPath下,而
     * ExtClassLoader(LoadJRE\lib\ext\*.jar或者Djava.ext.dirs指定目录下的jar包),
     * BootClassLoader(LoadJRE\lib\rt.jar或则Xbootclasspath选项所指定目录下的的jar包)
     * 均加载不了classPath下的.class文件,所以当前类又交回给AppClassLoader进行类的加载
     */
    public static void main(String[] args) {
        Class<?> clazz = ClassLoaderTest02.class;
        ClassLoader classLoader = clazz.getClassLoader();
        System.out.println(classLoader);
        while (classLoader != null ) {
            classLoader = classLoader.getParent();
            System.out.println(classLoader);
        }
    }
}
