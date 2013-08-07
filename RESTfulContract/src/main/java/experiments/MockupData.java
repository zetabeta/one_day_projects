package experiments;

import java.util.ArrayList;
import java.util.List;

import experiments.resources.dtos.Test;

/**
 * @author zlatka
 * 
 */
public class MockupData {

    private List<Test> tests;

    public MockupData() {
        this.tests = new ArrayList<Test>();
        Test t1 = new Test();
        t1.setId(1L);
        t1.setIntgr(new Integer(4));
        t1.setStr("foo");
        this.tests.add(t1);
        Test t2 = new Test();
        t2.setId(2L);
        t2.setIntgr(new Integer(8));
        t2.setStr("foo2");
        this.tests.add(t2);
    }

    public List<Test> getTests() {
        return this.tests;
    }

    public Test findTest(Long id) {
        for (Test t : tests) {
            if (t.getId() == id) {
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

}
