package com.teambronto.svc;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import com.teambronto.svc.resources.NumberGenResource;
import zipkin.reporter.okhttp3.OkHttpSender;

public class NumberGenApplication extends Application<NumberGenConfiguration> {
    public static final String SERVICE_NAME = "NumberGenService";
    public static final String SERVICE_URI = "/number-gen-svc";

    public static void main(String[] args) {
        try {
            new NumberGenApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return SERVICE_NAME;
    }

    @Override
    public void run(NumberGenConfiguration config, Environment environment) throws Exception {
        // Register the NumberGenResource to the environment.
        environment.jersey().register(new NumberGenResource(config.getOkHttpSender()));
    }
}
