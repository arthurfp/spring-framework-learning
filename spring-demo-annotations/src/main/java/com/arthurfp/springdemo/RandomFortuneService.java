package com.arthurfp.springdemo;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class RandomFortuneService implements FortuneService {

	// if you use annotation to set values to some variables (e.g., a, b, c, d)
	// the you will have to use @PostConstruct annotation to wait lifecycle to inject00
	// beans (those values) if you want to assign them to some other data
	/*
	 * private String[] data;
	 * 
	 * @PostConstruct public void setupMyData() {
	 * 
	 * data = new String[5];
	 * 
	 * data[0] = a; data[1] = b; data[2] = c; data[3] = d; data[4] = e;
	 * 
	 * }
	 * 
	 * // In Java 9+ you need to add this to pom to use @PostConstruct and @PreDestroy annotations
	 * // or implements InitializingBean, DisposableBean with methods afterPropertiesSet() and destroy()
	 *<dependency>
	 *   <groupId>javax.annotation</groupId>
	 *   <artifactId>javax.annotation-api</artifactId>
	 *   <version>1.3.2</version>
	 *</dependency>
	 */
	
	// create an array of strings
	private String[] data = {
			"Beware of the wolf in sheep's clothing",
			"Diligence is the mother of good luck",
			"The journey is reward"
	};
	
	// create a random number generator
	private Random myRandom = new Random();
	
	@Override
	public String getFortune() {
		// pick a random string from the array
		int index = myRandom.nextInt(data.length);
		
		return data[index];
	}

}
