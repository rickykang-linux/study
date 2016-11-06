package gc;

/**
 * @author YHJ create at 2011-12-24 下午05:08:09
 * @Described：�?逸分析测�?
 * @FileNmae com.yhj.jvm.gc.finalizeEscape.FinalizedEscape.java
 */
public class GcTest {
    public static void main(String[] args) throws InterruptedException {
        System.out.println(FinalizedEscapeTestCase.caseForEscape);
        FinalizedEscapeTestCase.caseForEscape = new FinalizedEscapeTestCase();
        System.out.println(FinalizedEscapeTestCase.caseForEscape);
        FinalizedEscapeTestCase.caseForEscape = null;
        System.gc();
        Thread.sleep(100);
        System.out.println(FinalizedEscapeTestCase.caseForEscape);
    }
}

/**
 * @author YHJ create at 2011-12-24 下午05:07:05
 * @Described：�?逸分析测试用�?
 * @FileNmae com.yhj.jvm.gc.pojo.TestCaseForEscape.java
 */
class FinalizedEscapeTestCase {
    public static FinalizedEscapeTestCase caseForEscape = null;

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("哈哈，我已�?逸！");
        caseForEscape = this;
    }
}
