package experiments.resources.examples;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import experiments.MockupData;
import experiments.exceptions.NotSupportedException;
import experiments.resources.base.DependentRestResource;
import experiments.resources.dtos.SubTest;

/**
 * @author zlatka
 * 
 */
@Path("/test/{id}/subtest")
public class SubtestResource extends DependentRestResource<SubTest> {

    @Override
    public Collection<SubTest> getResources(Long baseResourceId) throws NotSupportedException {
        return new MockupData().findSubTestsForTest(baseResourceId);
    }

    @Override
    public SubTest getResource(Long baseResourceId, Long resourceId) throws NotSupportedException {
        return new MockupData().findSubTest(resourceId, baseResourceId);
    }

    @GET
    @Path("hey")
    public Response justSayingHey() {
        return Response.ok("Hey hey!").build();
    }

    @Override
    public SubTest saveResource(Long baseResourceId, SubTest resource) throws NotSupportedException {
        return new MockupData().saveSubTest(resource, baseResourceId);
    }

    @Override
    public SubTest updateResource(Long baseResourceId, SubTest resource) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public void deleteResource(Long baseResourceId, Long resourceId) throws NotSupportedException {
        throw new NotSupportedException();
    }

}
