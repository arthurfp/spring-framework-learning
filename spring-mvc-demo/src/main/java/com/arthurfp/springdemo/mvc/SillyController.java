package com.arthurfp.springdemo.mvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SillyController {
	
	// conflict --- same @RequestMapping that HelloWorldController
	// it will throw an error 
	// FIXED by adding a parent mapping
	@RequestMapping("/showForm")
	public String displayTheForm() {
		return "silly";
	}
	
}
