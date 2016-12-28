package com.tsty.annotation.define;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.sun.javafx.collections.MappingChange.Map;

/**
 * 自定义注解的语法要求
 * 	.成员类型是受限的，合法的类型包括原始类型及String,Class,Annotation,Enumeration
 *  .如果注解只有一个成员，则成员名必须取名为value(),在使用时可以忽略成员名和赋值号(=)
 *  .注解类可以没有成员,没有成员的注解称为标识注解
 *
 */

@Target({ElementType.METHOD,ElementType.TYPE})   //表示作用域可以用在方法或者类上面
@Retention(RetentionPolicy.RUNTIME)     //表示运行时注解
@Inherited				//允许子类继承
@Documented				//生成javadoc时会包含注解
public @interface Description { //使用@interface关键字定义注解
	
	String value();	//成员以无参无异常方式声明
	
	

}
