package com.tsty.code.performance;

public class BadCode {  
	/**优化calculationWithPrint()方法，请看GoodCode类； 为了避免CPU浪费，最好的办法是引入一个包装的方法，
	 * 把System.out.println()抽成一个方法myPrintMethod();
	 */
	
    public static void calculationWithPrint(){  //calculationWithPrint() 方法居然需要令人难以置信的10.52 s来执行！
        double someValue = 0D;  
        for (int i = 0; i < 10000; i++) {  
            System.out.println(someValue = someValue + i);  
        }      
    }  
    public static void calculationWithOutPrint(){  //calculationWithOutPrint() 方法执行时间是0.001204 s. 作为对比

            double someValue = 0D;  
            for (int i = 0; i < 10000; i++) {  
                someValue = someValue + i;  
            }  

    }  
    public static void main(String [] n) {
    	long startCalculationWithPrint = System.currentTimeMillis();
        BadCode.calculationWithPrint();
        long endCalculationWithPrint = System.currentTimeMillis();
        long startCalculationWithOutPrint = System.currentTimeMillis();
        BadCode.calculationWithOutPrint();
        long endCalculationWithOutPrint = System.currentTimeMillis();
        System.err.println("calculationWithPrint cost time:" + (endCalculationWithPrint - startCalculationWithPrint));
        System.err.println("calculationWithOutPrint cost time:" + (endCalculationWithOutPrint - startCalculationWithOutPrint));
    }  
}