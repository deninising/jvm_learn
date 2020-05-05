package com.dennis.bytecode;

/**
 * 描述：
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/29 21:22
 */
public class BycodeTest02 {
    String str = "Welcome";
    private int x = 5;
    public static Integer in = 10;
    private final Object LOCK = new Object();

    public static void main(String[] args) {
        BycodeTest02 myTest02 = new BycodeTest02();
        myTest02.setX(10);
    }

    public synchronized void setX(int x) {
        this.x = x;
    }

    public void test(String str) {
        synchronized (LOCK) {
            System.out.println("hello world");
        }
    }

    public synchronized static void test2() {

    }
}
