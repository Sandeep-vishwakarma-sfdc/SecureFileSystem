package com.viva.securefile.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.viva.securefile.entity.File;
import com.viva.securefile.entity.FileRequests;
import com.viva.securefile.entity.User;

public interface FileRequestRepository extends JpaRepository<FileRequests, Integer> {
	@Query("select f from FileRequests f where f.requesterid = :user")
	public List<FileRequests> getFileRequestByUser(@Param("user") User user);
	
	@Query("select f from FileRequests f where f.fileid IN :files")
	public List<FileRequests> getFileRequestByFiles(@Param("files") List<File> files);
}
