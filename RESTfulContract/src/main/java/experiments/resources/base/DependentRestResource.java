package experiments.resources.base;

import java.util.Collection;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import experiments.exceptions.NotSupportedException;

/**
 * Defines REST resource which is dependent on other REST resource. For example,
 * "ban" resource does not make sense without the corresponding "account"
 * resource. The dependent REST resource is the one of interest and all queries
 * assume that the base resource id is known. The path of the dependent
 * resources should be: baseresource_name/{id}/dependentresource_name
 * 
 * @author zlatka
 * 
 */
public abstract class DependentRestResource<T> {

    /**
     * Collects all dependent resources from given type for specified base
     * resource. For example, all transactions for given player.
     * 
     * @param baseResourceId
     *            the ID of the base resource
     * @return a collection with all dependent resources of the base resource
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract Collection<T> getResources(Long baseResourceId) throws NotSupportedException;

    /**
     * Retrieves specific dependent resource. For example, ban with specific id
     * for given player.
     * 
     * @param baseResourceId
     *            the base resource ID
     * @param resourceId
     *            the dependent resource id
     * @return the dependent resource
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract T getResource(Long baseResourceId, Long resourceId) throws NotSupportedException;

    /**
     * Saves new instance of a dependent resource for given base resource. For
     * example, saves new ban or transaction for given player.
     * 
     * @param baseResourceId
     *            the base resource ID
     * @param resource
     *            the new dependent resource to be saved
     * @return the saved dependent resource
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract T saveResource(Long baseResourceId, T resource) throws NotSupportedException;

    /**
     * Updates existing dependent resource for given base resource. For example,
     * sets the ban_expire_date of existing ban for given player to a new value.
     * 
     * @param baseResourceId
     *            the base resource ID
     * @param resource
     *            the dependent resource with new attribute values set
     * @return the updated dependent resource
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract T updateResource(Long baseResourceId, T resource) throws NotSupportedException;

    /**
     * Deletes existing dependent resource for given base resource.
     * 
     * @param baseResourceId
     *            the base resource ID
     * @param resourceId
     *            the dependent resource ID
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract void deleteResource(Long baseResourceId, Long resourceId) throws NotSupportedException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResources(@PathParam("id") Long baseResourceId) throws NotSupportedException {
        return Response.ok(getResources(baseResourceId)).build();
    }

    @GET
    @Path("{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("id") Long baseResourceId, @PathParam("resourceId") Long resourceId) throws NotSupportedException {
        return Response.ok(getResource(baseResourceId, resourceId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@PathParam("id") Long baseResourceId, T resource) throws NotSupportedException {
        return Response.ok(saveResource(baseResourceId, resource)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("id") Long baseResourceId, T resource) throws NotSupportedException {
        return Response.ok(updateResource(baseResourceId, resource)).build();
    }

    @DELETE
    @Path("{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("id") Long baseResourceId, @PathParam("resourceId") Long resourceId) throws NotSupportedException {
        deleteResource(baseResourceId, resourceId);
        return Response.ok().build();
    }
}
