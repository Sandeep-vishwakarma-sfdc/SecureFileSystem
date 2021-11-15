package com.viva.securefile.helper;

import java.util.List;

import com.viva.securefile.entity.File;
import com.viva.securefile.entity.FileRequests;

public class Utility {

	public static String isRequested(File file, List<FileRequests> fileRequests) {
		for (FileRequests fileRequest : fileRequests) {
			if (fileRequest.getFileid().getId() == file.getId()) {
				return fileRequest.getStatus();
			}
		}
		return "Request";
	}

}
