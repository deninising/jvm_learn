package com.dennis.classloader;

import com.mysql.jdbc.Driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 描述：使用mysql驱动类时深度剖析
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/28 11:48
 */
public class ClassLoaderTest11 {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        // 加载子类的时候会自动加载父类
        ClassLoaderTest05 loader = new ClassLoaderTest05("loader");
        loader.loadClass("com.dennis.classloader.Child");

        // 查看当前线程的上下文类加载器
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("当前线程上下文类加载器：" + contextClassLoader);


        // 加载实现类时会去首先加载其接口（若之前没有被加载）,所以加载java.sql.Driver类的线程为当前线程,其线程上下文加载器为AppClassLoader
        Class.forName("com.mysql.jdbc.Driver");
        ClassLoader classLoader = Driver.class.getClassLoader();
        System.out.println("实现类的类加载器：" + classLoader);// 实现类的类加载也为AppClassLoader,因此JDBC与厂商实现类可兼容

        // 类DriverManager的初始化过程中又会采用标准SPI类加载的方式加载一次JDBC中驱动的具体实现类,
        // 因此Class.forName("com.mysql.jdbc.Driver")中重点在于类的初始化时驱动的注册操作 java.sql.DriverManager.registerDriver(new Driver());
        Connection connection = DriverManager.getConnection("jdbc:mysql://192.168.1.57:3306/test", "root", "1234");
    }
}

class Child extends Parent {
}

class Parent {
}
