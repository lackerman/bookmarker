package me.a6n.bookmarker;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class App extends Application<AppConfig> {
    public static final String APP_NAME = "bookmarker";

    public static void main(final String[] args) throws Exception {
        new App().run(args);
    }

    @Override
    public String getName() {
        return APP_NAME;
    }

    @Override
    public void initialize(final Bootstrap<AppConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(
                new SubstitutingSourceProvider(
                        new ResourceConfigurationSourceProvider(),
                        new EnvironmentVariableSubstitutor()));
    }

    @Override
    public void run(final AppConfig configuration, final Environment environment) {
        configuration.getBookmarks().getTags().forEach(System.out::println);
        var di = DaggerAppDI.builder().build();
        environment.jersey().register(di.bookmarkResource());
    }
}
