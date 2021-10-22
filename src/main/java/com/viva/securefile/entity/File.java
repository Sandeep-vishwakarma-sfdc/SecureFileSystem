package com.viva.securefile.entity;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String filename;
	private String filepassphrase;
	private String fileurl;
	@OneToOne
	private User owner;
	public File() {}
	public File(int id, String filename, String filepassphrase, String fileurl, User owner) {
		super();
		this.id = id;
		this.filename = filename;
		this.filepassphrase = filepassphrase;
		this.fileurl = fileurl;
		this.owner = owner;
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
	public String getfileurl() {
		return fileurl;
	}
	public void setfileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	@Override
	public String toString() {
		return "File [id=" + id + ", filename=" + filename + ", filepassphrase=" + filepassphrase + ", fileurl="
				+ fileurl + ", owner=" + owner + "]";
	}

}
