package com.tsty.aop.interceptor;

public class I18NInterceptor implements Interceptor {  
	  
    @Override  
    public String intercept(ActionInvocation invocation) {  
        System.out.println("I18NInterceptor");  
        return invocation.invoke() + "&I18NInterceptor";  
    }  
  
}  
