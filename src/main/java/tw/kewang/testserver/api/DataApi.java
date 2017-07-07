package tw.kewang.testserver.api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;

@Path("data")
public class DataApi {
    @Produces("application/json")
    @Path("{name}")
    @GET
    public Response get(@Context HttpHeaders headers, @PathParam("name") String name) {
        return Response.ok().entity("Hello " + name).build();
    }
}