package br.com.b2w.swapi.spring.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.Environment;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.data.mongodb.config.AbstractMongoConfiguration;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.mongodb.MongoClient;

@Configuration
@EnableMongoRepositories(basePackages = "br.com.b2w")
@PropertySource({ "classpath:config.properties" })
public class MongoConfig extends AbstractMongoConfiguration {

	@Autowired
	private Environment environment;
	
	private final List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();

	@Override
	protected String getDatabaseName() {
		return "test";
	}

	@Override
	public MongoClient mongoClient() {
		String host = environment.getRequiredProperty("mongo.host");
		Integer port = Integer.parseInt(environment.getRequiredProperty("mongo.port"));
		return new MongoClient(host, port);
	}

	@Override
	public String getMappingBasePackage() {
		return "br.com.b2w.swapi.planet.model";
	}

	@Override
	public MongoCustomConversions customConversions() {
		return new MongoCustomConversions(converters);
	}

	@Bean
	public GridFsTemplate gridFsTemplate() throws Exception {
		return new GridFsTemplate(mongoDbFactory(), mappingMongoConverter());
	}

	@Bean
	MongoTransactionManager transactionManager(MongoDbFactory dbFactory) {
		return new MongoTransactionManager(dbFactory);
	}
}
