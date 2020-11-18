package com.dalesko.jersey;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.google.gson.*;

@Path("example")
public class TestEndPoint {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getText() {
		return "SUCCESSFUL OUTPUT!";
	}
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/params/{id}")
	public String getParamText(@PathParam("id") String id) {
		return "The value of the path parameter is " + id;
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/object/{name}/{password}")
	public String getObject(@PathParam("name") String name, @PathParam("password") String password) {
		
		Person person = new Person();
		person.setId(name);
		person.setPassword(password);
		Gson gson = new Gson();
		return gson.toJson(person);
	}
}
