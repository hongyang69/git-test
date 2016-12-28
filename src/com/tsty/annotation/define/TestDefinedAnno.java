package com.tsty.annotation.define;

/**
 * 如何使用自定义注解
 * @<注解名>(<成员名1>=<成员值1>,<成员名2>=<成员值2>,...) 
 *
 */

public class TestDefinedAnno {
	
	@Description1(desc="I am eyeColor",author="Tsty",age=19)
	public String eyeColor(){
		return "red";
	}
	
}
