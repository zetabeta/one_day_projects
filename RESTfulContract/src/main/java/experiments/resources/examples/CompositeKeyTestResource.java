package experiments.resources.examples;

import java.util.Collection;

import javax.ws.rs.Path;

import experiments.exceptions.NotSupportedException;
import experiments.resources.base.CompositeKeyRestResource;
import experiments.resources.dtos.CompositeKeyTest;

/**
 * Experimental resource for composite key REST resource.
 * 
 * @author zlatka
 * 
 */
@Path("/key1/{key1}/key2/{key2}/compositekeytest")
public class CompositeKeyTestResource extends CompositeKeyRestResource<CompositeKeyTest> {

    @Override
    public Collection<CompositeKeyTest> getResources(Long firstKeyId, Long secondKeyId) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public CompositeKeyTest getResource(Long firstKeyId, Long secondKeyId, Long resourceId) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public CompositeKeyTest saveResource(Long firstKeyId, Long secondKeyId, CompositeKeyTest resource) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public CompositeKeyTest updateResource(Long firstKeyId, Long secondKeyId, CompositeKeyTest resource) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public void deleteResource(Long firstKeyId, Long secondKeyId, Long resourceId) throws NotSupportedException {
        throw new NotSupportedException();
    }

}
