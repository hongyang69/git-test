package com.tsty.jvm.runtime;

/**
 * Java.lang.Runtime 类：
 * 	--每个Java应用程序都有一个类实例，使用应用程序能够与其运行的环境相连接。可以
 *  --通过getRuntime方法获取当前运行时。
 *  
 *  应用程序不能创建自己的Runtime类实例。
 *
 */

public class TestRuntime {
	public static void main(String[] args) {
		testRuntime();
	}
	
	private static void testRuntime() {
//		Runtime.maxMemory();   //最大JVM内存（就是你配置给JVM的值）
//		Runtime.totalMemory();//JVM可支配的最大内存值
//		Runtime.freeMemory();//剩余内存空间

		//例子如下：
		Runtime run = Runtime.getRuntime();

		long max = run.maxMemory();
		long total = run.totalMemory();
		long free = run.freeMemory();
		long usable = max - total + free;
		System.out.println("最大内存 = " + max);
		System.out.println("已分配内存 = " + total);
		System.out.println("已分配内存中的剩余空间 = " + free);
		System.out.println("最大可用内存 = " + usable);

	}
}
