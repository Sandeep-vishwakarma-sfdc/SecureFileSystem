package com.viva.securefile.helper;

import java.time.LocalDateTime;

import com.viva.securefile.entity.File;

public class FileWrapper {
	public int fid;//file id
	public String fname;
	public String furl;
	public LocalDateTime fcreatedDate;
	public LocalDateTime fmodifiedDate;
	public String ownername;
	public String status;
	public String size;
	public String type;
	public FileWrapper(File file, String status) {
		super();
		this.fid = file.getId();
		this.fname  = file.getFilename();
		this.fcreatedDate = file.getCreatedDate();
		this.fmodifiedDate = file.getModifiedDate();
		this.ownername = file.getOwner().getName();
		this.status = status;
		this.size = file.getSize();
		this.type = file.getType();
	}
	@Override
	public String toString() {
		return "FileWrapper [fid=" + fid + ", fname=" + fname + ", fcreatedDate=" + fcreatedDate
				+ ", fmodifiedDate=" + fmodifiedDate + ", ownername=" + ownername + ", status=" + status + ", size="
				+ size + ", type=" + type + "]";
	}
	
	
	
}
