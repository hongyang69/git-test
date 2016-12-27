package com.tsty.annotation.define;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * 解析注解：
 * 	概念：通过反射获取类、函数或成员上的运行时注解信息，从而实现动态控制程序运行的逻辑。 
 * 
 * 在接口上面使用注解是没有用的；
 * 并且注解的继承只能针对在父类上面的注解，针对父类方法的注解，则不能继承
 *
 */

public class ParseAnno {

	public static void main(String[] args) {
		//1. 使用类加载器加载类
		try {
			Class kClass = Class.forName("com.tsty.annotation.studyjdkanno.Child");
			//2. 找到类上面的注解
			boolean isExisted = kClass.isAnnotationPresent(Description.class);
			if (isExisted) {
				//3. 拿到注解实例
				Description description = (Description) kClass.getAnnotation(Description.class);
				System.err.println(description.value());
				
				//4.找到方法上的注解
				Method[] methods = kClass.getMethods();
					//4.1 第一种注解方法
				for (Method method : methods) {
					boolean isMExisted= method.isAnnotationPresent(Description.class);
					if (isMExisted) {
						Description mDescription = (Description) method.getAnnotation(Description.class);
						System.err.println(mDescription.value());
					}
				}
				
					//4.2 另外一种注解方法
				for (Method method : methods) {
					Annotation[] annotations = method.getAnnotations();
					for (Annotation annotation : annotations) {
						if (annotation instanceof Description) {
							Description mDescription = (Description) annotation;
							System.err.println(mDescription.value());
						}
					}
				}
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
