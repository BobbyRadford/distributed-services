package filters;

import java.io.IOException;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.container.ContainerRequestContext;

public class TracingClientRequestFilter implements ClientRequestFilter{
    
	private ContainerRequestContext containerRequestContext;

	public TracingClientRequestFilter(ContainerRequestContext containerRequestContext){
		this.containerRequestContext = containerRequestContext;
	}
	
	@Override
	public void filter(ClientRequestContext clientRequestContext) throws IOException {
		Long value = (Long) containerRequestContext.getProperty("H-TraceID");
		clientRequestContext.getHeaders().putSingle("Header", 1L);
	}

}
