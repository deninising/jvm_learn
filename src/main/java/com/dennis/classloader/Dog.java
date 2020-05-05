package com.dennis.classloader;

// Dog类中访问Cat类的class对象
public class Dog {
    public Dog() {
        System.out.println("Dog is loaded by:" + this.getClass().getClassLoader());
        System.out.println("from class Dog:" + Cat.class + "==>classLoader:" + Cat.class.getClassLoader());
    }
}
