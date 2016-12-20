package com.jersey.thara;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/injectdemo")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	
	@GET
	@Path("annotation")
	public String getParamsUsingAnnotations(@MatrixParam("param") String param,
			@HeaderParam("authSessionId") String authSessionId,
			@CookieParam("name") String name){
		return "Test ===:::::::::"+param +"===>:"+authSessionId;
		
	}
	
	@GET
	@Path("context")
	public String getContextPathUsingAnnotations(@Context UriInfo uriInfo, @Context HttpHeaders header ){
		String path = uriInfo.getAbsolutePath().toString();
		String cookiesPath = header.getCookies().toString();
		return "path ===:::::::::"+path +"===>cookiesPath:"+cookiesPath;
		
	}

}
