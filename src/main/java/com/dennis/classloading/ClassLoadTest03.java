package com.dennis.classloading;

/**
 * 描述：运行期常量与编译期常量的区别：
 * 【1】运行期常量不会进入常量池，但是编译期常量会存储到常量池中
 * 【2】运行期常量要求定义该常量的类或者接口执行初始化，而编译期只需要执行类或者接口的加载
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/22 17:03
 */
public class ClassLoadTest03 {
    public static void main(String[] args) {
        /**
         * 运行期常量不会像编译期常量那样，在编译期间就会将常量值存储到调用该常量的方法的类的常量池中去，
         * 该常量值在编译期间是未知的，只有在程序的运行期间去主动调用定义该常量的类并获取常量值
         * 因此，定义运行期常量的类会被初始化且该常量也不会存储到常量池中
         */
        System.out.println(Parent03.UUID);
    }
}

class Parent03 {
    // 此为一运行期常量，在编译阶段无法确定
    public static final String UUID = java.util.UUID.randomUUID().toString();
    static {
        System.out.println("class Parent has been initialized!");
    }
}
