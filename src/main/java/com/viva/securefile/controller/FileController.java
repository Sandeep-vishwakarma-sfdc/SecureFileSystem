package com.viva.securefile.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.viva.securefile.entity.File;
import com.viva.securefile.entity.FileRequests;
import com.viva.securefile.entity.User;
import com.viva.securefile.helper.FileWrapper;
import com.viva.securefile.helper.Utility;
import com.viva.securefile.repository.FileRepository;
import com.viva.securefile.repository.FileRequestRepository;
import com.viva.securefile.repository.UserRepository;
import com.viva.securefile.service.StorageService;

@Controller
public class FileController {
	
	@Autowired
	UserRepository userRepository;
	@Autowired
	FileRepository fileRepository;
	@Autowired
	FileRequestRepository fileRequestRepository;
	
	@Autowired
	StorageService storageService;

	@RequestMapping("/sendreq/{fileId}")
	public String allfiles(Model model,Principal principal,@Param("email") String email,@PathVariable("fileId") int fileId) {
		User user = userRepository.getUserByusername(principal.getName());
		List<File> files = fileRepository.getOtherUserFiles(email);
		List<FileRequests> requests = fileRequestRepository.getFileRequestByFiles(files);
		File file = fileRepository.getById(fileId);
		sendReq(user, file);
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
		return "redirect:/user/allfiles?email="+file.getOwner().getEmail()+"";
	}
	
	public void sendReq(User user,File file) {
		FileRequests fileRequest = new FileRequests();
		fileRequest.setStatus("Pending");
		fileRequest.setFileid(file);
		fileRequest.setrequesterid(user);
		List<FileRequests> filereqs = fileRequestRepository.getFileRequestByUser(user);
		boolean isExit = isExistFileRequest(fileRequest, filereqs);
		if(!isExit) {
			fileRequestRepository.save(fileRequest);
		}
	}
	
	public boolean isExistFileRequest(FileRequests fileRequestnew, List<FileRequests> fileRequests) {
		for (FileRequests fileRequest : fileRequests) {
			if (fileRequest.getFileid().getId() == (fileRequestnew.getFileid().getId())) {
				return true;
			}
		}
		return false;
	}
	
	@PostMapping("/upload")
	public String uploadFile(@RequestParam(value = "file") MultipartFile file,@RequestParam(value = "secretkey")String secretkey,Principal principal) {
		User user = userRepository.getUserByusername(principal.getName());
		System.out.println("RequestParam File "+file);
		
		String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
		storageService.uploadFile(file,fileName,user,secretkey);
		return "redirect:/user/myfiles";
	}
	
	@RequestMapping("/download/{filename}")
	public ResponseEntity downloadFile(@PathVariable("filename")String urlname) {
		String filename = urlname.split("-")[0];
		String inputkey = urlname.split("-")[1];
		File file = fileRepository.findByName(filename);
		byte[] data = storageService.downloadFile(filename);
		System.out.println("Secret Key "+file.getfilepassphrase());
		System.out.println("Is Downloable -------------> Key "+inputkey==file.getfilepassphrase());
		if(inputkey==file.getfilepassphrase()) {
		ByteArrayResource resource = new ByteArrayResource(data);
		HttpHeaders header = new HttpHeaders();
		header.add("Content-type", "application/octet-stream");
		header.add("Content-disposition", "attachment; filename=\"" + filename + "\"");
		System.out.println(resource);
		return ResponseEntity
                .ok()
                .headers(header)
                .contentLength(data.length)
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(resource);
		}else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("FILE NOT FOUND");
		}
	}
}
