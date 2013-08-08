package experiments.resources;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ws.rs.Path;

import experiments.MockupData;
import experiments.exceptions.NotSupportedException;
import experiments.resources.dtos.Test;

/**
 * @author zlatka
 * 
 */
@Path("test")
public class TestResource extends RestResource<Test> {

    @Override
    public List<Test> getResources() throws NotSupportedException {
        return new MockupData().getTests();
    }

    @Override
    public Test getResource(Long resourceId) throws NotSupportedException {
        Test t = new MockupData().findTest(resourceId);
        return t;
    }

    @Override
    public Test saveResource(Test resource) throws NotSupportedException {
        MockupData md = new MockupData();
        Test test = md.saveTest(resource);
        return test;
    }

    @Override
    public Test updateResource(Test resource) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public void deleteResource(Long resourceId) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public Collection<Test> getFilteredResources(String[] attributes, String[] values) throws NotSupportedException {
        List<Test> result = new ArrayList<Test>();
        Map<Method, String> invocationMethods = new HashMap<Method, String>();
        for (Method m : Test.class.getDeclaredMethods()) {

            for (int i = 0; i < attributes.length; i++) {
                if (m.getName().equalsIgnoreCase("get" + attributes[i])) {
                    invocationMethods.put(m, values[i]);
                }
            }
        }

        for (Test t : getResources()) {
            for (Entry<Method, String> e : invocationMethods.entrySet()) {
                Method method = e.getKey();
                String value = e.getValue();
                try {
                    Class returnType = method.getReturnType();
                    Object o = method.invoke(t);
                    if (returnType.cast(o).equals(returnType.getConstructor(String.class).newInstance(value))) {
                        result.add(t);
                    }
                } catch (IllegalAccessException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (IllegalArgumentException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InvocationTargetException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (InstantiationException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (NoSuchMethodException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                } catch (SecurityException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }

        return result;
    }

}
