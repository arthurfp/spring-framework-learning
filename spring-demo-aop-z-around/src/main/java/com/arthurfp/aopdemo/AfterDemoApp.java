package com.arthurfp.aopdemo;

import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arthurfp.aopdemo.dao.AccountDAO;
import com.arthurfp.aopdemo.dao.MembershipDAO;

public class AfterDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
			new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get account bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// call method to find the accounts
		List<Account> theAccounts = null;
		
		try {
			// add a boolean flag to simulate exceptions
			boolean tripWire = true;
			theAccounts = theAccountDAO.findAccounts(tripWire);
			
			// run without exception to simulate success case with @After
			
			theAccounts = theAccountDAO.findAccounts(false);
		} catch (Exception exc) {
			System.out.println("\n\nMain Program ... caught exception: " + exc);
		}
		
		// display the accounts
		System.out.println("\n\nMain Program: AfterDemoApp");
		System.out.println("-----");
		
		System.out.println(theAccounts);
		
		System.out.println("\n");
		
		
		// close the context
		context.close();
	}

}
