package com.tsty.code.performance;

public class GoodCode {

	public static final int DEBUG_MODE = 1;
	public static final int PRODUCTION_MODE = 2;

	public static void calculationWithPrint(int logMode) {
		double someValue = 0D;
		for (int i = 0; i < 10000; i++) {
			someValue = someValue + i;
			myPrintMethod(logMode, someValue);
		}
	}

	public static void myPrintMethod(int logMode, double value) {
		if (logMode > GoodCode.DEBUG_MODE) {
			return;
		}
		System.out.println(value);
	}

	public static void main(String[] n) {
		long startCalculationWithPrint = System.currentTimeMillis();
//		GoodCode.calculationWithPrint(GoodCode.PRODUCTION_MODE);
		GoodCode.calculationWithPrint(GoodCode.DEBUG_MODE);
		long endCalculationWithPrint = System.currentTimeMillis();
        System.err.println("calculationWithPrint cost time:" + (endCalculationWithPrint - startCalculationWithPrint));
	}
}
