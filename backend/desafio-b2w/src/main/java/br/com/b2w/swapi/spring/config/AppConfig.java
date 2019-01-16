package br.com.b2w.swapi.spring.config;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import com.swapi.sw.StarWars;
import com.swapi.sw.StarWarsApi;

@Configuration
@PropertySource({ "classpath:config.properties" })
@EnableTransactionManagement
@ComponentScan(basePackages = "br.com.b2w")
public class AppConfig {

	@Autowired
	private Environment environment;

	@Bean
	public LocaleResolver localeResolver() {
		String language = environment.getRequiredProperty("enterprise.language");
		String country = environment.getRequiredProperty("enterprise.country");
		Locale locale = new Locale(language, country);
		SessionLocaleResolver localeResolver = new SessionLocaleResolver();
		localeResolver.setDefaultLocale(locale);
		return localeResolver;
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("messages");
		messageSource.setUseCodeAsDefaultMessage(true);
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}
	
	@Bean
	public StarWars starWars() {
		return StarWarsApi.getApi();
	}
}
