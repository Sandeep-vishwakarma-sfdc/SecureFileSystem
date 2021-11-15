package com.viva.securefile.entity;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String filename;
	private String filepassphrase;
	private String size;
	private String type;
	@CreationTimestamp
	private LocalDateTime createdDate;
	@CreationTimestamp
	private LocalDateTime modifiedDate;
	@OneToOne
	private User owner;
	public File() {}
	public File(int id, String filename, String filepassphrase,  User owner,LocalDateTime  createdDate,LocalDateTime modifiedDate,String size,String type) {
		super();
		this.id = id;
		this.filename = filename;
		this.filepassphrase = filepassphrase;
		this.owner = owner;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.size = size;
		this.type = type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getfilepassphrase() {
		return filepassphrase;
	}
	public void setfilepassphrase(String filepassphrase) {
		this.filepassphrase = filepassphrase;
	}
	
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public LocalDateTime getCreatedDate() {
		return createdDate;
	}
	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}
	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}
	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getSize() {
		return size;
	}
	public String getType() {
		return type;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "File [id=" + id + ", filename=" + filename + ", filepassphrase=" + filepassphrase + ", owner=" + owner + "]";
	}

}
