package br.com.b2w.swapi.spring.config;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

@Component
public class Messages {

	@Autowired
	private Environment environment;

	@Autowired
	private MessageSource messageSource;

	private MessageSourceAccessor accessor;

	@PostConstruct
	private void init() {
		String language = environment.getRequiredProperty("enterprise.language");
		String country = environment.getRequiredProperty("enterprise.country");
		Locale locale = new Locale(language, country);
		accessor = new MessageSourceAccessor(messageSource, locale);
	}

	public String get(String code) {
		return accessor.getMessage(code);
	}

	public String get(String code, String... args) {
		return accessor.getMessage(code, args);
	}

	public String get(Errors errors) {
		String messages = "";
		if (errors.hasErrors()) {
			String msgErr = "";
			for (ObjectError objErr : errors.getAllErrors()) {
				msgErr = accessor.getMessage(objErr.getCode());
				messages += messages.contains(msgErr) ? "" : ("<li>" + msgErr + "</li>");
			}
		}
		return messages;
	}

}