package com.viva.securefile.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String email;
	private String password;
	private String role;
	private String active;
	private LocalDateTime createdDate;
	private LocalDateTime modifiedDate;
	@ManyToMany
	@JoinTable(name = "fileowner", joinColumns = @JoinColumn(name = "ownerid"))
	private List<File> files;
	
	public User() {}

	public User(int id, String name, String email, String password, String role, String active,
			LocalDateTime createdDate, LocalDateTime modifiedDate, List<File> files) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.role = role;
		this.active = active;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
		this.files = files;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getRole() {
		return role;
	}

	public String getActive() {
		return active;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public LocalDateTime getModifiedDate() {
		return modifiedDate;
	}

	public List<File> getFiles() {
		return files;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public void setActive(String active) {
		this.active = active;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public void setModifiedDate(LocalDateTime modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

	public void setFiles(List<File> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", role=" + role
				+ ", active=" + active + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate + ", files="
				+ files + "]";
	}
}
