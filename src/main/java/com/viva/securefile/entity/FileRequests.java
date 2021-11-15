package com.viva.securefile.entity;
import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "FILEREQUEST")
public class FileRequests {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@OneToOne
	private User requesterid;
	@OneToOne
	private File fileid;
	private String status;
	@CreationTimestamp
	private LocalDateTime createdDate;
	@CreationTimestamp
	private LocalDateTime modifiedDate;
	
	public FileRequests() {}
	public FileRequests(int id, User requesterid, File fileid, String status,LocalDateTime createdDate, LocalDateTime modifiedDate) {
		super();
		this.id = id;
		this.requesterid = requesterid;
		this.fileid = fileid;
		this.status = status;
		this.createdDate = createdDate;
		this.modifiedDate = modifiedDate;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getrequesterid() {
		return requesterid;
	}
	public void setrequesterid(User requesterid) {
		this.requesterid = requesterid;
	}
	public File getFileid() {
		return fileid;
	}
	public void setFileid(File fileid) {
		this.fileid = fileid;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
	@Override
	public String toString() {
		return "FileRequests [id=" + id + ", requesterid=" + requesterid + ", fileid=" + fileid + ", status=" + status + "]";
	}
	
	
}
