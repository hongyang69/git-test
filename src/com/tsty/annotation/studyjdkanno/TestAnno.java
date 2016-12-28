package com.tsty.annotation.studyjdkanno;

/**
 * 注解的分类
 * 按照运行机制分：
 * 	.源码注解--注解只在源码中存在，编译成.class文件就不存在了
 *  .编译时注解--注解在源码和.class文件中都存在(@Override,@@Deprecated,@@SuppressWarnings)
 *  .运行时注解--在运行阶段还起作用，甚至会影响运行逻辑的注解(spring中@Autowired)
 * 
 * 按照来源分:
 * 	.来自JDK的注解
 *  .来自第三方的注解
 *  .我们自己定义的注解
 * 
 * 
 *
 */

public class TestAnno {
	
	@SuppressWarnings("deprecation")
	public void sing(){
		Person person = new Child();
		person.sing();
	}
	
}
