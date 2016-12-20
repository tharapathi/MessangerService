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

import com.jersey.thara.model.Comments;
import com.jersey.thara.service.CommentService;

@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource {

	
	CommentService commentService = new CommentService();
	
	@GET
	public List<Comments> getAllComments(@PathParam("messageId") int messageId){
		return  commentService.getAllComments(messageId);
		
	}
	
	@GET
	@Path("/{messageId}")
	public Comments getCommentByMessageId(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId){
		
		
		return  commentService.getCommentsByMessageId(messageId, commentId);
		
	}
	
	@POST
	public Comments addComment(@PathParam("messageId") int messageId, Comments comment){
		return  commentService.addCommentForMessage(messageId, comment);
		
	}
	
	@PUT
	@Path("/{messageId}")
	public Comments updateComment(@PathParam("messageId") int messageId, Comments comment ){
		return  commentService.updateComment(messageId, comment);
		
	}
	@DELETE
	@Path("/{messageId}")
	public Comments removeComment(@PathParam("messageId") int messageId, @PathParam("commentId") int commentId){
		return  commentService.removeComment(messageId, commentId);
	}

}
