package com.tsty.jvm.finalize;

/**
 * @Described：逃逸分析测试
 */
public class FinalizedEscape {
	public static void main(String[] args) throws InterruptedException {

		/**
		 * 1、 当程序执行第一行是，因为这个对象没有值，结果肯定是null 
		 * 2、 程序第二行给该对象赋值为新开辟的一个对象 
		 * 3、 第三行打印的时候，肯定是第二行对象的hash代码 
		 * 4、 第四行将该对象重新置为null 
		 * 5、 第五行触发GC 
		 * 6、 为了保证GC能够顺利执行完毕，第六行等待100毫秒 
		 * 7、 第七行打印对应的值，回事null么？一定会是null么？
		 **/
		System.out.println(FinalizedEscapeTestCase.caseForEscape);
		FinalizedEscapeTestCase.caseForEscape = new FinalizedEscapeTestCase();
		System.out.println(FinalizedEscapeTestCase.caseForEscape);
		FinalizedEscapeTestCase.caseForEscape = new FinalizedEscapeTestCase();
		System.out.println(FinalizedEscapeTestCase.caseForEscape);
		FinalizedEscapeTestCase.caseForEscape = null;
		System.gc();
		Thread.sleep(100);
		System.out.println(FinalizedEscapeTestCase.caseForEscape);
	}
}
