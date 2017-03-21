package com.teambronto.svc.filters;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;

public class TracingClientRequestFilter implements ClientRequestFilter {
	
	@Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        clientRequestContext.setProperty("H-traceID", 1L);
        clientRequestContext.setProperty("H-spanID", 1L);
    }

}
