package com.viva.securefile.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.viva.securefile.entity.User;

@Controller
public class HomeController {

	@GetMapping("/")
	public String home() {
		return "home";
	}
	
	@GetMapping("/signup")
	public String signup(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
	@GetMapping("/about")
	public String about(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "about";
	}
	
	@GetMapping("/signin")
	public String signin(Model model) {
		return "login";
	}
	
	@GetMapping("/do_register")
	public String register(Model model) {
		User user = new User();
		model.addAttribute("user", user);
		return "register";
	}
	
	
	
}
