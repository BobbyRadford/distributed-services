import filters.TracingRequestFilter;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import resources.HTraceServiceResource;

public class HTraceServiceApplication extends Application<HTraceServiceConfiguration> {
//implement something like this.
//	public class MyApplication extends ResourceConfig {
//
//	    public MyApplication() {
//	        register(RequestIdContainerFilter.class);
//	        register(new AbstractBinder() {
//	            @Override
//	            protected void configure() {
//	                bindFactory(MyWebTargetFactory.class).to(WebTarget.class);
//	            }
//	        }
//	    }
//	}
    public static void main(String[] args) {
        try {
            new HTraceServiceApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(HTraceServiceConfiguration hTraceServiceConfiguration, Environment environment) throws Exception {
        environment.jersey().register(new HTraceServiceResource());
        environment.jersey().register(TracingRequestFilter.class);

    }
}
