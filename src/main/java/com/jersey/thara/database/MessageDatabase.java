package com.jersey.thara.database;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.jersey.thara.model.Comments;
import com.jersey.thara.model.MessageVo;
import com.jersey.thara.model.ProfileVo;


public class MessageDatabase {
	
	private static Map<Integer, MessageVo> messageMap = new HashMap<Integer, MessageVo>();
	private static Map<String, ProfileVo> profileMap = new HashMap<String, ProfileVo>();
	private static Map<Integer,Comments> commentsMap = new HashMap<Integer, Comments>();
	
	public static Map<Integer, MessageVo> getMessageData(){
		
		MessageVo  message1 = new MessageVo(1, "Thara", "thara@gmail.com","7405207197","rodJonson", "rodJonson");
		MessageVo  message2 = new MessageVo(2, "Hemapathi", "hema@gmail.com","9177039308","hema", "RVR");
		MessageVo  message3 = new MessageVo(3,"jhansi", "jhan@gmail.com","9968575785", "Jhan", "NGR");
		MessageVo  message4 = new MessageVo(4, "srav", "srav@gmail.com","9445885787","srav", "vani");
		messageMap.put(1, message1);
		messageMap.put(2, message2);
		messageMap.put(3, message3);
		messageMap.put(4, message4);
		return messageMap;
		
	}
	
	public static Map<String, ProfileVo> getProfileMessage(){
		
		return profileMap;
		
	}
	public Collection<? extends MessageVo> values() {
		// TODO Auto-generated method stub
		return messageMap.values();
	}
	
	public static Map<Integer, Comments> getComments(){
		return commentsMap;
	}

}
