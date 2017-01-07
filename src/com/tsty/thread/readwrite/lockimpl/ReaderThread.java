package com.tsty.thread.readwrite.lockimpl;

/**
 * 在 ReaderThread 中通过一个死循环去不断地读取 Data 中的数据，并将结果打印出来。
 * 
 */

public class ReaderThread extends Thread {

	private final Data data;

	public ReaderThread(Data data) {
		this.data = data;
	}

/*	@Override
	public void run() {
		while (true) {
			String result = data.read();
			System.out.println(Thread.currentThread().getName() + " => " + result);
		}
	}*/
	
	public void run() {
        while (true) {
            long begin = System.currentTimeMillis();
            for (int i = 0; i < 10; i++) {
                String result = null;
				try {
					result = data.read();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                System.out.println(Thread.currentThread().getName() + " => " + result);
            }
            long time = System.currentTimeMillis() - begin;
            System.out.println(Thread.currentThread().getName() + " -- " + time + "ms");
        }
    }
}