package experiments.resources;

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
public abstract class RestResource {

    public abstract Collection getResources() throws NotSupportedException;

    public abstract Object getResource(Long resourceId) throws NotSupportedException;

    public abstract Object saveResource(String resource) throws NotSupportedException;

    public abstract Object updateResource(String resource) throws NotSupportedException;

    public abstract void deleteResource(Long resourceId) throws NotSupportedException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResources() throws NotSupportedException {
        return Response.ok(getResources()).build();
    }

    @GET
    @Path("{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("resourceId") Long resourceId) throws NotSupportedException {
        return Response.ok(getResource(resourceId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(String resource) throws NotSupportedException {
        return Response.ok(saveResource(resource)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(String resource) throws NotSupportedException {
        return Response.ok(updateResource(resource)).build();
    }

    @DELETE
    @Path("{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("resourceId") Long resourceId) throws NotSupportedException {
        deleteResource(resourceId);
        return Response.ok().build();
    }

}
