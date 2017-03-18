package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/htrace-svc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HTraceServiceResource {

    @GET
    public Response getEmployeeDataById(@QueryParam("id") long id) {
        org.apache.htrace.Trace
        return null;
    }
}
