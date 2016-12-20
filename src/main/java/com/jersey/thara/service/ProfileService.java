package com.jersey.thara.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.jersey.thara.database.MessageDatabase;
import com.jersey.thara.model.ProfileVo;

public class ProfileService {

	
	private Map<String, ProfileVo> profileMap = MessageDatabase.getProfileMessage();
	
	public ProfileService(){
		profileMap.put("tharapathi", new ProfileVo(1, "vani", "tharapathi", "reddy"));
	}
	
	public List<ProfileVo> getAllProfiles(){
		
		return new ArrayList<ProfileVo>(profileMap.values());
	}
	
	public ProfileVo getProfileByName(String profileName){
		
		return profileMap.get(profileName);
	}
	
	public ProfileVo addProfile(ProfileVo profile){
		profile.setProfileId(profileMap.size() + 1);
		profileMap.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public ProfileVo updateProfile(ProfileVo profile){
		if(profile.getProfileName().isEmpty()){
			return null;
			
		}
		profileMap.put(profile.getProfileName(), profile);
		return profile;
	}
	
	public ProfileVo removeProfile(String profileName){
		
		return profileMap.remove(profileName);
	}
	



}
