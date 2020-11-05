package com.arthurfp.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3) // sets the order where the Aspect is called
public class MyApiAnalyticsAspect {
	
	@Before("com.arthurfp.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void performApiAnalytics() {
		
		System.out.println("\n====>>> Performing API Analytics");
	}	
	
}
