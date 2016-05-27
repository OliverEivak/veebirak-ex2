package ee.ttu.olivereivak.webbasedapps.repair.test;

import org.gwizard.hibernate.HibernateModule;
import org.gwizard.logging.LoggingModule;
import org.junit.Before;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.util.Modules;

import lombok.extern.slf4j.Slf4j;

import ee.ttu.olivereivak.webbasedapps.repair.guice.GuiceInjector;
import ee.ttu.olivereivak.webbasedapps.repair.guice.module.ApplicationModule;
import ee.ttu.olivereivak.webbasedapps.repair.guice.module.EmptyModule;
import ee.ttu.olivereivak.webbasedapps.repair.guice.module.TestModule;

/**
 * Some common behavior for all tests. Sets up an injector suitable for executing JAXRS resources
 * directly, exactly as the web container would.
 */
@Slf4j
public class TestBase {

	protected Injector injector;

	/** */
	@Before
	public void initializeTestBase() {
		injector = Guice.createInjector(
				Modules.override(
					new LoggingModule(),
					new HibernateModule(),
					new ApplicationModule(),
					new TestModule())
						.with(overrideModule()));

		GuiceInjector.setInjector(injector);
	}

	/** Override this method in a test class if you want special guice behavior */
	protected Module overrideModule() {
		return new EmptyModule();
	}

	/** Convenience method */
	protected <T> T instance(Class<T> clazz) {
		return injector.getInstance(clazz);
	}
}
