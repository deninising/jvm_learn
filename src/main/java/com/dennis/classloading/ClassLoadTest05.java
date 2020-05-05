package com.dennis.classloading;

import java.util.UUID;

/**
 * 描述：接口加载的规则同类的加载规则相同,
 * 但是接口的初始化规则与类大不相同：
 * 【1】一个类的初始化并不要求必须初始化它实现的接口
 * 【2】一个接口的初始化也不要求必须对它的父接口进行初始化
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/22 19:12
 */
public class ClassLoadTest05 {
    public static void main(String[] args) {
        // 子接口通过多态的方式调用父接口的运行期常量，即使父接口进行了初始化但其子接口也不执行初始化===>对谁进行了主动使用，就对谁进行初始化
        System.out.println(Child05.P_STR);
        // 子接口执行初始化,其父接口不执行初始化
        System.out.println(Child05.C_STR);
        // 对于包含编译期常量的类或接口而言，其他类对该常量的调用，只要求编译期常量所在的类或者接口执行加载，不要求执行初始化，且常量会存储在调用该常量的方法所在的类的常量池当中
        // 编译期常量与运行期常量的区别：运行期常量需要类或者接口的运行期环境，因此需要对应的类或者接口执行初始化，而编译期常量只需要对应的类或者接口执行类加载即可
        System.out.println(Child05.C_STRING) ;
        System.out.println(Child05.P_STRING) ;
        System.out.println(Parent05.P_STRING) ;
    }
}

interface Parent05 {
    // 接口中无法定义static{}代码块，通过一下方式模拟类的初始化代码块，初始化阶段一下输出必执行
    public static final Thread thread = new Thread() {
        {
            System.out.println("interface Parent05 has been initialized");
        }
    };
    public static String P_STRING = "hello parent interface";
    public static final String P_STR = UUID.randomUUID().toString();
}

interface Child05 extends Parent05 {
    // 接口中无法定义static{}代码块，通过一下方式模拟类的初始化代码块，初始化阶段一下输出必执行
    public static final Thread thread = new Thread() {
        {
            System.out.println("interface Child05 has been initialized");
        }
    };
    public static String C_STRING = "hello child interface";
    public static String C_STR = UUID.randomUUID().toString();
}


