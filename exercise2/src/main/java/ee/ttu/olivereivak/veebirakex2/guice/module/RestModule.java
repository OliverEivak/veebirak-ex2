package ee.ttu.olivereivak.veebirakex2.guice.module;

import ee.ttu.olivereivak.veebirakex2.config.ApplicationConfig;
import ee.ttu.olivereivak.veebirakex2.resource.filter.ApplicationPersistFilter;
import ee.ttu.olivereivak.veebirakex2.config.ApplicationWebConfig;
import com.google.inject.Provides;

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
