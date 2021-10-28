package com.viva.securefile.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.viva.securefile.entity.User;
import com.viva.securefile.helper.Message;
import com.viva.securefile.repository.UserRepository;

@Controller
public class HomeController {
	@Autowired
	UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
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
	
	@PostMapping("/do_register")
	public String register(@ModelAttribute("user") User user,HttpSession session,@RequestParam(value = "aggrement",defaultValue = "false") boolean agreement) {
		try {
			System.out.println("user from page "+user);
			if(!agreement) {
				throw new Exception("Terms and Conditions is not accepted");
			}
			user.setRole("ROLE_USER");
			user.setActive("true");
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			session.setAttribute("alertMessage", new Message("Successfully Registered", "alert-success"));
			this.userRepository.save(user);
			return "register";
		}catch(Exception e) {
			
			session.setAttribute("alertMessage", new Message("something went wrong", "alert-danger"));
			return "register";
		}
		
	}
	
	
	
}
