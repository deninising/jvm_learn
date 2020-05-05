package com.dennis.memory;

import java.util.concurrent.TimeUnit;

/**
 * 描述：JVM常用工具命令
 *
 * jcmd (从JDK 1. 7开始增加的命令)
 * 1. jcmd pid VM. flags:查看JVM的启动参数
 * 2. jcmd pid help: 列出当前运行的Java进程可以执行的操作
 * 3. jcmd pid help JFR.dump: 查看具体命令的选项
 * 4. jcmd pid PerfCounter.print:查看JVM性能相关的参数
 * 5. jcmd pid VM.uptime:查看JVM的启动时长
 * 6. jcmd pid GC.class_ histogram:查看系统中类的统计信息
 * 7. jcmd pid Thread. print:查看线程堆栈信息
 * 8. jcmd pid GC.heap_dump filename: 导出Heap_ dump文件，导出的文件可以通过jvisualvm查看
 * 9. jcmd pid VM. system_properties:查看JVM的属性信息
 * 10. jcmd pid VM.version:查看目标JVM进程的版本信息
 * 11. jcmd pid VM. command_ line:查看JVM启动的命令行参数信息
 *
 * jstack:可以查看或是导出Java应用程序中线程的堆栈信息
 * jmc: Java Mission Control
 * jfr: Java Flight Recorder
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/5/2 22:12
 */
public class JmmTest04 {
    public static void main(String[] args) throws InterruptedException {
        for (; ; ) {
            System.out.println("hello JVM tools");
            TimeUnit.MILLISECONDS.sleep(200);
        }
    }
}
