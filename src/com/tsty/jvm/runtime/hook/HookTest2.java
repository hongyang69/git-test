package com.tsty.jvm.runtime.hook;

import java.util.concurrent.TimeUnit;

public class HookTest2  
{  
    public void start()  
    {  
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {  
            @Override 
            public void run()  
            {  
                System.out.println("Execute Hook.....");  
            }  
        }));  
    }  


    public static void main(String[] args)  
    {  
        new HookTest().start();  
        System.out.println("The Application is doing something");  
        byte[] b = new byte[500*1024*1024];  //运行参数设置为：-Xmx20M  这样可以保证会有OutOfMemoryError的发生。
        try 
        {  
            TimeUnit.MILLISECONDS.sleep(5000);  //    可以看到程序遇到内存溢出错误后调用关闭钩子，与第一种情况中，程序等待5000ms运行结束之后推出调用关闭钩子不同。
        }  
        catch (InterruptedException e)  
        {  
            e.printStackTrace();  
        }  
    }  
 
}
