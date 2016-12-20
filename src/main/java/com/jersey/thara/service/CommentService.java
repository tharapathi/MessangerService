package com.jersey.thara.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.jersey.thara.database.MessageDatabase;
import com.jersey.thara.model.Comments;
import com.jersey.thara.model.ErrorMessages;
import com.jersey.thara.model.MessageVo;

public class CommentService {

	
	private Map<Integer, MessageVo> mapComments = MessageDatabase.getMessageData();
	
	public List<Comments> getAllComments(int messageId){
		Map<Integer, Comments> commentsMap = mapComments.get(messageId).getCommentMap();
		
		return new ArrayList<Comments>(commentsMap.values());
	}
	
	public Comments getCommentsByMessageId(int messageId,int commentId){
		
		ErrorMessages errMessage = new ErrorMessages("Not Found", 404, "http://localhost:8080/MessageBook/");
		Response response = Response.status(Status.NOT_FOUND)
				.entity(errMessage)
				.build();
		MessageVo  message = mapComments.get(messageId);
		if(message == null){
			throw new WebApplicationException(response);
		}
		Map<Integer, Comments> comments = mapComments.get(messageId).getCommentMap();
		if(comments == null){
			throw new WebApplicationException(response);
		}
		return mapComments.get(messageId).getCommentMap().get(commentId);
	}
	
	public Comments addCommentForMessage(int messageId,Comments comment){
		Map<Integer, Comments> commentsMap = mapComments.get(messageId).getCommentMap();
		comment.setId(commentsMap.size() + 1);
		commentsMap.put(comment.getId(), comment);
		
		return comment;
	}
	
	public Comments updateComment(int messageId, Comments comment){
		Map<Integer, Comments> commentsMap = mapComments.get(messageId).getCommentMap();
		if(comment.getId() <=0){
			return null;
		}
		commentsMap.put(comment.getId(), comment);
		
		return comment;
	}
	
	public Comments removeComment(int messageId, int commentId){
		Map<Integer, Comments> commentsMap = mapComments.get(messageId).getCommentMap();
		
		return commentsMap.remove(commentId);
	}

}
