package com.arthurfp.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/*
 * Spring AOP Pointcut's ("?" is optional):
 * execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern)
 *         throws-pattern?)
 *         
 *  simplifying -> execution ("public"? "return-type/void" "class-name"? "method-name(parameters) "throws"?)
 *  
 *  it can uses wildcards(*):
 *  Example 1 -> execution(public void add*())
 *  Example 2 -> execution(* add*())
 *  
 *  it can also use (..) for parameters:
 *  - * is used for one parameter of any typy
 *  - .. is used to 0 or any parameters of any type
 *  
 *  it is possible to combine package, method, wildcards, ".." to filter as you wish
 * 
 * Important note: Spring AOP uses run-time Weaving
 * 
 */

@Aspect
@Component
public class MyDemoLoggingAspect {
	
	// it is possible to declare Pointcuts to reuse them later. Example:
	
	@Pointcut("execution(* com.arthurfp.aopdemo.dao.*.*(..))")
	private void forDaoPackage() {} // you declare a method, but you can "use it as a variable"
	
	@Before("forDaoPackage()")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n====>>> Executing @Before advice on addAccount()");
	}
	
	// reusing
	@Before("forDaoPackage()")
	public void performApiAnalytics() {
		
		System.out.println("\n====>>> Performing API Analytics");
	}
	
}
