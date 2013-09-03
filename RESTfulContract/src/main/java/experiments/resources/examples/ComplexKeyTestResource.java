package experiments.resources.examples;

import java.util.Collection;

import javax.ws.rs.Path;

import experiments.exceptions.NotSupportedException;
import experiments.resources.base.ComplexKeyRestResource;
import experiments.resources.dtos.ComplexKeyTest;

/**
 * @author zlatka
 * 
 */
@Path("/key1/{key1}/key2/{key2}/complexkeytest")
public class ComplexKeyTestResource extends ComplexKeyRestResource<ComplexKeyTest> {

    @Override
    public Collection<ComplexKeyTest> getResources(Long firstKeyId, Long secondKeyId) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public ComplexKeyTest getResource(Long firstKeyId, Long secondKeyId, Long resourceId) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public ComplexKeyTest saveResource(Long firstKeyId, Long secondKeyId, ComplexKeyTest resource) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public ComplexKeyTest updateResource(Long firstKeyId, Long secondKeyId, ComplexKeyTest resource) throws NotSupportedException {
        throw new NotSupportedException();
    }

    @Override
    public void deleteResource(Long firstKeyId, Long secondKeyId, Long resourceId) throws NotSupportedException {
        throw new NotSupportedException();
    }

}
