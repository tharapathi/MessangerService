package com.jersey.thara.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement
public class MessageVo {
	
	private int id;
	private String message;
	private Date created;
	private String author;
	
	private Map<Integer, Comments> commentMap = new HashMap<Integer, Comments>();
	
	public List<Link> links = new ArrayList<Link>();
	
	
	public MessageVo() {
	}
	
	public List<Link> getLink() {
		return links;
	}

	public void setLink(List<Link> link) {
		this.links = link;
	}

	@XmlTransient
	public Map<Integer, Comments> getCommentMap() {
		return commentMap;
	}

	public void setCommentMap(Map<Integer, Comments> commentMap) {
		this.commentMap = commentMap;
	}

	public MessageVo(int id, String message, String author) {
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
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public void addLink(String url,String rel){
		
		Link link = new Link();
		link.setLink(url);
		link.setRel(rel);
		
		links.add(link);
	}

}
