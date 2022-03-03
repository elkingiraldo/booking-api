package co.com.elkin.apps.bookingapi.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.Locale;

/**
 * Configuration for internationalization
 * 
 * @author egiraldo
 *
 */
@Configuration
public class I18nConfig {

	@Bean
	public LocaleResolver localeResolver() {
		var localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(Locale.ENGLISH);
		return localeResolver;
	}

	@Bean
	public ReloadableResourceBundleMessageSource bundleMessageSource() {
		var messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:locale/messages");
		messageSource.setCacheMillis(3600);

		return messageSource;
	}
}
