package com.dennis.memory;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.util.concurrent.TimeUnit;

/**
 * 描述： 采用充满元空间的方法模拟内存溢出，借助ciglib不停地动态生成Class obj，使得元空间内存的使用耗尽，最终导致内存溢出
 * -XX:MaxMetaspaceSize=30m
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/5/2 17:01
 */
public class JmmTest03 {

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        for (; ; ) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(JmmTest03.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (obj, method, args1, proxy) ->
                    proxy.invokeSuper(obj, args1)
            );

            TimeUnit.MILLISECONDS.sleep(10);
            System.out.println("动态class obj:" + (++i));
            enhancer.create();//Caused by: java.lang.OutOfMemoryError: Metaspace
        }
    }
}
