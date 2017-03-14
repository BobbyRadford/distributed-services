package com.teambronto.svc.resources;

import brave.Span;
import brave.Tracer;
import brave.internal.recorder.Recorder;
import brave.propagation.Propagation;
import com.google.common.base.Optional;
import com.teambronto.svc.NumberGenApplication;
import zipkin.Endpoint;
import zipkin.reporter.AsyncReporter;
import zipkin.reporter.okhttp3.OkHttpSender;

import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;

import java.math.BigInteger;
import java.util.Random;

@Path("/number-gen-svc")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NumberGenResource {
    /** The sender provides the means of sending Spans to the Zipkin server */
    private OkHttpSender sender;
    /** The tracer collects tracing data to send to Zipkin via the OkHttpSender */
    private Tracer tracer;

    public NumberGenResource(OkHttpSender sender) {
        this.sender = sender;
        this.tracer = Tracer.newBuilder()
                .localServiceName(NumberGenApplication.SERVICE_NAME)
                .reporter(AsyncReporter.builder(sender).build())
                .build();
    }

    
    /**
     * Sends a request to /sleep-svc with an employee's ID and expects the data for that employee to be returned.
     * @param id The employee's ID. This is optional. If not defined, a random value between 1 and 30 will be chosen.
     * @return The employee data for the employee with the specified ID.
     */
    @GET
    public String getEmployeeData(@QueryParam("id")Optional<Integer> id) {
        // Start a new Brave Trace.
        Span span = tracer.newTrace().name("getEmployeeData").kind(Span.Kind.CLIENT);
        // TODO: look here https://github.com/openzipkin/brave/blob/master/brave/README.md
        
        span.start();
        
        String employee = ClientBuilder.newClient().target("http://localhost:8889/sleep-svc")
                .queryParam("id", id.or(new Random().nextInt(29) + 1))
                .queryParam("traceId", span.context().traceId())
                .queryParam("spanId", span.context().spanId())
                .request(MediaType.APPLICATION_JSON)
                .get(String.class);

        span.finish();
        //grab the traceID
        Long traceID = span.context().traceId();
        //if the traceID is not negative, that means the trace did not produce an ID that is larger than LONG.MAX_VALUE
        if(traceID > 0){
        	String hex = Long.toHexString(traceID);
        	employee = employee + "\nConverted hex = " + hex;
        //otherwise we need to use BigInteger to be able to fit the entire traceID, and convert this traceID to hex
        //this only works if the traceID is not FFFFFFFFFFFFFFFF
        } else if (traceID < 0){
        	BigInteger b = new BigInteger(Long.toString(traceID));
        	Long difference = (Math.abs(Long.MIN_VALUE) - Math.abs(traceID)) + 1;
        	BigInteger maxLong = new BigInteger(Long.toString(Long.MAX_VALUE));
        	BigInteger diff = new BigInteger(Long.toString(difference));
        	b = maxLong.add(diff);
//        	BigInteger toHex = new BigInteger(b.toString(), 16);
        	employee = employee + "\nConverted TraceID = " + b.toString(16);
        	
        	
        } else {
        	//shouldn't ever reach this
        	String convertedID = "Something went wrong. Ignore anything added to this end of this message.";
        	employee = employee + convertedID;
        }
        employee = employee + "\n Original Trace ID = " + span.context().traceId() + "\n Original Span ID = " + span.context().spanId();
        return employee;
    }
}
