import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class HTraceServiceApplication extends Application<HTraceServiceConfiguration> {
    public static void main(String[] args) {
        try {
            new HTraceServiceApplication().run(args);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(HTraceServiceConfiguration hTraceServiceConfiguration, Environment environment) throws Exception {

    }
}
