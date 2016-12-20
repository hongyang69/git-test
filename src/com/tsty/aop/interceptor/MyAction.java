package com.tsty.aop.interceptor;

public class MyAction implements Action {  
	  
    @Override  
    public String execute() {  
        System.out.println("execute...");  
        return "success:" + getClass();  
    }  
  
}  
