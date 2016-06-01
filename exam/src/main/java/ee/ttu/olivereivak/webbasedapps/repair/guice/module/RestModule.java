package ee.ttu.olivereivak.webbasedapps.repair.guice.module;

import javax.inject.Singleton;

import org.gwizard.rest.JaxrsModule;
import org.gwizard.rest.ObjectMapperContextResolver;
import org.gwizard.web.WebModule;
import org.jboss.resteasy.plugins.guice.GuiceResteasyBootstrapServletContextListener;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;

import com.google.inject.Provides;

import ee.ttu.olivereivak.webbasedapps.repair.config.ApplicationConfig;
import ee.ttu.olivereivak.webbasedapps.repair.config.ApplicationWebConfig;
import ee.ttu.olivereivak.webbasedapps.repair.resource.filter.ApplicationPersistFilter;
import ee.ttu.olivereivak.webbasedapps.repair.resource.servlet.ServiceRequestServlet;

public class RestModule extends org.gwizard.rest.RestModule {

    @Override
    protected void configureServlets() {
        //super.configureServlets();
        install(new WebModule());
        install(new JaxrsModule());

        // Binding this will cause it to be picked up by gwizard-web
        bind(GuiceResteasyBootstrapServletContextListener.class);

        // Make sure RESTEasy picks this up so we get our ObjectMapper from guice
        bind(ObjectMapperContextResolver.class);

        bind(HttpServletDispatcher.class).in(Singleton.class);

        // This needs to be before serve("/*") (can't call super, have to copy-paste)
        serve("/serviceRequestsServlet").with(ServiceRequestServlet.class);

        serve("/*").with(HttpServletDispatcher.class);

        filter("/*").through(ApplicationPersistFilter.class);
    }

    @Provides
    public ApplicationWebConfig applicationWebConfig(ApplicationConfig cfg) {
        return cfg.getWeb();
    }

}
