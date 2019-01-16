package br.com.b2w.swapi.spring.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "br.com.b2w")
@PropertySource("classpath:config.properties")
public class WebConfig implements WebMvcConfigurer {

	@Autowired
	private Environment environment;

	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) { }

	@Override
	public void addViewControllers(ViewControllerRegistry registry) { }

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		String origins = environment.getProperty("enterprise.origins");
		registry.addMapping("/**").allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
				.allowedOrigins(origins.split(","));
	}
}
