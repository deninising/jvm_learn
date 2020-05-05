package com.dennis.memory;

import java.util.concurrent.TimeUnit;
/**
 * 描述：虚拟机栈溢出
 * -Xss200k
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/5/2 15:06
 */
public class JmmTest02 {
    private int depth;

    public int getDepth() {
        return this.depth;
    }

    public void recurseDo() {
        System.out.println("递归深度：" + this.depth);
        this.depth++;
        try {
            TimeUnit.MILLISECONDS.sleep(20);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        recurseDo();
    }

    public static void main(String[] args) {
        JmmTest02 jmmTest02 = new JmmTest02();
        jmmTest02.recurseDo();//Exception in thread "main" java.lang.StackOverflowError
    }
}
