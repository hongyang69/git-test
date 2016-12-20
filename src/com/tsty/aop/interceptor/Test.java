package com.tsty.aop.interceptor;

/**
 * 
 * 在Struts2中有拦截器的概念，通过它的拦截器可以拦截Action。Struts2的拦截器是通过AOP来实现的，在Spring也有类似的概念。下面的我们先来比较一下Struts2和Spring中AOP的东西。
 
AOP概念	Struts2	Spring
JoinPoint	Action中方法的执行	符合条件方法的执行
Pointcut	Action（不能自己指定）	可以通过正则或AspectJ表达式来指定
Advice	Before、After、Around	Before、After、Around
Aspect	拦截器	拦截器
 
     从上面的比较中可以看到，Struts2的AOP功能比较单一，只能拦截Action类中的方法。Spring的AOP是通过JDK动态代理或者CGLib来生成目标对象的代理对象，然后将增强功能（Aspect【包括了Advice和Pointcut】）织入到符合条件（Pointcut）的类的方法（JoinPoint）上。
     Struts2的AOP实现跟Filter的实现差不多，它有一系列的拦截器，称为拦截器栈，通过这些拦截器栈通过ActionInvocation的调度可以在Action中方法执行之前或执行做一些操作。原理图请看：action-interceptor.jpg
 *
 */

public class Test {  
	  
	/** 
	 * 可以看到添加进去的Interceptor像是一个栈一样，如果放进去的的Interceptor是BeforeAdvice类型的，则会在Action中的方法执行之前，执行增强操作；如果放进去的Interceptor是AroundAdvice类型的，则会在Action中的方法之前按照“进栈”的顺序执行增强操作，在Action方法执行之后，按照“出栈”的顺序执行增强操作
      Struts2就是这样实现AOP的，比起Spring的AOP实现，简单多了。^_^
      */
    public static void main(String[] args) {  
        Interceptor exptionInterceptor = new ExceptionInterceptor();  
        Interceptor i18nInterceptor = new I18NInterceptor();  
        Interceptor aroundInterceptor = new AroundInterceptor();  
        Interceptor aroundInterceptor01 = new AroundInterceptor01();  
          
        DefaultActionInvoation actionInvocation = new DefaultActionInvoation();  
        actionInvocation.addInterceptor(exptionInterceptor);  
        actionInvocation.addInterceptor(i18nInterceptor);  
        actionInvocation.addInterceptor(aroundInterceptor);  
        actionInvocation.addInterceptor(aroundInterceptor01);  
          
        Action action = new MyAction();  
        actionInvocation.setAction(action);  
          
        String result = actionInvocation.invoke();  
        System.out.println("Action result:" + result);  
    }  
      
}  