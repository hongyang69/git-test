package com.tsty.initialize;

public class TestInitializeOrder {
	public static void main(String[] args) {
		InitializeOrder initializeOrder = new InitializeOrder();
		System.err.println(initializeOrder.order);
	}
}

class InitializeOrder{
	int order = 1;
	
	public InitializeOrder() {
		super();
		System.err.println(order);
		order = 2;
		System.err.println(order);
	}
}
