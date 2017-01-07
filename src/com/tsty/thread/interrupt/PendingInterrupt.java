package com.tsty.thread.interrupt;

/**
 * 注释及代码出自：http://blog.csdn.net/ns_code/article/details/17091267
 * 
 * 待决中断
 * 在SleepInterrupt的例子中，sleep（）方法的实现检查到休眠线程被中断，它会相当友好地终止线程，并抛出InterruptedException异常。另外一种情况，
 * 如果线程在调用sleep（）方法前被中断，那么该中断称为待决中断，它会在刚调用sleep（）方法时，立即抛出InterruptedException异常。
 *
 */

public class PendingInterrupt extends Object {
	public static void main(String[] args) {
		// 如果输入了参数，则在mian线程中中断当前线程（亦即main线程）
		if (args.length > 0) {
			Thread.currentThread().interrupt();
		}
		// 获取当前时间
		long startTime = System.currentTimeMillis();
		try {
			Thread.sleep(2000);
			System.out.println("was NOT interrupted");
		} catch (InterruptedException x) {
			System.out.println("was interrupted");
		}
		// 计算中间代码执行的时间
		System.out.println("elapsedTime=" + (System.currentTimeMillis() - startTime));
	}
}
