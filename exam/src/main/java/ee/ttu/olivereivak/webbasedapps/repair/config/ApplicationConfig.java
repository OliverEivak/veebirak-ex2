package ee.ttu.olivereivak.webbasedapps.repair.config;

import org.gwizard.hibernate.DatabaseConfig;
import org.gwizard.logging.LoggingConfig;

import lombok.Data;

/**
 * Your configuration class can be any POJO, and there's nothing special about
 * the property names - although we recommend sticking to these standards.
 * Your Guice module will need to @Provide the relevant config classes for
 * logging, web, database, etc, and this is where they should come from.
 */
@Data
public class ApplicationConfig {

	private int shutdownPort;

	/** Some standard bits of configuration */
	private LoggingConfig logging = new LoggingConfig();
	private ApplicationWebConfig web = new ApplicationWebConfig();
	private DatabaseConfig database = new DatabaseConfig();
}
