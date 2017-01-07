package com.tsty.thread.readwrite.synimpl;

/**
 * 代码出自：http://www.importnew.com/22971.html
 *
 */
/**
 * 需求：这是一个在线文件编辑器。同一份文件，一个人在读的时候，其他人不能写；
 * 同理，一个人在写的时候，其他人也不能读。也就是说，要么读，要么写，这两件事情不能同时进行。
 * 前提：可以多个人同时读，但是不能多个人同时写！
 * */

/**
 * 1. Data 类中封装了一个 char 数组类型的 buffer 成员变量。
 * 2. 在构造器中传入一个 size，表示 buffer 的长度，并在其中创建并初始化这个 buffer，使其每个字符都为“*”。
 * 3. 提供两个方法，一个负责读取，另一个负责写入。在读取方法中只需遍历 buffer，将结果不断 append 到一个 StringBuilder 中，最终将其转为 String 并返回。
 * 4. 在写入方法中传入一个字符，仍然是遍历 buffer，赋值 buffer 中的每个字符，这样可以使 buffer 中每个字符都是相同的。
 * 5. 故意在读写方法中加入了一个 sleep() 方法，让程序运行慢一点，模拟比较耗时的操作。而且故意让写入比读取慢一点，因为将 sleep() 方法放入了 write() 方法的循环体中，而 read() 方法却没有。
 */

public class Data {

	private final char[] buffer;

	public Data(int size) {
		this.buffer = new char[size];
		for (int i = 0; i < size; i++) {
			buffer[i] = '*';
		}
	}

	public synchronized String read() {
		StringBuilder result = new StringBuilder();
		for (char c : buffer) {
			result.append(c);
		}
		sleep(100);
		return result.toString();
	}

	public synchronized void write(char c) {
		for (int i = 0; i < buffer.length; i++) {
			buffer[i] = c;
			sleep(100);
		}
	}

	private void sleep(long ms) {
		try {
			Thread.sleep(ms);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
