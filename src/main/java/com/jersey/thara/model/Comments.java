package com.jersey.thara.model;

import java.util.Date;

public class Comments {
	
	private int id;
	private String message;
	private String author;
	private Date created;
	
	public Comments(){
		
	}
	
	
	public Comments(int id, String message, String author, Date createDate) {
		super();
		this.id = id;
		this.message = message;
		this.author = author;
		this.created = new Date();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date createDate) {
		this.created = createDate;
	}
	

}
