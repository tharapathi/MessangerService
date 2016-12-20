package com.jersey.thara;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.jersey.thara.model.ProfileVo;
import com.jersey.thara.service.ProfileService;

@Path("/profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource {
	
	ProfileService profileService = new ProfileService();
	
	@GET
	public List<ProfileVo> getAllProfiles(){
		
		return profileService.getAllProfiles();
	}

	@GET
	@Path("/{profileName}")
	public ProfileVo getProfileById(@PathParam("profileName") String profileName){
		 
		return profileService.getProfileByName(profileName);
	}
	
	@POST
	public ProfileVo addProfile(ProfileVo profileVo){
		
		return profileService.addProfile(profileVo);
	}
	
	@PUT
	@Path("/{profileName}")
	public ProfileVo updateProfile(@PathParam("profileName") String profileName, ProfileVo profileVo){
		
		profileVo.setProfileName(profileName);
		return profileService.updateProfile(profileVo);
		
	}
	
	@DELETE
	@Path("/{profileName}")
	public ProfileVo deleteProfile(@PathParam("profileName") String profileName){
		
		return profileService.removeProfile(profileName);
	}
	
	
}
