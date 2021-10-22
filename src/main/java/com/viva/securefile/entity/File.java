package com.viva.securefile.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class File {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	private String file_url;
	@OneToOne
	private User owner;
	@ManyToMany
	@JoinTable(name = "filerequester", joinColumns = @JoinColumn(name = "fileid"))
	private List<User> requesters;

	public File(int id, String name, String file_url, User owner, List<User> requesters) {
		super();
		this.id = id;
		this.name = name;
		this.file_url = file_url;
		this.owner = owner;
		this.requesters = requesters;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getFile_url() {
		return file_url;
	}

	public User getOwner() {
		return owner;
	}

	public List<User> getRequesters() {
		return requesters;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setFile_url(String file_url) {
		this.file_url = file_url;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public void setRequesters(List<User> requesters) {
		this.requesters = requesters;
	}

	@Override
	public String toString() {
		return "File [id=" + id + ", name=" + name + ", file_url=" + file_url + ", owner=" + owner + ", requesters="
				+ requesters + "]";
	}
}
