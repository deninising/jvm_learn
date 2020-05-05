package com.dennis.classloader;

public class Pig {
    private Pig pig;

    public void setPig(Object object) {
        this.pig = (Pig) object;
        System.out.println("method in Class pig is invoked successfully");
    }
}
