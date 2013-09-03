package experiments.resources.examples;

import java.util.Collection;
import java.util.List;

import javax.ws.rs.Path;

import experiments.MockupData;
import experiments.exceptions.NotSupportedException;
import experiments.resources.base.SimpleRestResource;
import experiments.resources.dtos.Test;

/**
 * Experimental resource for simple REST resource.
 * 
 * @author zlatka
 * 
 */
@Path("test")
public class TestResource extends SimpleRestResource<Test> {

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
    public Collection<Test> getFilteredResourcesLike(String attribute, String value) throws NotSupportedException {
        throw new NotSupportedException();
    }

}
