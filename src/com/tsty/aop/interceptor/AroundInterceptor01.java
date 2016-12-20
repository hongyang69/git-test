package com.tsty.aop.interceptor;

/** 
 * 环绕拦截器 
 * @author zyb 
 * @since 2013-6-2 下午1:21:24 
 */  
public class AroundInterceptor01 implements Interceptor {  
  
    @Override  
    public String intercept(ActionInvocation invocation) {  
        System.out.println("before:" + this.getClass());  
        String result = invocation.invoke();  
        System.out.println("after:" + this.getClass());  
        return result + "&AroundInterceptor01";  
    }  
  
}  
