package com.dennis.classloading;

/**
 * 描述：final关键字的本质作用，以及相关助记符
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/22 14:52
 */
public class ClassLoadTest02 {

    public static void main(String[] args) {
        /*
         * 编译期常量在编译阶段会存入到调用这个常量的方法所在的类的常量池中去
         * 本质上，在程序运行阶段，调用的类并没有直接引用定义常量的类，
         * 因此不会去触发定义常量的类的初始化
         * 【注意】本例中，在编译阶段，在MyParent类中定义的常量STR会被
         *  存储到ClassLoadTest02类中的常量池中去，之后ClassLoadTest02与
         *  Parent02之间就没有关系了，甚至可以将MyParent.class文件删除
         */
//        System.out.println(Parent02.str);
//        System.out.println(Parent02.STR);

        // 【助记符】
        //iconst_m1:表示将常量值-1从常量池推送至栈顶                                    =====>-1
        //iconst_n:表示将范围在[0,5]内的常量值从常量池推送至栈顶                          =====>0,1,2,3,4,5
        //bipush:表示将范围在[-128,-2]U[6,127]内的常量值从常量池推至栈顶                  =====>byte
        //sipush:表示将范围在[-32768,-129]U[128,32767]内的常量值从常量池推至栈顶          ====>char,short
        //ldc:表示将范围在[-2147483648,-32769]U[32768,2147483647],float或String类型   =====>int,float,string
        // 的常量值从常量池中推至栈顶
        //ldc2_w：表示将long或者double类型常量值推送至栈顶                              =====>long,double
        System.out.println(Parent02.NUM);
//        System.out.println(Parent02.L_NUM);
    }
}

class Parent02 {
    public static String str = "hello MyParent02";

    public static final String STR = "hello MyParent02";

    public static final  int NUM = 32767;

    public static final  long L_NUM = Long.MAX_VALUE;

    public static final  double D_NUM = 0.878;

    static {
        System.out.println("MyParent is initialized!");
    }
}
