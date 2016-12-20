package com.tsty.aop.interceptor;

import java.util.ArrayList;
import java.util.List;

import sun.management.counter.Variability;

/** 
 * Action、Interceptor调度器默认实现 
 * @author zyb 
 * @since 2013-6-2 下午1:21:14 
 */  
public class DefaultActionInvoation implements ActionInvocation {  
      
    private int index;  
      
    private Action action;  
      
    private List<Interceptor> interceptors = new ArrayList<Interceptor>();  
  
    public void addInterceptor(Interceptor interceptor) {  
        this.interceptors.add(interceptor);  
    }  
  
    public void setAction(Action action) {  
        this.action = action;  
    }  
  
    @Override  
    public String invoke() {  
        String result = "";  
        // 如果所有的拦截器已经执行完，则执行Action中的方法  
        if (index == interceptors.size()) {  
            result = action.execute();  
//            System.err.println("action" + result);
        } else {  
            Interceptor interceptor = interceptors.get(index);  
            index++;  
            int i = index; //测试
            result = interceptor.intercept(this);
//            System.err.println("interceptor" + i + ":" + result);
        }  
        return result;   
    }  
  
}  
 