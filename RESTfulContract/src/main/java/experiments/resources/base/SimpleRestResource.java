package experiments.resources.base;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import experiments.exceptions.NotSupportedException;
import experiments.exceptions.QuerySyntaxException;

/**
 * Starting point for the most basic REST resource defining the paths for all
 * CRUD operations and filters. This abstract resource is suitable for simple
 * resources, uniquely identified by their IDs, such as
 * interface_url/resource/{resourceId}.
 * 
 * @author zlatka
 * 
 */
public abstract class SimpleRestResource<T> {

    /**
     * Used to find all instances of a given resource, for example all players.
     * 
     * @return a collection with all instances of the given resource
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract Collection<T> getResources() throws NotSupportedException;

    /**
     * Finds a resource by its ID.
     * 
     * @param resourceId
     *            the ID of the resource
     * @return the resource found
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract T getResource(Long resourceId) throws NotSupportedException;

    /**
     * Saves resource.
     * 
     * @param resource
     *            the resource to be saved
     * @return the resource after being saved (some attributes such as ID or
     *         saveDate might have been changed in the saving process)
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract T saveResource(T resource) throws NotSupportedException;

    /**
     * Updates given resource by setting new property values.
     * 
     * @param resource
     *            the resource with the new properties set
     * @return the updated resource
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract T updateResource(T resource) throws NotSupportedException;

    /**
     * Deletes resource with given ID.
     * 
     * @param resourceId
     *            the ID of the resource to be deleted
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract void deleteResource(Long resourceId) throws NotSupportedException;

    /**
     * Retrieves all resources that has an attribute which value contains the
     * value string specified. For example: resource that has attribute
     * "destination", can be searched. All destination attributes that contain
     * the string "NYC" can be retrieved by calling the method with attribute
     * "destination" and value "NYC".
     * 
     * @param attribute
     *            the name of the attribute to be considered in the search
     * @param value
     *            the string that has to be contained in the attribute's value
     * @return a collection with all resources found relevant for the search
     * @throws NotSupportedException
     *             thrown in case the operation is not supported or should not
     *             be available for the given resource
     */
    public abstract Collection<T> getFilteredResourcesLike(String attribute, String value) throws NotSupportedException;

    private final static Logger LOGGER = LoggerFactory.getLogger(SimpleRestResource.class);

    /**
     * GET <website_url>/<resource_name> retrieves all resources from given type
     * 
     * @return HTTP response OK 200 with all resources from the given type as
     *         JSON collection
     * @throws NotSupportedException
     *             thrown in case the operation is not supported
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllResources() throws NotSupportedException {
        return Response.ok(getResources()).build();
    }

    /**
     * GET <website_url>/<resource_name>/<resource_id> retrieves specific
     * resource by its ID
     * 
     * @param resourceId
     *            the ID of the resource to be found
     * @return HTTP response OK 200 with the resource found as JSON object
     * @throws NotSupportedException
     *             thrown in case the operation is not supported
     */
    @GET
    @Path("{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response find(@PathParam("resourceId") Long resourceId) throws NotSupportedException {
        return Response.ok(getResource(resourceId)).build();
    }

    /**
     * POST <website_url>/<resource_name> saves new resource
     * 
     * @param resource
     *            the resource object as JSON object
     * @return HTTP response OK 200 with the saved resource object as JSON
     *         object
     * @throws NotSupportedException
     *             thrown in case the operation is not supported
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response save(T resource) throws NotSupportedException {
        return Response.ok(saveResource(resource)).build();
    }

    /**
     * PUT <website_url>/<resource_name> updates existing resource
     * 
     * @param resource
     *            the resource object as JSON object with all new attribute
     *            values set
     * @return HTTP response OK 200 with the updated resource object as JSON
     *         object
     * @throws NotSupportedException
     *             thrown in case the operation is not supported
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(T resource) throws NotSupportedException {
        return Response.ok(updateResource(resource)).build();
    }

    /**
     * DELETE <website_url>/<resource_name>/<resource_id> deletes existing
     * resource
     * 
     * @param resourceId
     *            the id of the resource to be deleted
     * @return HTTP response OK 200
     * @throws NotSupportedException
     *             thrown in case the operation is not supported
     */
    @DELETE
    @Path("{resourceId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(@PathParam("resourceId") Long resourceId) throws NotSupportedException {
        deleteResource(resourceId);
        return Response.ok().build();
    }

    @GET
    @Path("like")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterByAttributeLike(@QueryParam("attribute") String attribute, @QueryParam("value") String value)
            throws NotSupportedException {
        return Response.ok(getFilteredResourcesLike(attribute, value)).build();
    }

    @GET
    @Path("{attribute}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterByAttribute(@PathParam("attribute") String attribute, @PathParam("value") String value)
            throws NotSupportedException {
        return Response.ok(getFilteredResourcesWithExactMatch(attribute, value)).build();
    }

    @GET
    @Path("find")
    @Produces(MediaType.APPLICATION_JSON)
    public Response filterResourcesBy(@QueryParam("attributes") String attributes, @QueryParam("values") String values)
            throws NotSupportedException, QuerySyntaxException {

        if (attributes == null && values == null) {
            return Response.ok(getResources()).build();
        } else if (attributes == null || values == null) {
            throw new QuerySyntaxException();
        } else if (attributes == "" || values == "") {
            throw new QuerySyntaxException();
        }

        String[] attrs = attributes.split(",");
        String[] vals = values.split(",");

        if (attrs.length != vals.length) {
            throw new QuerySyntaxException();
        }

        return Response.ok(getFilteredResources(attrs, vals)).build();
    }

    public Collection<T> getFilteredResources(String[] attributes, String[] values) throws NotSupportedException {
        Set<T> result = new HashSet<T>();
        for (int i = 0; i < attributes.length; i++) {
            result.addAll(getFilteredResourcesWithExactMatch(attributes[i], values[i]));
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    public Collection<T> getFilteredResourcesWithExactMatch(String attribute, String value) throws NotSupportedException {
        Set<T> result = new HashSet<T>();
        Collection<T> allResources = getResources();
        Method method = null;
        Class<T> type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        for (Method m : type.getDeclaredMethods()) {
            if (m.getName().equalsIgnoreCase("get" + attribute)) {
                method = m;
            }
        }
        if (method != null) {
            for (T t : allResources) {
                try {
                    Class<?> returnType = method.getReturnType();
                    Object o = method.invoke(t);
                    if (returnType.cast(o).equals(returnType.getConstructor(String.class).newInstance(value))) {
                        result.add(t);
                    }
                } catch (Exception e) {
                    LOGGER.error("An exception occurred while trying to filter resource ", e);
                }
            }
        }
        return result;
    }

}
