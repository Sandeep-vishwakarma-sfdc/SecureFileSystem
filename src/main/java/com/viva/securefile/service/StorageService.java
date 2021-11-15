package com.viva.securefile.service;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;
import com.viva.securefile.entity.User;
import com.viva.securefile.repository.FileRepository;
import com.viva.securefile.repository.FileRequestRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

@Service
public class StorageService {

    @Value("${application.bucket.name}")
    private String bucketName;

    @Autowired
    private AmazonS3 s3Client;
    
    @Autowired
	FileRepository fileRepository;

    public boolean uploadFile(MultipartFile file,String fileName,User user,String secretkey) {
    	try {			
    		File fileObj = convertMultiPartFileToFile(file);
    		s3Client.putObject(new PutObjectRequest(bucketName, fileName, fileObj));
    		com.viva.securefile.entity.File f = new com.viva.securefile.entity.File();
    		long fileSizeInBytes = fileObj.length();
			long fileSizeInKB = fileSizeInBytes / 1024;
			System.out.println("------------ File Uploaded  ----------------");
			f.setFilename(fileName);
			f.setfilepassphrase(secretkey);
			f.setOwner(user);
			f.setSize(String.valueOf(fileSizeInKB)+" KB");
			f.setType(file.getContentType());
			fileRepository.save(f);
    		fileObj.delete();
    		return true;
		} catch (Exception e) {
			System.out.println("Error while file uploading "+e.getMessage());
		}
    	return false;
    }


    public byte[] downloadFile(String fileName) {
        S3Object s3Object = s3Client.getObject(bucketName, fileName);
        S3ObjectInputStream inputStream = s3Object.getObjectContent();
        try {
            byte[] content = IOUtils.toByteArray(inputStream);
            return content;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public String deleteFile(String fileName) {
        s3Client.deleteObject(bucketName, fileName);
        return fileName + " removed ...";
    }


    private File convertMultiPartFileToFile(MultipartFile file) {
        File convertedFile = new File(file.getOriginalFilename());
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            
        }
        return convertedFile;
    }
}