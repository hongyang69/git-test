package com.tsty.jvm.runtime.hook;

import java.util.concurrent.TimeUnit;

/**
 * 
 * Java程序经常也会遇到进程挂掉的情况，一些状态没有正确的保存下来，这时候就需要在JVM关掉的时候执行一些清理现场的代码。
 * JAVA中的ShutdownHook提供了比较好的方案。
 * 
 * JDK提供了Java.Runtime.addShutdownHook(Thread
 * hook)方法，可以注册一个JVM关闭的钩子，这个钩子可以在一下几种场景中被调用：
 * 
 * 程序正常退出 使用System.exit() 终端使用Ctrl+C触发的中断 系统关闭 OutOfMemory宕机 使用Kill
 * pid命令干掉进程（注：在使用kill -9 pid时，是不会被调用的） 下面是JDK1.7中关于钩子的定义：
 * 
 * public void addShutdownHook(Thread hook) 参数： hook - An initialized but
 * unstarted Thread object 抛出： IllegalArgumentException - If the specified hook
 * has already been registered, or if it can be determined that the hook is
 * already running or has already been run IllegalStateException - If the
 * virtual machine is already in the process of shutting down SecurityException
 * - If a security manager is present and it denies
 * RuntimePermission("shutdownHooks") 从以下版本开始： 1.3 另请参见：
 * removeShutdownHook(java.lang.Thread), halt(int), exit(int)
 *
 */

public class HookTest {
	
//	当main线程运行结束之后就会调用关闭钩子。
	public void start() {
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("Execute Hook.....");
			}
		}));
	}

	public static void main(String[] args) {
		new HookTest().start();//	当main线程运行结束之后就会调用关闭钩子。
		System.out.println("The Application is doing something");

		try {
			TimeUnit.MILLISECONDS.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
