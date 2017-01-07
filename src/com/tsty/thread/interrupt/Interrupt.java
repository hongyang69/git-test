package com.tsty.thread.interrupt;

/**
 * 注释及代码出自：http://www.php.cn/java-article-339998.html
 * 
 * 
 * 说说interrupt, interrupted和isInterrupted的区别了：
 * 
 * interrupt方法是用于中断线程的，调用该方法的线程的状态将被置为"中断"状态。注意：线程中断仅仅是设置线程的中断状态位，不会停止线程。
 * 需要用户自己去监视线程的状态为并做处理。支持线程中断的方法（也就是线程中断后会抛出InterruptedException的方法，比如这里的sleep，
 * 以及Object.wait等方法）就是在监视线程的中断状态，一旦线程的中断状态被置为“中断状态”，就会抛出中断异常。这个观点可以通过这篇文章证实：
 * 
 * 看看interrupted方法的实现： public static boolean interrupted() { return
 * currentThread().isInterrupted(true); } 和isInterrupted的实现： public boolean
 * isInterrupted() { return isInterrupted(false); }
 * 这两个方法一个是static的，一个不是，但实际上都是在调用同一个方法，只是interrupted方法传入的参数为true，
 * 而inInterrupted传入的参数为false。那么这个参数到底是什么意思呢？来看下这个isInterrupted(boolean)方法的实现：
 * (Tests if some Thread has been interrupted. The interrupted state is reset or
 * not based on the value of ClearInterrupted that is passed. 原生方法注释) private
 * native boolean isInterrupted(boolean ClearInterrupted);
 *
 * 这是一个native方法，看不到源码没有关系，参数名字ClearInterrupted已经清楚的表达了该参数的作用----是否清除中断状态。
 * 方法的注释也清晰的表达了“中断状态将会根据传入的ClearInterrupted参数值确定是否重置”。所以，静态方法interrupted将会清除中断状态
 * （传入的参数ClearInterrupted为true），而实例方法isInterrupted则不会（
 * 传入的参数ClearInterrupted为false）。
 * 
 * 回到刚刚的问题：很明显，如果要isInterrupted这个方法返回true，通过在调用isInterrupted方法之前再次调用interrupt()
 * 方法来恢复这个中断的状态即可：查看例子：类Interrupt1
 * 
 * 
 * 
 * 
 * 
 */

public class Interrupt {
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
				/**
				 * InterruptedException - if any thread has interrupted the
				 * current thread. The interrupted status of the current thread
				 * is cleared when this exception is thrown.
				 * 
				 * 注意到后面这句“当抛出这个异常的时候，中断状态已被清除”。所以Thread.currentThread().
				 * isInterrupted()方法应该返回false。
				 */

				System.out.println("Worker IsInterrupted: " + Thread.currentThread().isInterrupted());
			}

			System.out.println("Worker stopped.");
		}
	}
}