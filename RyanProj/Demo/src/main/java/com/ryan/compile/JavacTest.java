package com.ryan.compile;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * Created by Ryan on 2015/1/14.
 */
public class JavacTest {

    public static final String Compile_source_path = "\\Demo\\src\\main\\java\\com\\ryan\\compile\\";
    public static final String Compile_class_path = "\\Demo\\target\\com\\ryan\\compile\\";

    public JavacTest() {

    }

    public static void eval(String str) {
        System.out.println(System.getProperty("user.dir"));//当前工作目录
        String s = "package com.ryan.compile;";
        s += "\r\n" + "public class Temp{";
        s += "\r\n" + "      public static String call(String ss){      ";
        s += "\r\n" + "            System.out.println(\"" + str + "\");  ";
        s += "\r\n" + "            return \"return_str\"; ";
        s += "\r\n" + "      }";
        s += "\r\n" + "}";
        try {
            File file = new File(System.getProperty("user.dir") + Compile_source_path + "Temp.java");
            PrintWriter pw = new PrintWriter(new FileWriter(file));
            pw.println(s);
            pw.close();

            //动态编译
            JavaCompiler javac = ToolProvider.getSystemJavaCompiler();
            int status = javac.run(null, null, null, "-d", System.getProperty("user.dir") + Compile_source_path, "Temp.java");
            if (status != 0) {
                System.out.println("没有编译成功！");
            }

            //动态执行
            Class cls = Class.forName("Temp");//返回与带有给定字符串名的类 或接口相关联的 Class 对象。
            Method method = cls.getDeclaredMethod("call", String.class);//返回一个 Method 对象，该对象反映此 Class 对象所表示的类或接口的指定已声明方法
            String result = (String) method.invoke(null, str);//静态方法第一个参数可为null,第二个参数为实际传参
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        JavacTest javacTest = new JavacTest();
        javacTest.eval("input_str");

    }

}