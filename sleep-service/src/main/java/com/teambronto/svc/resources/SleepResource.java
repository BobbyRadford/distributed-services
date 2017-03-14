package com.teambronto.svc.resources;

import brave.Span;
import brave.Tracer;
import brave.propagation.TraceContext;
import com.google.common.base.Optional;
import com.teambronto.svc.SleepApplication;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.okhttp3.OkHttpSender;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path(SleepApplication.SERVICE_URI)
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class SleepResource {
    /** The sender provides the means of sending Spans to the Zipkin server */
    private OkHttpSender sender;
    /** The tracer collects tracing data to send to Zipkin via the OkHttpSender */
    private Tracer tracer;

    public SleepResource(OkHttpSender sender) {
        this.sender = sender;
        this.tracer = Tracer.newBuilder()
                .localServiceName(SleepApplication.SERVICE_NAME)
                .reporter(AsyncReporter.builder(sender).build())
                .build();
    }

    @GET
    public String getEmployeeData(@QueryParam("id")      int id,
                                  @QueryParam("traceId") long traceId,
                                  @QueryParam("spanId")  long spanId) {
        Span span = tracer.joinSpan(TraceContext.newBuilder().traceId(traceId).spanId(spanId).build());

        return null;
    }
}
