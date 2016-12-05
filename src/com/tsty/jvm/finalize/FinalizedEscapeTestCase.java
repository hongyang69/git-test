package com.tsty.jvm.finalize;

/**
 * @Described：逃逸分析测试用例
 */
public class FinalizedEscapeTestCase {
 
    public static FinalizedEscapeTestCase caseForEscape = null;
    @Override
    protected void finalize() throws Throwable {
       super.finalize();
       System.out.println("哈哈，我已逃逸！");
       caseForEscape = this;
    }
}

