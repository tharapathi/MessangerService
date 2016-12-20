package com.jersey.thara.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import com.jersey.thara.database.MessageDatabase;
import com.jersey.thara.exception.DataNotFoundException;
import com.jersey.thara.model.MessageVo;

public class MessageService {
	
	private Map<Integer, MessageVo> mapMessages = MessageDatabase.getMessageData();
	
	public List<MessageVo> getMessages(){
		
		return new ArrayList<MessageVo>(mapMessages.values());
	}
	
	public MessageVo getMessageById(int messageId){
		
		MessageVo  messageVo = mapMessages.get(messageId);
		if(messageVo == null){
			throw new DataNotFoundException("Message with Id="+messageId +"not found.");
			
		}
		
		return mapMessages.get(messageId);
	}
	
	public MessageVo addMessage(MessageVo message){
		message.setId(mapMessages.size() + 1);
		mapMessages.put(message.getId(), message);
		return message;
	}
	
	public MessageVo updateMessage(MessageVo message){
		if(message.getId() <=0){
			return null;
			
		}
		mapMessages.put(message.getId(), message);
		return message;
	}
	
	public MessageVo removeMessage(int id){
		
		return mapMessages.remove(id);
	}

	public List<MessageVo> getMessages(int year) {
		Calendar cal = Calendar.getInstance();
		List<MessageVo> inTheYearMessages = new ArrayList<MessageVo>();
		
		for (MessageVo messageVo : mapMessages.values()) {
			cal.setTime(messageVo.getCreated());
			if(cal.get(Calendar.YEAR) == year){
				inTheYearMessages.add(messageVo);
			}
		}
		return inTheYearMessages;
	}

	public List<MessageVo> getMessagesByPagination(int start, int size) {
		List<MessageVo> listPagination = new ArrayList<MessageVo>(mapMessages.values());
		if(start +size > listPagination.size()){
			return  new ArrayList<MessageVo>();
		}
		return listPagination.subList(start, start+size);
	}
	
	
	
	

}
