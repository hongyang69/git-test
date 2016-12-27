package com.tsty.annotation.studyjdkanno;

import com.tsty.annotation.define.Description;

@Description("I am interface")
public interface Person {
	@Description("I am interface method")
	public String name();
	public int age();
	@Deprecated
	public void sing();
	
}
