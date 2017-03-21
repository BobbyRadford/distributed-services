package com.teambronto.svc;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.dropwizard.Configuration;
import zipkin.reporter.okhttp3.OkHttpSender;

public class HTraceServiceConfiguration extends Configuration{
		@NotNull
	    @JsonProperty
	    private String zipkinReportingAddress;

	    String getZipkinReportingAddress() {
	        return zipkinReportingAddress;
	    }

}
