package com.arthurfp.springdemo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
// Scan project --- used when you don't define beans 
/* @ComponentScan("com.arthurfp.springdemo") */
@PropertySource("classpath:sport.properties")
public class SportConfig {	
	
	// define bean for our sad fortune service
	@Bean
	public FortuneService sadFortuneService() {
		return new SadFortuneService();
	}

	// define bean for our swim coach AND inject dependency
	@Bean
	public SwimCoach swimCoach() {
		return new SwimCoach(sadFortuneService());
	}
}
