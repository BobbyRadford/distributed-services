package com.teambronto.svc;

import com.teambronto.svc.filters.TracingClientRequestFilter;
import com.teambronto.svc.resources.SleepResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class SleepApplication extends Application<SleepConfiguration> {
    public static final String SERVICE_NAME = "SleepService";
    public static final String SERVICE_URI = "/sleep-svc";

    public static void main(String[] args) {
        try {
            new SleepApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getName() {
        return SERVICE_NAME;
    }

    @Override
    public void run(SleepConfiguration config, Environment environment) throws Exception {
        // Register the NumberGenResource to the environment.
        environment.jersey().register(new SleepResource(config.getOkHttpSender()));
        environment.jersey().register(TracingClientRequestFilter.class);
    }
}
