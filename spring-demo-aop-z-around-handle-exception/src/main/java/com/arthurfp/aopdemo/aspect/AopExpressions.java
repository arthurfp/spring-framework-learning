package com.arthurfp.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

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
public class AopExpressions {
	
	// it is possible to declare Pointcuts to reuse them later. Example:
	
	@Pointcut("execution(* com.arthurfp.aopdemo.dao.*.*(..))")
	public void forDaoPackage() {} // you declare a method, but you can "use it as a variable"
	
	// create Pointcut for getter methods
	@Pointcut("execution (* com.arthurfp.aopdemo.dao.*.get*(..))")
	public void getter () {}
	
	// create Pointcut for setter methods
	@Pointcut("execution (* com.arthurfp.aopdemo.dao.*.set*(..))")
	public void setter () {}
	
	// it is possible to combine Pointcuts using logical operators (like an "if" condition)
		
	//combine example: include package ... exclude getter/setter
	@Pointcut("forDaoPackage() && !(getter() || setter())")
	public void forDaoPackageNoGetterSetter() {}
	
	// PS: declared as public to be accessed by other classes

}
