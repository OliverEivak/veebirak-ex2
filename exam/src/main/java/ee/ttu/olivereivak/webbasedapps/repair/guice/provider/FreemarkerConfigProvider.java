package ee.ttu.olivereivak.webbasedapps.repair.guice.provider;

import java.io.File;
import java.io.IOException;

import javax.inject.Singleton;

import com.google.inject.Provider;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Singleton
public class FreemarkerConfigProvider implements Provider<Configuration> {

    private Configuration configuration;

    @Override
    public Configuration get() {
        if (configuration == null) {
            configuration = getConfiguration();
        }
        return configuration;
    }

    private Configuration getConfiguration() {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_24);
        try {
            configuration.setDirectoryForTemplateLoading(new File("data/template"));
        } catch (IOException e) {
            log.error("Failed to set directory for template loading", e);
        }
        configuration.setDefaultEncoding("UTF-8");
        configuration.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
        configuration.setLogTemplateExceptions(false);
        return configuration;
    }
}
