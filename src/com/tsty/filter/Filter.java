package com.tsty.filter;

/** 
 * 过滤器接口 
 * @author zyb 
 * @since 2012-12-1 
 * 
 */  
public interface Filter {  
  
    /** 
     * 处理请求和响应 
     * @param request       请求 
     * @param response      响应 
     * @param chain         过滤器链 
     */  
    void doFilter(Request request, Response response, FilterChain chain);  
      
}  
