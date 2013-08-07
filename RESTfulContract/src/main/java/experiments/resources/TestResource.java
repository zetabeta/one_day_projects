package experiments.resources;

import java.util.List;

import javax.ws.rs.Path;

import org.codehaus.jackson.map.ObjectMapper;

import experiments.MockupData;
import experiments.exceptions.NotSupportedException;
import experiments.resources.dtos.Test;

/**
 * @author zlatka
 * 
 */
@Path("test")
public class TestResource extends RestResource {

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
    public Test saveResource(String resource) throws NotSupportedException {
        MockupData md = new MockupData();
        ObjectMapper mapper = new ObjectMapper();
        Test test;

        try {
            test = mapper.readValue(resource, Test.class);
            test = md.saveTest(test);
            return test;
        } catch (Exception e) {
            // TODO Auto-generated catch block
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see experiments.resources.RestResource#updateResource(java.lang.String)
     */
    @Override
    public Object updateResource(String resource) throws NotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see experiments.resources.RestResource#deleteResource(java.lang.Long)
     */
    @Override
    public void deleteResource(Long resourceId) throws NotSupportedException {
        // TODO Auto-generated method stub

    }
}
