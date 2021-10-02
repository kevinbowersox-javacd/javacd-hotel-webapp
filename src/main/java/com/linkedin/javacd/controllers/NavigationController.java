package com.linkedin.javacd.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flagsmith.FlagsmithClient;

@Controller
@RequestMapping(path = { "/", "/index.html" })
public class NavigationController {

	private FlagsmithClient flagsmith;
	
	@Autowired
	public NavigationController(FlagsmithClient flagsmith) {
		this.flagsmith = flagsmith;
	}
	

	@GetMapping(produces = "text/html")
	public String home() {
		
		boolean featureEnabled = flagsmith.hasFeatureFlag("booking_service");
		
		String navigation = featureEnabled == true ? "bookings.html":"rooms.html";
		
		return "redirect:" + navigation;
	}

}
