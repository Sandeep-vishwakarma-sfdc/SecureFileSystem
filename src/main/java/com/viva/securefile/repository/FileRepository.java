package com.viva.securefile.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.viva.securefile.entity.File;
import com.viva.securefile.entity.User;

public interface FileRepository extends JpaRepository<File, Integer>{

	@Query("select f from File f where f.owner.email = :email")
	public List<File> getOtherUserFiles(@Param("email") String email);
	
	@Query("select f from File f where f.owner = :user")
	public List<File> getLoggedinUserFiles(@Param("user") User user);
	
	@Query("select f from File f where f.filename = :filename")
	public File findByName(@Param("filename") String filename);
}
