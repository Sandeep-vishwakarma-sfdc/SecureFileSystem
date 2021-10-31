package com.viva.securefile.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viva.securefile.entity.User;
import com.viva.securefile.repository.UserRepository;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model,Principal principal) {
		User user = userRepository.getUserByusername(principal.getName());
		char fchar = user.getName().charAt(0);
		model.addAttribute("user",user);
		model.addAttribute("fchar",fchar);
		return "user/dashboard";
	}
	
	@RequestMapping("/allfiles")
	public String allfiles(Model model,Principal principal) {
		User user = userRepository.getUserByusername(principal.getName());
		char fchar = user.getName().charAt(0);
		model.addAttribute("user",user);
		model.addAttribute("fchar",fchar);
		return "user/allfiles";
	}
	
	@RequestMapping("/myfiles")
	public String myfiles(Model model,Principal principal) {
		User user = userRepository.getUserByusername(principal.getName());
		char fchar = user.getName().charAt(0);
		model.addAttribute("user",user);
		model.addAttribute("fchar",fchar);
		return "user/myfiles";
	}
	
	@RequestMapping("/settings")
	public String settings(Model model,Principal principal) {
		User user = userRepository.getUserByusername(principal.getName());
		char fchar = user.getName().charAt(0);
		model.addAttribute("user",user);
		model.addAttribute("fchar",fchar);
		return "user/settings";
	}
	
	
}
