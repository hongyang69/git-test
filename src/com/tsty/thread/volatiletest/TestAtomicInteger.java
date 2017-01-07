package com.tsty.thread.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * atomic是利用CAS来实现原子性操作的（Compare And
 * Swap），CAS实际上是利用处理器提供的CMPXCHG指令实现的，而处理器执行CMPXCHG指令是一个原子性操作。
 * 
 *
 */

public class TestAtomicInteger {
	public AtomicInteger inc = new AtomicInteger();

	public void increase() {
		inc.getAndIncrement();
	}

	public static void main(String[] args) {
		final TestAtomicInteger test = new TestAtomicInteger();
		for (int i = 0; i < 10; i++) {
			new Thread() {
				public void run() {
					for (int j = 0; j < 1000; j++)
						test.increase();
				};
			}.start();
		}

		while (Thread.activeCount() > 1) // 保证前面的线程都执行完
			Thread.yield();
		System.out.println(test.inc);
	}
}
