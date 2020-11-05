package com.arthurfp.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arthurfp.aopdemo.dao.AccountDAO;
import com.arthurfp.aopdemo.dao.MembershipDAO;

public class MainDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
			new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get account bean from spring container
		AccountDAO theAccountDAO = context.getBean("accountDAO", AccountDAO.class);
		
		// get membership bean from spring container
		MembershipDAO theMembershipDAO = context.getBean("membershipDAO", MembershipDAO.class);
		
		// call the business method
		Account myAccount = new Account();
		theAccountDAO.addAccount(myAccount, true);
		
		// call it again
		System.out.println("\ncalling account method again!");
		theAccountDAO.addAccount(myAccount, false);
		
		theAccountDAO.doWork();
		
		theMembershipDAO.addMembership();
		
		theMembershipDAO.goToSleep();
		
		// close the context
		context.close();
	}

}
