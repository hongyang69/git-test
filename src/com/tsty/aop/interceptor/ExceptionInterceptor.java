package com.tsty.aop.interceptor;

public class ExceptionInterceptor implements Interceptor {  
	  
    @Override  
    public String intercept(ActionInvocation invocation) {  
        System.out.println("ExceptionInterceptor");  
        return invocation.invoke() + "&ExceptionInterceptor";  
    }  
  
}  
