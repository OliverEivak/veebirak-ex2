package ee.ttu.olivereivak.webbasedapps.repair.guice.module;

import org.gwizard.hibernate.DatabaseConfig;
import org.gwizard.logging.LoggingConfig;
import org.gwizard.web.WebServer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.Service;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;

import lombok.extern.slf4j.Slf4j;

import ee.ttu.olivereivak.webbasedapps.repair.config.ApplicationConfig;
import ee.ttu.olivereivak.webbasedapps.repair.guice.provider.ObjectMapperProvider;
import ee.ttu.olivereivak.webbasedapps.repair.resource.BaseResource;
import ee.ttu.olivereivak.webbasedapps.repair.util.ReflectionUtils;
import ee.ttu.olivereivak.webbasedapps.repair.web.ApplicationWebServer;

/**
 * <p>Among the duties of your application module(s), you must explicitly bind every JAXRS resource class.
 * Consider using Reflections to do this automatically.</p>
 *
 * <p>We must provide bindings for the LoggingConfig, WebConfig, and DatabaseConfig to use the
 * logging, rest, and hibernate modules.</p>
 */
@Slf4j
public class ApplicationModule extends AbstractModule {
	@Override
	protected void configure() {
		bindResources();
		bindServices();

		bind(ObjectMapper.class).toProvider(ObjectMapperProvider.class);

		bind(WebServer.class).to(ApplicationWebServer.class);
	}

	private void bindServices() {
		for (Class service : ReflectionUtils.getClasses("ee.ttu.olivereivak.webbasedapps.repair.services", Service.class)) {
			log.debug("Binding service " + service.getSimpleName());
			bind(service).asEagerSingleton();
		}
	}

	private void bindResources() {
		for (Class resource : ReflectionUtils.getClasses("ee.ttu.olivereivak.webbasedapps.repair.resource", BaseResource.class)) {
			log.debug("Binding resource " + resource.getSimpleName());
			bind(resource);
		}
	}

	@Provides
	public LoggingConfig loggingConfig(ApplicationConfig cfg) {
		return cfg.getLogging();
	}

	@Provides
	public DatabaseConfig databaseConfig(ApplicationConfig cfg) {
		return cfg.getDatabase();
	}
}
