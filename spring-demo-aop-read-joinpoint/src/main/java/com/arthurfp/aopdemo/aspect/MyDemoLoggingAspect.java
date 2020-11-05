package com.arthurfp.aopdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arthurfp.aopdemo.Account;

@Aspect
@Component
@Order(2) // sets the order where the Aspect is called
public class MyDemoLoggingAspect {
	
	@Before("com.arthurfp.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
		
		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();
		
		System.out.println("Method: " + methodSig);
		
		// display the method arguments
		
		// get args
		Object[] args = theJoinPoint.getArgs();
		
		// loop through args
		for(Object tempArg : args) {
			
			System.out.println(tempArg);
			
			if (tempArg instanceof Account) {
				
				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;
				
				System.out.println("Account name: " + theAccount.getName());
				System.out.println("Account level: " + theAccount.getLevel());
			}
		}
		
	}
	
	
}
