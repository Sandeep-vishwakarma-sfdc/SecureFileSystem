package com.viva.securefile.helper;

public class Message {
	String Content;
	String type;
	public Message() {}
	public Message(String content, String type) {
		super();
		Content = content;
		this.type = type;
	}
	public String getContent() {
		return Content;
	}
	public String getType() {
		return type;
	}
	public void setContent(String content) {
		Content = content;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
