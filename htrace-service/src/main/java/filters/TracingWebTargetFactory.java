package filters;

import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.Context;

import okhttp3.WebSocket.Factory;

public class TracingWebTargetFactory implements Factory<WebTarget> {

    @Context
    private ContainerRequestContext containerRequestContext;

    @Inject
    public TracingWebTargetFactory(ContainerRequestContext containerRequestContext) {
        this.containerRequestContext = containerRequestContext;
    }

    public WebTarget provide() {
        Client client = ClientBuilder.newClient();
        client.register(new TracingClientRequestFilter(containerRequestContext));
        //add the path to the api here
        return client.target("path/to/api");
    }

    public void dispose(WebTarget target) {

    }
}