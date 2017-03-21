package com.teambronto.svc.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.ws.spi.http.HttpContext;

import org.apache.htrace.Tracer;

@Path("/htrace-svc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class HTraceServiceResource {
	//not sure if we need this
	private Tracer hTracer;
	
	
    @GET
    public Response getEmployeeDataById(@Context HttpContext ctx, @QueryParam("id") long id) {
        ctx.getAttribute("HTRACE");
        return null;
    }
}
