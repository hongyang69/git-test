package com.tsty.thread.interrupt;

/**
 * 注释及代码出自：http://www.php.cn/java-article-339998.html
 * 从执行结果也可以看到，前两次调用isInterrupted方法都返回true，说明isInterrupted方法不会改变线程的中断状态，
 * 而接下来调用静态的interrupted()方法，第一次返回了true，表示线程被中断，第二次则返回了false，因为第一次调用的时候已经清除了中断状态。
 * 最后两次调用isInterrupted()方法就肯定返回false了。
 * 
 * 那么，在什么场景下，我们需要在catch块里面中断线程（重置中断状态）呢？
 * 
 * 那么问题来了：为什么要在抛出InterruptedException的时候清除掉中断状态呢？
 * 这个问题没有找到官方的解释，估计只有Java设计者们才能回答了。但这里的解释似乎比较合理：一个中断应该只被处理一次（
 * 你catch了这个InterruptedException，说明你能处理这个异常，你不希望上层调用者看到这个中断）。
 * 
 * 
 * 
 * 
 */

public class Interrupt1 {
	public static void main(String[] args) throws Exception {
		Thread t = new Thread(new Worker());
		t.start();

		Thread.sleep(200);
		t.interrupt();

		System.out.println("Main thread stopped.");
	}

	public static class Worker implements Runnable {
		public void run() {
			System.out.println("Worker started.");
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				Thread curr = Thread.currentThread(); // 再次调用interrupt方法中断自己，将中断状态设置为“中断”
				curr.interrupt();
				System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
				System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
				System.out.println("Static Call: " + Thread.interrupted());// 返回true，并且clear掉中断状态
				try {
					Thread.sleep(2000); // 不会跳入catch块中，因为Thread.interrupted()清除掉中断状态了
				} catch (InterruptedException e1) {
					System.err.println("中断退出");
					return;
				}
				// status
				System.out.println("---------After Interrupt Status Cleared----------");
				System.out.println("Static Call: " + Thread.interrupted());// 返回false,因为状态已经被清除掉
				System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
				System.out.println("Worker IsInterrupted: " + curr.isInterrupted());
			}

			System.out.println("Worker stopped.");
		}
	}
}
