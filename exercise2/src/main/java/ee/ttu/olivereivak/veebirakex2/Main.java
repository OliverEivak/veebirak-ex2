package ee.ttu.olivereivak.veebirakex2;

import java.io.File;

import org.gwizard.config.ConfigModule;
import org.gwizard.healthchecks.HealthChecksModule;
import org.gwizard.hibernate.HibernateModule;
import org.gwizard.logging.LoggingModule;
import org.gwizard.metrics.MetricsModule;
import org.gwizard.services.Run;

import ee.ttu.olivereivak.veebirakex2.config.ApplicationConfig;
import ee.ttu.olivereivak.veebirakex2.guice.GuiceInjector;
import ee.ttu.olivereivak.veebirakex2.guice.module.ApplicationModule;
import ee.ttu.olivereivak.veebirakex2.guice.module.RestModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

/**
 * Set up the injector and start all services
 */
public class Main {

	public static String[] arguments;

	public static void main(String[] args) throws Exception {
		if (args.length < 1) {
			System.err.println("First argument needs to be a yaml config file, doofus");
			return;
		}

		arguments = args;

		Injector injector = Guice.createInjector(
				new ApplicationModule(),
				new ConfigModule(new File(args[0]), ApplicationConfig.class),
				new LoggingModule(),
				new RestModule(),
				new HibernateModule(),
				new MetricsModule(),
				new HealthChecksModule());

		injector.getInstance(Run.class).start();
		GuiceInjector.setInjector(injector);
	}

}
