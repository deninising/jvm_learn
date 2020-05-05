package com.dennis.bytecode;

/**
 * 描述： 动态分派（方法的重写，程序运期才能确定）
 */
public class BycodeTest05 {
    public static void main(String[] args) {
        Fruit apple = new Apple();

        System.out.println(apple.getName());
    }
}

class Fruit {
    public String getName() {
        return "please choose a specific fruit";
    }
}

class Apple extends Fruit {
    private String name = "apple";
    @Override
    public String getName() {
        return this.name;
    }
}

class Orange extends Fruit {
    private String name = "orange";
    @Override
    public String getName() {
        return this.name;
    }
}

