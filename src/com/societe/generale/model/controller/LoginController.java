package com.societe.generale.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

	@RequestMapping(value="/", method = RequestMethod.GET)
	public String getM() {		
		return post();
	}
	
	@RequestMapping(value="societegenerale.search", method = RequestMethod.GET)
	public String get() {
		return "memberDetail";
		
	}
	
	@RequestMapping(value="societegenerale.search", method = RequestMethod.POST)
	public String post() {
		return "memberDetail";
		
	}
}
