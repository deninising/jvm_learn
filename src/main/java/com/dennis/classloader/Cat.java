package com.dennis.classloader;

// Cat类中访问Dog类的class对象
public class Cat {
    public Cat() {
        System.out.println("Cat is loaded by:" + this.getClass().getClassLoader());
        System.out.println("from class Cat:" + Dog.class + "==>classLoader:" + Dog.class.getClassLoader());
    }
}
