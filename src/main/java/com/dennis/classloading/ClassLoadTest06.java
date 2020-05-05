package com.dennis.classloading;

/**
 * 描述：类加载过程中，准备阶段的重要意义
 * 【知识要点】
 * 1、类初始化前：必定完成准备阶段，所有属性值JVM自动设置为默认值
 * 2、类初始时机：首次对类进行主动调用
 * 3、类初始次数：初始化只执行一次
 * 4、类初始化顺序：由上至下顺序执行
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/22 20:06
 */
public class ClassLoadTest06 {
    public static void main(String[] args) {
        Singleton singleton = Singleton.getInstance();
        System.out.println("counter1:" + singleton.getCounter1());
        System.out.println("counter2:" + singleton.getCounter2());
    }
}

class Singleton {
    /**
     * 准被阶段所有的属性值均为默认值：counter1 = 0,counter2 = 0,singleton = null;
     * 准备阶段完成后对象才有可能被初始化，且初始化只能执行一次
     */
//    private static int counter1;
//    private static int counter2 = 0;

    /**
     * 类初始化时被赋值为 1
     */
    private static int counter1 = 1;


    /**
     * 对该类的主动调用，将首次也是唯一一次对类进行初始，构造器的初始化在static{}之前执行
     */
//    private static Singleton singleton = new Singleton();

    static {
        System.out.println("class Singleton is been initialized");
    }

    // 构造器初始化阶段执行
    private Singleton() {
        System.out.println("constructor is invoking");
        counter1++;
        counter2++;
        System.out.println(counter1);
        System.out.println(counter2);
    }

    /**
     * 对该类的主动调用，将首次也是唯一一次对类进行初始,构造器的初始化在static{}之后执行
     */
    private static Singleton singleton = new Singleton();

    /**
     * 类初始化时被赋值为 0
     */
    private static int counter2 = 0;

    public static Singleton getInstance() {
        System.out.println("getInstance is been invoking");
        return singleton;
    }

    public int getCounter1() {
        return counter1;
    }

    public int getCounter2() {
        return counter2;
    }

}
