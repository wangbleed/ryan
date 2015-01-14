package com.ryan.compile;

import javax.tools.JavaCompiler;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Ryan on 2015/1/14.
 */
public class JavaCompileTest {

    private static String PACKAGE_NAME = "com.ryan.compile";
    private static String JAVA_CLASS_NAME = "DynamicObject";
    private static String JAVA_SOURCE_FILE = JAVA_CLASS_NAME + ".java";
    private static String JAVA_CLASS_FILE = JAVA_CLASS_NAME + ".class";

    public static void main(String[] args) throws Exception{
        //创建java文件
        String tr = "\r\n";
        StringBuilder sb = new StringBuilder();
        sb.append("package "+ PACKAGE_NAME +";").append(tr);
        sb.append("public class ").append(JAVA_CLASS_NAME).append("{").append(tr);
        sb.append("     public static void main(String[] args) {").append(tr);
        sb.append("         System.out.println(\"this is DynamicJavaCompiler\");").append(tr);
        sb.append("     }").append(tr);
        sb.append("}").append(tr);

        String filename = System.getProperty("user.dir") + "\\Demo\\src\\main\\java\\com\\ryan\\compile\\" + JAVA_SOURCE_FILE;
        //字符输出流
        FileWriter fw = new FileWriter(filename);
        //将字节输出流转为PrintWriter
        PrintWriter pw = new PrintWriter(fw);
        pw.write(sb.toString());
        pw.close();

        //编译java文件
        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager javaFileManager = javaCompiler.getStandardFileManager(null, null, null);
        Iterable sourceFiles = javaFileManager.getJavaFileObjects(filename);
        //指定编译文件存放位置,如果不指定的话，编译的文件会和java源文件在一个文件夹中
        //这样的话加载类的时候会报java.lang.ClassNotFoundException
        Iterable<String> options = Arrays.asList("-d", System.getProperty("user.dir") + "WebRoot\\WEB-INF\\classes");
        javaCompiler.getTask(null, javaFileManager, null, options, null, sourceFiles);
        javaFileManager.close();


        URL[] urls = new URL[]{new URL("file://" + System.getProperty("user.dir") + "\\Demo\\src\\main\\java\\")};
        URLClassLoader loader = new URLClassLoader(urls);
        Class cls = loader.loadClass(PACKAGE_NAME+ "." + JAVA_CLASS_NAME);
        cls.getMethod("main", String[].class).invoke(null, new String[]{null});
    }
}
