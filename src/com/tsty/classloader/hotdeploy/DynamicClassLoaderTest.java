package com.tsty.classloader.hotdeploy;

import java.lang.reflect.Method;  

public class DynamicClassLoaderTest {  
  
    public static void main(String[] args) throws Exception {  
        while (true) {  
        	DynamicClassLoader loader = new DynamicClassLoader(); 
//            System.err.println(loader);	
//            System.err.println(loader.getParent());
//            Class<?> clazz = loader.loadClass("F:\\JavaProjects\\MyTomcat\\bin", "test.classloader.Hello");
            Class<?> clazz = loader.loadClass("/Users/ningguangyuan/git/git-test/bin/", "com.tsty.classloader.hotdeploy.Hello");
            Method method = clazz.getMethod("sayHello", String.class);  
            System.out.println(method.invoke(clazz.newInstance(), " Reflect"));
            Hello hello = new Hello();
            System.err.println(hello.sayHello(" New"));
            // 每隔3秒钟重新加载  
            Thread.sleep(3000);  
        }  
    }  
}  


