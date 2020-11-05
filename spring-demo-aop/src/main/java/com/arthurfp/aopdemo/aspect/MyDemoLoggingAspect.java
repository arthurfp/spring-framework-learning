package com.arthurfp.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
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

	// this is where we add all of our related advices for logging
	
	// let's start with an @Before advice
	
	@Before("execution(* com.arthurfp.aopdemo.dao.*.*(com.arthurfp.*.*, ..))")
	public void beforeAddAccountAdvice() {
		
		System.out.println("\n====>>> Executing @Before advice on addAccount()");
	}	
}