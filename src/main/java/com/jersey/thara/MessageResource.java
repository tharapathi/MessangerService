package com.jersey.thara;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
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

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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
	
	@POST
	@Path("addUser/{name}/{author}/{message}/{email}/{phoneNumber}")/*name,email,phoneNumber*/
	public MessageVo addMessageVo(@PathParam("name") String name,
			@PathParam("author") String author,
			@PathParam("message") String strMessage,
			@PathParam("email") String email,
			@PathParam("phoneNumber") String phoneNumber
			){
		MessageVo message = new 	MessageVo(0,name, strMessage, author, email, phoneNumber);
		return  messageService.addMessage(message);

	}
	@POST
	@Path("addUser/test/{data}")/*name,email,phoneNumber*/
	public MessageVo addMessageVoJson(@PathParam("data") String data
			) throws ParseException{
		MessageVo ronaldo = null;
		try {
			ObjectMapper mapper = new ObjectMapper();
			
			JSONParser parser = new JSONParser(); 
			ronaldo = mapper.readValue(new File("data.json"), MessageVo.class);
		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		String jsonInString = data;

//		MessageVo message1 = new 	MessageVo(0,message.get, strMessage, author, email, phoneNumber);
		return  messageService.addMessage(ronaldo);

	}
	public static void toJava() { // this is the key object to convert JSON to Java 
		ObjectMapper mapper = new ObjectMapper(); 
		try {
			File json = new File("MessageVo.json"); 
			MessageVo cricketer = mapper.readValue(json, MessageVo.class);
			System.out.println("Java object created from JSON String :");
			System.out.println(cricketer); 
			} catch (JsonGenerationException ex) {
				ex.printStackTrace(); 
				} catch (JsonMappingException ex) {
					ex.printStackTrace(); 
					} catch (IOException ex) {
						ex.printStackTrace(); 
						}
		}
	/** * Java method to convert Java Object into JSON String with help of Jackson API. * *//* 
	public static void toJSON() {
		MessageVo kevin = new MessageVo(0,"thara","tt","rr","rrr","fa"); // our bridge from Java to JSON and vice versa 
		ObjectMapper mapper = new ObjectMapper();
		try {
			File json = new File("MessageVo.json");
			mapper.writeValue(json, kevin);
			System.out.println("Java object converted to JSON String, written to file");
			System.out.println(mapper.writeValueAsString(kevin));
			} catch (JsonGenerationException ex) {
				ex.printStackTrace();
				} catch (JsonMappingException ex) {
					ex.printStackTrace();
					} catch (IOException ex) { 
						ex.printStackTrace();
						}
		}
	}*/

	
	@PUT
	@Path("edit/{id}/{name}/{author}/{message}/{email}/{phoneNumber}")
	public MessageVo updateMessageType(@PathParam("id") int id, @PathParam("name") String name,
			@PathParam("author") String author,
			@PathParam("message") String strMessage,
			@PathParam("email") String email,
			@PathParam("phoneNumber") String phoneNumber
			){
		MessageVo message = new 	MessageVo(id,name, strMessage, author, email, phoneNumber);
		return  messageService.updateMessage(message);
		
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
