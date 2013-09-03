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
 * @author zlatka
 * 
 */
public abstract class DependentRestResource<T> {

    public abstract Collection<T> getResources(Long baseResourceId) throws NotSupportedException;

    public abstract T getResource(Long baseResourceId, Long resourceId) throws NotSupportedException;

    public abstract T saveResource(Long baseResourceId, T resource) throws NotSupportedException;

    public abstract T updateResource(Long baseResourceId, T resource) throws NotSupportedException;

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
