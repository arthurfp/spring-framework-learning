package com.arthurfp.springdemo.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.sun.istack.logging.Logger;

@Aspect
@Component
public class CRMLoggingAspect {

	// setup logger
	private Logger myLogger = Logger.getLogger(getClass());
	
	// setup pointcut declarations
	@Pointcut("execution(* com.arthurfp.springdemo.controller.*.*(..))") // any class - any method - any number of arguments
	private void forControllerPackage() {}
	
	// do the same for service and dao
	@Pointcut("execution(* com.arthurfp.springdemo.service.*.*(..))")
	private void forServicePackage() {}
	
	@Pointcut("execution(* com.arthurfp.springdemo.dao.*.*(..))")
	private void forDaoPackage() {}
	
	@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
	private void forAppFlow() {}
	
	// add @Before advice
	@Before("forAppFlow()")
	public void before(JoinPoint theJoinPoint) {
		
		// display method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		
		myLogger.info("====> in @Before: calling method: " + theMethod);
		/* System.out.println("====> in @Before: calling method: " + theMethod); */
		
		// display the arguments to the method
		
		// get the arguments
		Object[] args = theJoinPoint.getArgs();
		
		// loop thru and display args
		for (Object tempArg : args) {
			myLogger.info("====> argument: " + tempArg);
			/* System.out.println("====> argument: " + tempArg); */
		}
		
	}
	
	
	// add @AfterReturning advice
	@AfterReturning(
			pointcut="forAppFlow()",
			returning="theResult"
			)
	public void afterReturning(JoinPoint theJoinPoint, Object theResult) {
		
		// display method we are returning from
String theMethod = theJoinPoint.getSignature().toShortString();
		
		myLogger.info("====> in @AfterReturning: from method: " + theMethod);
		/* System.out.println("====> in @AfterReturning: from method: " + theMethod); */
		
		// display data returned
		myLogger.info("====> result: " + theResult);
		/* System.out.println("====> result: " + theResult); */ 
	}
}
