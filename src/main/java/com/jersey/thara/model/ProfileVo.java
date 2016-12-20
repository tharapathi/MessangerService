package com.jersey.thara.model;

import java.util.Date;

public class ProfileVo {
	
	private int profileId;
	private String profileName;
	private String firstName;
	private String lastName;
	private Date createdDate;
	
	public ProfileVo() {
	}
	
	public ProfileVo(int profileId,String profileName, String firstName, String lastName) {
		super();
		this.profileId = profileId;
		this.setProfileName(profileName);
				
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public int getProfileId() {
		return profileId;
	}
	public void setProfileId(int profileId) {
		this.profileId = profileId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getProfileName() {
		return profileName;
	}

	public void setProfileName(String profileName) {
		this.profileName = profileName;
	}
	

}
