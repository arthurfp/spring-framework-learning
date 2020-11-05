package com.arthurfp.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1) // sets the order where the Aspect is called
public class MyCloudLogAsyncAspect {
	
	@Before("com.arthurfp.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void logToCloundAsync() {
		
		System.out.println("\n====>>> Logging to Cloud in async fashion");
	}
	
	
}
