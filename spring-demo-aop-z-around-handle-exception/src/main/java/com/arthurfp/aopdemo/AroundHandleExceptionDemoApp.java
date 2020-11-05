package com.arthurfp.aopdemo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.arthurfp.aopdemo.service.TrafficFortuneService;

public class AroundHandleExceptionDemoApp {

	public static void main(String[] args) {

		// read spring config java class
		AnnotationConfigApplicationContext context =
			new AnnotationConfigApplicationContext(DemoConfig.class);
		
		// get the bean from spring container
		TrafficFortuneService theFortuneService = context.getBean("trafficFortuneService", TrafficFortuneService.class);
		
		System.out.println("\nMain Program: AroundDemoApp");
		
		System.out.println("Calling getFortune");
		
		// add a boolean flag to simulate exceptions
		boolean tripWire = true;
		String data = theFortuneService.getFortune(tripWire);
		
		System.out.println("\nmy fortune is: " + data);
		
		System.out.println("Finished");
		
		// close the context
		context.close();
	}

}
