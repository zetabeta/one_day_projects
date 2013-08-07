package experiments.resources;

import java.util.Collection;

import javax.ws.rs.Path;

import experiments.exceptions.NotSupportedException;

/**
 * @author zlatka
 * 
 */
@Path("player")
public class PlayerResource extends RestResource {

    /*
     * (non-Javadoc)
     * 
     * @see experiments.resources.RestResource#getResources()
     */
    @Override
    public Collection getResources() throws NotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see experiments.resources.RestResource#getResource(java.lang.Long)
     */
    @Override
    public Object getResource(Long resourceId) throws NotSupportedException {
        // TODO Auto-generated method stub
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see experiments.resources.RestResource#saveResource(java.lang.String)
     */
    @Override
    public Object saveResource(String resource) throws NotSupportedException {
        // TODO Auto-generated method stub
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
