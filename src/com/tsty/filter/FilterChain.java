package com.tsty.filter;

import java.util.ArrayList;  
import java.util.List;  
  
/** 
 * 过滤器链 
 * @author zyb 
 * @since 2012-12-1 
 * 
 */  
public class FilterChain implements Filter { // 在JavaEE的FilterChain源码中并没有实现Filter接口  
      
    // 用于保存所有要处理的过滤器  
    private List<Filter> filters = new ArrayList<Filter>();  
      
    // 索引，用于记数  
    private int index = 0;  
      
    @Override  
    public void doFilter(Request request, Response response, FilterChain chain) {  
        // 如果所有的过滤器都已经处理完  
        if (index == filters.size()) {  
            return;  
        }  
        // 取出相应的过滤器  
        Filter filter = filters.get(index);  
        // 索引加1  
        index++;  
        // 调用具体的过滤器处理请求和响应  
        filter.doFilter(request, response, chain);  
    }  
  
    // 往过滤器链中添加过滤器  
    public void addFilter(Filter filter) {  
        this.filters.add(filter);  
    }  
}  