package com.tsty.proxy.pdynamic.jdkimpl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class TestInterfaces {
	public static void main(String[] args) {
		InterImpl interImpl = new InterImpl(); 
		System.err.println(interImpl.getClass().getInterfaces());
		System.err.println(interImpl.getClass().getInterfaces().length);
		for (Class kClass : interImpl.getClass().getInterfaces()) {
			System.err.println(kClass);
		}
		System.err.println(interImpl.getClass());
		
		
		Class<?>[] interfaces = interImpl.getClass().getInterfaces();	//interImpl继承的接口
		ClassLoader loader = interImpl.getClass().getClassLoader();	//interImpl的类加载器
		
		String[] interfaceNames = new String[interfaces.length];  
		  
	    Set interfaceSet = new HashSet();   // for detecting duplicates  --set去除重复的接口
	    
	  
	    // 遍历目标类所实现的接口  
	    for (int i = 0; i < interfaces.length; i++) {  
	          
	        // 拿到目标类实现的接口的名称  
	        String interfaceName = interfaces[i].getName();  
	        Class interfaceClass = null;  
	        try {  
	        // 加载目标类实现的接口到内存中  
	        interfaceClass = Class.forName(interfaceName, false, loader);  
	        System.err.println(i + ":" + loader );
	        System.err.println(i + ":" + interfaceName );
	        System.err.println(i + ":" + interfaceClass );
	        } catch (ClassNotFoundException e) {  
	        }  
	        if (interfaceClass != interfaces[i]) {  
	        throw new IllegalArgumentException(  
	            interfaces[i] + " is not visible from class loader");  
	        }  
	  
	        // 中间省略了一些无关紧要的代码 .......  
	          
	        // 把目标类实现的接口代表的Class对象放到Set中  
	        interfaceSet.add(interfaceClass);  
	  
	        interfaceNames[i] = interfaceName;  
	    }  
	    
	    System.err.println(interfaceSet);
	    System.err.println(Arrays.asList(interfaceNames));
	    
	    SecurityManager sm = System.getSecurityManager();
	    System.err.println(sm);

	}
}

class InterImpl implements Inter1,Inter2,Inter3{

	@Override
	public void printInter3() {
		System.err.println("printInter3()");
		
	}

	@Override
	public void printInter2() {
		System.err.println("printInter2()");
		
	}

	@Override
	public void printInter1() {
		System.err.println("printInter1()");
		
	}
	
}

interface Inter1{
	void printInter1();
}

interface Inter2{
	void printInter2();
}

interface Inter3{
	void printInter3();
}