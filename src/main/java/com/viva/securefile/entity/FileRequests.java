package com.viva.securefile.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
	
	public FileRequests() {}
	public FileRequests(int id, User requesterid, File fileid, String status) {
		super();
		this.id = id;
		this.requesterid = requesterid;
		this.fileid = fileid;
		this.status = status;
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
	@Override
	public String toString() {
		return "FileRequests [id=" + id + ", requesterid=" + requesterid + ", fileid=" + fileid + ", status=" + status + "]";
	}
	
	
}
