package com.jersey.thara.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.jersey.thara.model.ErrorMessages;

@Provider
public class DataNotFoundExceptionMessage implements ExceptionMapper<DataNotFoundException> {
	
	public Response toResponse(DataNotFoundException exception) {
		
		ErrorMessages errMessage = new ErrorMessages(exception.getMessage(), 404, "http://localhost:8080/MessageBook/");
		return Response.status(Status.NOT_FOUND)
				.entity(errMessage)
				.build();
	}

}
