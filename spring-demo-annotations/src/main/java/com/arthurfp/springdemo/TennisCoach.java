package com.arthurfp.springdemo;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
/* @Scope("prototype") */
public class TennisCoach implements Coach, InitializingBean, DisposableBean {

	// Field injection
	@Autowired
	@Qualifier("randomFortuneService")
	private FortuneService fortuneService;

	// inject properties using annotation
	/*
	 * @Value("${foo.email}") private String email;
	 * 
	 * @Value("${foo.team}") private String team;
	 */

	// Auto wiring on constructor
	/*
	 * @Autowired annotation on constructor is no longer necessary if the target
	 * bean only defines one constructor to begin with. However, if several
	 * constructors are available, at least one must be annotated to teach the
	 * container which one to use. - Spring 4.3
	 * 
	 * Still so, it makes it more readable and scalable
	 */

	/*
	 * @Autowired public TennisCoach(FortuneService fortuneService) {
	 * this.fortuneService = fortuneService; }
	 */

	// default constructor
	public TennisCoach() {
		System.out.println(">> TennisCoach: inside default constructor");
	}

	/*
	 * // In Java 9+ you need to add this to pom to use @PostConstruct
	 * and @PreDestroy annotations // or implements InitializingBean, DisposableBean
	 * with methods afterPropertiesSet() and destroy() <dependency>
	 * <groupId>javax.annotation</groupId>
	 * <artifactId>javax.annotation-api</artifactId> <version>1.3.2</version>
	 * </dependency>
	 */

	// define my init method
	/* @PostConstruct */
	public void doMyStartupStuff() {
		System.out.println(">> TennisCoach: inside of doMyStartupStuff");
	}

	// define my destroy method
	/* @PreDestroy */
	public void doMyCleanupStuff() {
		System.out.println(">> TennisCoach: inside of doMyCleanupStuff");
	}

	// lifecycle alternative in Java 9+ for @PostConstruct
	@Override
	public void afterPropertiesSet() throws Exception {
		doMyStartupStuff();
	}

	// lifecycle alternative in Java 9+ for @PreConstruct
	@Override
	public void destroy() throws Exception {
		doMyCleanupStuff();
	}

	@Override
	public String getDailyWorkout() {
		return "Practice your backhand volley";
	}

	@Override
	public String getDailyFortune() {
		return fortuneService.getFortune();
	}

	// define a setter method
	/*
	 * @Autowired public void setFortuneService(FortuneService fortuneService) {
	 * System.out.println(">> TennisCoach: inside setFortuneService() method");
	 * this.fortuneService = fortuneService; }
	 */

	// method injection
	/*
	 * @Autowired public void doSomeCrazyStuff(FortuneService fortuneService) {
	 * System.out.println(">> TennisCoach: inside doSomeCrazyStuff() method");
	 * this.fortuneService = fortuneService; }
	 */
}
