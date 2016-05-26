package ee.ttu.olivereivak.webbasedapps.repair.guice.module;

import com.google.inject.Provides;

import ee.ttu.olivereivak.webbasedapps.repair.config.ApplicationConfig;
import ee.ttu.olivereivak.webbasedapps.repair.config.ApplicationWebConfig;
import ee.ttu.olivereivak.webbasedapps.repair.resource.filter.ApplicationPersistFilter;

public class RestModule extends org.gwizard.rest.RestModule {

    @Override
    protected void configureServlets() {
        super.configureServlets();
        filter("/*").through(ApplicationPersistFilter.class);
    }

    @Provides
    public ApplicationWebConfig applicationWebConfig(ApplicationConfig cfg) {
        return cfg.getWeb();
    }

}
