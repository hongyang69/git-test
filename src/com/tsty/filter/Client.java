package com.tsty.filter;

/** 
 * 客户端程序 
 * @author zyb 
 * @since 2012-12-1 
 * 
 */  
public class Client {  
  
    public static void main(String[] args) {  
        Request request = new Request();  
        request.setValue("abcd");  
        Response response = new Response();  
        FilterChain filter = new FilterChain();  
          
        // 将请求值变成大写字母  
        filter.addFilter(new Filter() {  
              
            @Override  
            public void doFilter(Request request, Response response, FilterChain chain) {  
                System.out.println("第一个Filter处理...请求值：" + request.getValue());  
                StringBuilder sb = new StringBuilder();  
                for (int i = 0; i < request.getValue().length(); i++) {  
                    sb.append((char) (request.getValue().charAt(i) - 32));  
                }  
                Response result = new Response();  
                result.setValue(sb.toString());  
                chain.doFilter(request, response, chain);  
                System.out.println("第一个Filter响应...响应值：" + result.getValue());  
                  
            }  
        });  
          
        // 将请求值加1  
        filter.addFilter(new Filter() {  
              
            @Override  
            public void doFilter(Request request, Response response, FilterChain chain) {  
                System.out.println("第二个Filter处理...请求值：" + request.getValue());  
                StringBuilder sb = new StringBuilder();  
                for (int i = 0; i < request.getValue().length(); i++) {  
                    sb.append((char) (request.getValue().charAt(i) + 1));  
                }  
                Response result = new Response();  
                result.setValue(sb.toString());  
                chain.doFilter(request, response, chain);  
                System.out.println("第二个Filter响应...响应值：" + result.getValue());  
            }  
        });  
          
        filter.doFilter(request, response, filter);  
    }  
}  