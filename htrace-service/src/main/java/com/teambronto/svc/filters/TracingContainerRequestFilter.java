package com.teambronto.svc.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

public class TracingContainerRequestFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
    	long traceId = (long)containerRequestContext.getProperty("H-TraceID");
    	long spanId = (long)containerRequestContext.getProperty("H-SpanID");

    	//TODO: Initialize HTrace Object
        // Htrace htrace = new HTrace(traceId, spanId);

        // containerRequestContext.setProperty("HTRACE", htrace);
    }
}
