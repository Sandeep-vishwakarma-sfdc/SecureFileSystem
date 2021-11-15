package com.viva.securefile.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.viva.securefile.entity.File;
import com.viva.securefile.entity.FileRequests;
import com.viva.securefile.entity.User;
import com.viva.securefile.helper.FileWrapper;
import com.viva.securefile.helper.Utility;
import com.viva.securefile.repository.FileRepository;
import com.viva.securefile.repository.FileRequestRepository;
import com.viva.securefile.repository.UserRepository;

@RequestMapping("/user")
@Controller
public class UserController {

	@Autowired
	UserRepository userRepository;
	@Autowired
	FileRepository fileRepository;
	@Autowired
	FileRequestRepository fileRequestRepository; 
	
	@RequestMapping("/dashboard")
	public String dashboard(Model model,Principal principal) {
		User user = userRepository.getUserByusername(principal.getName());
		char fchar = user.getName().charAt(0);
		model.addAttribute("user",user);
		model.addAttribute("fchar",fchar);
		return "user/dashboard";
	}
	
	@RequestMapping("/allfiles")
	public String allfiles(Model model,Principal principal,@Param("email") String email) {
		User user = userRepository.getUserByusername(principal.getName());
		List<File> files = fileRepository.getOtherUserFiles(email);
		System.out.println("Other Users file "+files);
		List<FileRequests> requests = fileRequestRepository.getFileRequestByFiles(files);
		char fchar = user.getName().charAt(0);
		List<FileWrapper> listwrapper = new ArrayList<FileWrapper>();
		for(File f:files) {
			FileWrapper fileWrapper = new FileWrapper(f, Utility.isRequested(f, requests));
			listwrapper.add(fileWrapper);
		}
		model.addAttribute("user",user);
		model.addAttribute("fchar",fchar);
		model.addAttribute("files",files);
		model.addAttribute("wrapper",listwrapper);
		System.out.println("Wrapper list "+listwrapper);
		return "user/allfiles";
	}
	
	@RequestMapping("/myfiles")
	public String myfiles(Model model,Principal principal) {
		User user = userRepository.getUserByusername(principal.getName());
		List<File> files = fileRepository.getLoggedinUserFiles(user);
		char fchar = user.getName().charAt(0);
		model.addAttribute("user",user);
		model.addAttribute("fchar",fchar);
		model.addAttribute("files", files);
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


