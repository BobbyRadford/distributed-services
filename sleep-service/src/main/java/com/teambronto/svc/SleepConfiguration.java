package com.teambronto.svc;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import zipkin.reporter.okhttp3.OkHttpSender;

import javax.validation.constraints.NotNull;

public class SleepConfiguration extends Configuration {
    @NotNull
    @JsonProperty
    private String zipkinReportingAddress;

    String getZipkinReportingAddress() {
        return zipkinReportingAddress;
    }

    OkHttpSender getOkHttpSender() {
        return OkHttpSender.create(getZipkinReportingAddress());
    }
}
