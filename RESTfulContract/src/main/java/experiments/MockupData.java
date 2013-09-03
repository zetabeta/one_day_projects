package experiments;

import java.util.ArrayList;
import java.util.List;

import experiments.resources.dtos.SubTest;
import experiments.resources.dtos.Test;

/**
 * Creates mockup data for experimenting and testing
 * 
 * @author zlatka
 * 
 */
public class MockupData {

    private List<Test> tests;
    private List<SubTest> subtests;

    public MockupData() {
        this.tests = new ArrayList<Test>();
        Test t1 = new Test();
        t1.setId(1L);
        t1.setIntgr(new Integer(49));
        t1.setStr("foo");
        this.tests.add(t1);
        Test t2 = new Test();
        t2.setId(2L);
        t2.setIntgr(new Integer(8));
        t2.setStr("foo2");
        this.tests.add(t2);
        Test t3 = new Test();
        t3.setId(3L);
        t3.setIntgr(new Integer(999));
        t3.setStr("yahookikilooooo");
        this.tests.add(t3);

        this.subtests = new ArrayList<SubTest>();
        SubTest s1 = new SubTest();
        s1.setContent("lalala");
        s1.setId(1L);
        s1.setTestId(1L);
        this.subtests.add(s1);
        SubTest s2 = new SubTest();
        s2.setContent("hmmmm");
        s2.setId(22L);
        s2.setTestId(3L);
        this.subtests.add(s2);
        SubTest s3 = new SubTest();
        s3.setContent("ohoo");
        s3.setId(23L);
        s3.setTestId(3L);
        this.subtests.add(s3);
    }

    public List<Test> getTests() {
        return this.tests;
    }

    public List<SubTest> getSubTests() {
        return this.subtests;
    }

    public Test findTest(Long id) {
        for (Test t : tests) {
            if (t.getId() == id) {
                return t;
            }
        }
        return null;
    }

    public List<SubTest> findSubTestsForTest(Long testId) {
        List<SubTest> filteredsubtests = new ArrayList<SubTest>();
        for (SubTest t : subtests) {
            if (t.getTestId() == testId) {
                filteredsubtests.add(t);
            }
        }
        return filteredsubtests;
    }

    public SubTest findSubTest(Long id, Long testId) {
        for (SubTest t : subtests) {
            if (t.getId() == id && t.getTestId() == testId) {
                return t;
            }
        }
        return null;
    }

    public Test saveTest(Test t) {
        t.setId(888L);
        this.tests.add(t);
        return t;
    }

    public SubTest saveSubTest(SubTest t, Long testId) {
        t = new SubTest();
        t.setId(888L);
        t.setContent("brand new subtest");
        t.setTestId(testId);
        this.subtests.add(t);
        return t;
    }

}
