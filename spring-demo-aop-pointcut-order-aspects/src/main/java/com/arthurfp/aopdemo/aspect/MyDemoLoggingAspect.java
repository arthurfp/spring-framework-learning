package com.arthurfp.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(2) // sets the order where the Aspect is called
public class MyDemoLoggingAspect {
	
	@Before("com.arthurfp.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n====>>> Executing @Before advice on addAccount()");
	}
	
	
}
