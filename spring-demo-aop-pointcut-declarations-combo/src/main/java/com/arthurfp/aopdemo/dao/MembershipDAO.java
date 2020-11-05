package com.arthurfp.aopdemo.dao;

import org.springframework.stereotype.Component;

@Component
public class MembershipDAO {
	
	public void addMembership() {
		
		System.out.println(getClass() + ": DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT");
	}
	
	public void goToSleep() {
		
		System.out.println(getClass() + ": I'm going to sleep now...");
	}
}
