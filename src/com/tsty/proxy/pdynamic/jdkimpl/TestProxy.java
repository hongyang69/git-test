package com.tsty.proxy.pdynamic.jdkimpl;

import com.tsty.proxy.pstatic.Count;
import com.tsty.proxy.pstatic.CountImpl;

public class TestProxy {  
	  
    public static void main(String[] args) {  
        BookFacadeProxy proxy = new BookFacadeProxy();
        //委托类1
        BookFacade bookProxy = (BookFacade) proxy.bind(new BookFacadeImpl());  
        bookProxy.addBook();
        
        //委托类2
        Count countProxy = (Count) proxy.bind(new CountImpl());  
        countProxy.queryCount();
    }  
  
}  
