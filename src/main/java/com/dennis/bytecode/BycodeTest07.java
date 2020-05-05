package com.dennis.bytecode;

public class BycodeTest07 {

    public  void increase() {
        int a = 0;
        for (int i = 0; i < 99; i++) {
            a = ++a;
        }
        System.out.println(a); // 99
    }
}
