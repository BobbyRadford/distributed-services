package resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.htrace.Tracer;

@Path("/htrace-svc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HTraceServiceResource {
	//not sure if we need this
	private Tracer hTracer;
	
	
    @GET
    public Response getEmployeeDataById(@QueryParam("id") long id) {
    	
        return null;
    }
}
