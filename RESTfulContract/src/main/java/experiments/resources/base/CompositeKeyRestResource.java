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
 * Defines REST resource which key is composite. For example the resource
 * "character" may be uniquely defined by two components - "server" and
 * "account". Thus, server id is the first and account id the second key
 * necessary to uniquely identify the "character" resource. The path of the
 * composite key resource should be:
 * interface_url/key_resource_1/{key1}//key_resource_2/{key2}/resource
 * 
 * @author zlatka
 * 
 */
public abstract class CompositeKeyRestResource<T> {

    public abstract Collection<T> getResources(Long firstKeyId, Long secondKeyId) throws NotSupportedException;

    public abstract T getResource(Long firstKeyId, Long secondKeyId, Long resourceId) throws NotSupportedException;

    public abstract T saveResource(Long firstKeyId, Long secondKeyId, T resource) throws NotSupportedException;

    public abstract T updateResource(Long firstKeyId, Long secondKeyId, T resource) throws NotSupportedException;

    public abstract void deleteResource(Long firstKeyId, Long secondKeyId, Long resourceId) throws NotSupportedException;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResources(@PathParam("key1") Long firstKeyId, @PathParam("key2") Long secondKeyId) throws NotSupportedException {
        return Response.ok(getResources(firstKeyId, secondKeyId)).build();
    }

    @GET
    @Path("{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("key1") Long firstKeyId, @PathParam("key2") Long secondKeyId, @PathParam("resourceId") Long resourceId)
            throws NotSupportedException {
        return Response.ok(getResource(firstKeyId, secondKeyId, resourceId)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(@PathParam("key1") Long firstKeyId, @PathParam("key2") Long secondKeyId, T resource) throws NotSupportedException {
        return Response.ok(saveResource(firstKeyId, secondKeyId, resource)).build();
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(@PathParam("key1") Long firstKeyId, @PathParam("key2") Long secondKeyId, T resource)
            throws NotSupportedException {
        return Response.ok(updateResource(firstKeyId, secondKeyId, resource)).build();
    }

    @DELETE
    @Path("{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("key1") Long firstKeyId, @PathParam("key2") Long secondKeyId, @PathParam("resourceId") Long resourceId)
            throws NotSupportedException {
        deleteResource(firstKeyId, secondKeyId, resourceId);
        return Response.ok().build();
    }

    // TODO filters

}
