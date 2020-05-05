package com.dennis.bytecode;

import java.io.FileInputStream;
import java.net.ServerSocket;


/**
 * 描述：字节码层面异常处理流程
 * 对于Java类中的每-个实例方法，(非static方法) ，其在编译后所生成的字节码当中，方法参数的数量总是会比源代码中方法
 * 参数的数量多一个，(this) ，它位于方法的第一个参数位置处; 这样，我们就可以在Java的实例方法中使用this来去访问当前
 * 对象的属性以及其他方法。
 * 这个操作是在编译期间完成的，即由javac编译器在编译的时候将对this的访问转化为对一个普通实例方法参数的访问;接下来在运行期间,
 * 由JVM在调用实例方法时，自动向实例方法传入该this参数。所以，在实例方法的局部变量表中，至少会有一个指向当前对象的局部变量。
 *
 * @author Dennis
 * @version 1.0
 * @date 2020/4/30 9:49
 */
public class BycodeTest03 {

    public String test()  {

            try {
            FileInputStream fileInputStream =
                    new FileInputStream("text.txt");
            ServerSocket server =
                    new ServerSocket(9999);
            return "success";

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            System.out.println("release sourceRef");
        }

        return "failure";
    }
}
