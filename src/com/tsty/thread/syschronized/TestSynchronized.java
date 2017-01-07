package com.tsty.thread.syschronized;

/**
 * 注释及代码出自：http://zhh9106.iteye.com/blog/2151791
 *
 * 在Java编程中，经常需要用到同步，而用得最多的也许是synchronized关键字了，下面看看这个关键字的用法。
 * 因为synchronized关键字涉及到锁的概念，所以先来了解一些相关的锁知识。
 * 
 * java的内置锁：每个java对象都可以用做一个实现同步的锁，这些锁成为内置锁。线程进入同步代码块或方法的时候会自动获得该锁，
 * 在退出同步代码块或方法时会释放该锁。获得内置锁的唯一途径就是进入这个锁的保护的同步代码块或方法。
 * 
 * java内置锁是一个互斥锁，这就是意味着最多只有一个线程能够获得该锁，当线程A尝试去获得线程B持有的内置锁时，线程A必须等待或者阻塞，知道线程B释放这个锁
 * ，如果B线程不释放这个锁，那么A线程将永远等待下去。
 * 
 * java的对象锁和类锁：java的对象锁和类锁在锁的概念上基本上和内置锁是一致的，但是，两个锁实际是有很大的区别的，对象锁是用于对象实例方法，
 * 或者一个对象实例上的，类锁是用于类的静态方法或者一个类的class对象上的。我们知道，类的对象实例可以有很多个，但是每个类只有一个class对象，
 * 所以不同对象实例的对象锁是互不干扰的，但是每个类只有一个类锁。但是有一点必须注意的是，其实类锁只是一个概念上的东西，并不是真实存在的，
 * 它只是用来帮助我们理解锁定实例方法和静态方法的区别的
 *
 *
 * 
 * 
 * 以下的的代码，第一个方法时用了同步代码块的方式进行同步，传入的对象实例是this，表明是当前对象，当然，如果需要同步其他对象实例，也不可传入其他对象的实例
 * ；第二个方法是修饰方法的方式进行同步。因为第一个同步代码块传入的this，所以两个同步代码所需要获得的对象锁都是同一个对象锁，
 * 下面main方法时分别开启两个线程，分别调用test1和test2方法，那么两个线程都需要获得该对象锁，另一个线程必须等待。上面也给出了运行的结果可以看到
 * ：直到test2线程执行完毕，释放掉锁，test1线程才开始执行。（可能这个结果有人会有疑问，代码里面明明是先开启test1线程，
 * 为什么先执行的是test2呢？这是因为java编译器在编译成字节码的时候，会对代码进行一个重排序，也就是说，编译器会根据实际情况对代码进行一个合理的排序，
 * 编译前代码写在前面，在编译后的字节码不一定排在前面，所以这种运行结果是正常的， 这里是题外话，最主要是检验synchronized的用法的正确性）
 * 
 * 输入结果是两种情况：（总之：test2和test1方法不管哪儿一个先执行，都必须是执行完一个，在执行下一个方法） 结果1： test2 : 4 test2
 * : 3 test2 : 2 test2 : 1 test2 : 0 test1 : 4 test1 : 3 test1 : 2 test1 : 1
 * test1 : 0 结果2： test1 : 4 test1 : 3 test1 :2 test1 : 1 test1 : 0 test2 : 4
 * test2 : 3 test2 : 2 test2 : 1 test2 : 0
 * 
 * 如果我们把test2方法的synchronized关键字去掉，执行结果会如何呢？ 输出结果的一种情况： test1 : 4 test2 : 4
 * test2 : 3 test1 : 3 test1 : 2 test2 : 2 test2 : 1 test1 : 1 test2 : 0 test1 :
 * 0
 * 
 * 上面是执行结果，我们可以看到，结果输出是交替着进行输出的，这是因为，某个线程得到了对象锁，但是另一个线程还是可以访问没有进行同步的方法或者代码。
 * 进行了同步的方法（加锁方法）和没有进行同步的方法（普通方法）是互不影响的，一个线程进入了同步方法，得到了对象锁，其他线程还是可以访问那些没有同步的方法（
 * 普通方法）。这里涉及到内置锁的一个概念（此概念出自java并发编程实战第二章）：对象的内置锁和对象的状态之间是没有内在的关联的，
 * 虽然大多数类都将内置锁用做一种有效的加锁机制，但对象的域并不一定通过内置锁来保护。当获取到与对象关联的内置锁时，并不能阻止其他线程访问该对象，
 * 当某个线程获得对象的锁之后，只能阻止其他线程获得同一个锁。之所以每个对象都有一个内置锁，是为了免去显式地创建锁对象。
 * 
 * 所以synchronized只是一个内置锁的加锁机制，当某个方法加上synchronized关键字后，就表明要获得该内置锁才能执行，
 * 并不能阻止其他线程访问不需要获得该内置锁的方法。
 * 
 * 
 * 
 * 
 * 
 */

public class TestSynchronized {
	public void test1() {
		synchronized (this) {
			int i = 5;
			while (i-- > 0) {
				System.out.println(Thread.currentThread().getName() + " : " + i);
				try {
					Thread.sleep(500);
				} catch (InterruptedException ie) {
				}
			}
		}
	}

	public synchronized void test2() {
//	public void test2() {
		int i = 5;
		while (i-- > 0) {
			System.out.println(Thread.currentThread().getName() + " : " + i);
			try {
				Thread.sleep(500);
			} catch (InterruptedException ie) {
			}
		}
	}

	public static void main(String[] args) {
		final TestSynchronized myt2 = new TestSynchronized();
		Thread test1 = new Thread(new Runnable() {
			public void run() {
				myt2.test1();
			}
		}, "test1");
		Thread test2 = new Thread(new Runnable() {
			public void run() {
				myt2.test2();
			}
		}, "test2");
		test1.start();
		;
		test2.start();
		// TestRunnable tr=new TestRunnable();
		// Thread test3=new Thread(tr);
		// test3.start();
	}

}
