package com.jersey.thara;

import java.util.List;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import com.jersey.thara.model.MessageVo;
import com.jersey.thara.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(value = {MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
public class MessageResource {
	
	MessageService messageService = new MessageService();
	
	@GET
	public List<MessageVo> MessagerService(@BeanParam MessageFilter filter){
		if(filter.getYear() >0){
			return messageService.getMessages(filter.getYear());
		}
		if(filter.getStart() >=0 && filter.getSize() >0){
			return messageService.getMessagesByPagination(filter.getStart(),filter.getSize());
		}
		return  messageService.getMessages();
		
	}
	@GET
	@Path("/{id}")
	public MessageVo getMessageById(@PathParam("id") int id, @Context UriInfo uriInfo){

		MessageVo message = messageService.getMessageById(id);
		message.addLink(getResponseForMessage(uriInfo, message), "self");
		message.addLink(getResponseForProfile(uriInfo, message), "profile");
	//	message.addLink(getResponseForComments(uriInfo, message), "comments");
		
		
		return  message;

	}
	private String getResponseForComments(UriInfo uriInfo, MessageVo message) {
		String uri = uriInfo.getBaseUriBuilder().
				path(MessageResource.class).
				path(MessageResource.class,"getCommentResource").
				path(CommentResource.class).
				resolveTemplate("messageId", message.getId()).
				build().
				toString();
		  	return uri;
		}
	private String getResponseForProfile(UriInfo uriInfo, MessageVo message) {
		String uri = uriInfo.getBaseUriBuilder().
				path(ProfileResource.class).
				path(message.getAuthor()).
				build().
				toString();
		  	return uri;
		}
	private String getResponseForMessage(UriInfo uriInfo, MessageVo message) {
		String uri = uriInfo.getBaseUriBuilder().
				path(MessageResource.class).
				path(String.valueOf(message.getId())).
				build().
				toString();
		  	return uri;
	}
	@POST
	public MessageVo getMessageType(MessageVo message ){
		return  messageService.addMessage(message);
		
	}
	@PUT
	@Path("/{id}")
	public MessageVo updateMessageType(@PathParam("id") int id, MessageVo message ){
		return  messageService.updateMessage(message);
		
	}
	@DELETE
	@Path("/{messageId}")
	public MessageVo updateMessageType(@PathParam("id") int id){
		return  messageService.removeMessage(id);
	}
	
	@Path("/{id}/comments")
	public CommentResource getCommentResource(){
		return  new CommentResource();
		
	}
}
