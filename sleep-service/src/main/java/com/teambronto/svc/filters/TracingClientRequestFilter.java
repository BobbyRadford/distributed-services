package com.teambronto.svc.filters;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import java.io.IOException;

public class TracingClientRequestFilter implements ClientRequestFilter {
    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        clientRequestContext.setProperty("H-traceID", 1L);
        clientRequestContext.setProperty("H-spanID", 1L);
    }
}
