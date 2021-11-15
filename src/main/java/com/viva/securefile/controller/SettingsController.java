package com.viva.securefile.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viva.securefile.entity.User;
import com.viva.securefile.repository.UserRepository;

@Controller
public class SettingsController {

	
	@Autowired
	UserRepository userRepository;
	
	@PostMapping("/do_userupdate")
	public String updateUser(@RequestParam(value = "name")String name,@RequestParam(value = "email")String email ,Principal principal,Model model) {
		User user = userRepository.getUserByusername(principal.getName());
		user.setName(name);
		user.setEmail(email);
		userRepository.save(user);
		model.addAttribute("user", user);
		model.addAttribute("fchar", user.getName().charAt(0));
		return "/user/settings";
	}
	
}
