package com.teambronto.svc.filters;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import java.io.IOException;

public class TracingContainerRequestFitler implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        System.out.println("traceID:\t" + containerRequestContext.getProperty("H-traceID"));
        System.out.println("spanID:\t"  + containerRequestContext.getProperty("H-spanID"));
    }
}
