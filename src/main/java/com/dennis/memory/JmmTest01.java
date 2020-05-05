package com.dennis.memory;

import java.util.ArrayList;

/**
 * 描述：OutOfMemoryError
 * -Xms20m -Xmx20m  -XX:+HeapDumpOnOutOfMemoryError
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/5/2 14:30
 */
public class JmmTest01 {

    public static void main(String[] args) throws InterruptedException {
        ArrayList<JmmTest01> list = new ArrayList<>();
        while (true) {
            list.add(new JmmTest01());
            System.out.println(list.size());
        }
    }
}
