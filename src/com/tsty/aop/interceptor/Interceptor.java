package com.tsty.aop.interceptor;

/** 
 * 拦截器 
 * @author zyb 
 * @since 2013-6-2 下午1:23:35 
 */  
public interface Interceptor {  
  
    String intercept(ActionInvocation invocation);   
      
}  
