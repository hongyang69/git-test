package com.tsty.thread.readwrite.synimpl;

import java.nio.Buffer;

public class TestReadWrite {

	private final char[] buffer;

	public TestReadWrite(int size) {
		this.buffer = new char[size];
		for (int i = 0; i < size; i++) {
			buffer[i] = '*';
		}
	}

	public static void main(String[] args) {
		TestReadWrite testReadWrite = new TestReadWrite(100);
	}
	
}
