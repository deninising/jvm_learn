package com.dennis.classloading;

/**
 * 描述：数组创建的本质
 * 创建数组不会主动调用component元素类,jvm会自动创建一个将自定的元素作为数组的component的新的类型
 * 【对于数组来说，其类型是由JVM动态生成的】
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/22 17:24
 */
public class ClassLoadTest04 {

    public static void main(String[] args) {
        // 助记符：anewarray ===>component为引用类型
        // class [Lcom.dennis.classloading.Parent04;
        Parent04[] pArray = new Parent04[1];
        System.out.println(pArray.getClass());

        // 助记符：multianewarray
        // class [[Lcom.dennis.classloading.Parent04;
        Parent04[][] ppArray = new Parent04[1][1];
        System.out.println(ppArray.getClass());

        // 助记符：newarray ===>component为基本类型
        // class [I
        int[] intArray = new int[2];
        System.out.println(intArray.getClass());
    }
}

class Parent04 {
    public static String str = "hello parent";
    static {
        System.out.println("class Parent04 has been initialized!");
    }
}
