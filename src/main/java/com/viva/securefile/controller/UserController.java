package com.viva.securefile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/user")
@Controller
public class UserController {

	@RequestMapping("/dashboard")
	public String dashboard() {
		return "user/dashboard";
	}
	
}
