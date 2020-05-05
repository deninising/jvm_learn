package com.dennis.classloader;

import java.sql.Driver;
import java.util.Iterator;
import java.util.ServiceLoader;

/**
 * 描述： 结合java.util.ServiceLoader 与 mysql.Driver 的实际案,例分析contextClassLoader在实际场景中的应用和重要性
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/28 9:37
 */
public class ClassLoaderTest10 {

    public static void main(String[] args) {
        // 【1】Driver.class只是JDBC标准中的一个接口,那么ServiceLoader是怎么寻找并且加载到com.mysql.jdbc.Driver.class的？
        // 【2】加载ServiceLoader类的加载器是启动类加载器,因此它所依赖的类Driver.class也将由启动类加载器,而具体实现
        // 类com.mysql.jdbc.Driver是由AppClassLoader所加载的,那么标准中的Driver与具体实现的Driver是如何达到兼容的？
        ServiceLoader<Driver> loader = ServiceLoader.load(Driver.class);
        Iterator<Driver> iterator = loader.iterator();

        while (iterator.hasNext()) {
            Driver driver = iterator.next();
            System.out.println("driver:" + driver.getClass() + ",classLoader:" + driver.getClass().getClassLoader());
        }

        System.out.println("当前线程上下文类加载器：" + Thread.currentThread().getContextClassLoader());
        System.out.println("ServiceLoader的类加载器：" + ServiceLoader.class.getClassLoader());
    }
}
