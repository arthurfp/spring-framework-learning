package com.arthurfp.aopdemo.aspect;

import java.util.List;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.arthurfp.aopdemo.Account;
import com.arthurfp.aopdemo.AroundWithLoggerDemoApp;

@Aspect
@Component
@Order(2) // sets the order where the Aspect is called
public class MyDemoLoggingAspect {
	
	private Logger myLogger = Logger.getLogger(getClass().getName());

	@Around("execution(* com.arthurfp.aopdemo.service.*.getFortune(..))")	
	public Object aroundGetFortune(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
		
		// print out method we are advising on
		String method = theProceedingJoinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @Around on method: " + method);
		
		// get begin timestamp
		long begin = System.currentTimeMillis();
		
		// now, let's execute the method
		Object result = null;
		
		try {
			
			result = theProceedingJoinPoint.proceed();
		
		} catch (Exception e) {
			// log the exception
			
			System.out.println(e.getMessage());
			//myLogger.warning(e.getMessage());
			
			// give user a custom message
			//result = "Major accident! But no worries, your private AOP helicopter is on the way!";
			
			// rethrow exception
			throw e;
		}
		
		// get end timestamp
		long end = System.currentTimeMillis();
		
		// compute duration and display it
		long duration = end - begin;
		System.out.println("\n====> Duration : " + duration / 1000 + " seconds"); // divide by 1000 to convert to seconds

		return result;
	}

	@After("execution(* com.arthurfp.aopdemo.dao.AccountDAO.findAccounts(..))")
	public void afterFindAccountAdvice(JoinPoint theJoinPoint) {

		// print out wich method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @After on method: " + method);
	}

	// add a new advice for @AfterThrowing on the findAccounts method

	@AfterThrowing(pointcut = "execution(* com.arthurfp.aopdemo.dao.AccountDAO.findAccounts(..))", throwing = "theExc")
	public void afterThrowingFindAccountAdvice(JoinPoint theJoinPoint, Throwable theExc) {

		// print out wich method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @AfterThrowing on method: " + method);

		// log the exception
		System.out.println("\n====>>> The exception is: " + theExc);
	}

	@AfterReturning(pointcut = "execution(* com.arthurfp.aopdemo.dao.AccountDAO.findAccounts(..))", returning = "result")
	public void afterReturningFindAccountAdvice(JoinPoint theJoinPoint, List<Account> result) {

		// print out which method we are advising on
		String method = theJoinPoint.getSignature().toShortString();
		System.out.println("\n====>>> Executing @AfterReturning on method: " + method);

		// print out the result of the method call
		System.out.println("\n====>>> result is: " + result);

		// let's post-process the data ... let's modify it :-)

		// convert the account names to uppercase
		convertAccountNamesToUpperCase(result);

		// print result after modify return
		System.out.println("\n====>>> result after modifying is: " + result);

		/*
		 * Other example
		 * 
		 * // modify "result" list: add, remove, update, etc ... if (!result.isEmpty())
		 * {
		 * 
		 * Account tempAccount = result.get(0);
		 * 
		 * tempAccount.setName("Daffy Duck");
		 * 
		 * }
		 */

	}

	private void convertAccountNamesToUpperCase(List<Account> result) {

		// loop through accounts

		for (Account tempAccount : result) {

			// get uppercase version of name
			String theUpperName = tempAccount.getName().toUpperCase();

			// update the name on the account
			tempAccount.setName(theUpperName);
		}
	}

	@Before("com.arthurfp.aopdemo.aspect.AopExpressions.forDaoPackageNoGetterSetter()")
	public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {

		// display the method signature
		MethodSignature methodSig = (MethodSignature) theJoinPoint.getSignature();

		System.out.println("Method: " + methodSig);

		// display the method arguments

		// get args
		Object[] args = theJoinPoint.getArgs();

		// loop through args
		for (Object tempArg : args) {

			System.out.println(tempArg.toString());

			if (tempArg instanceof Account) {

				// downcast and print Account specific stuff
				Account theAccount = (Account) tempArg;

				System.out.println("Account name: " + theAccount.getName());
				System.out.println("Account level: " + theAccount.getLevel());
			}
		}

	}

}
