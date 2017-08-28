package com.dogAdopter.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;

import com.dogAdopter.entity.User;
import com.dogAdopter.util.JSONMapper;
import com.google.gson.Gson;

@Path("/userservice")
public class UserRestService extends BaseRestService{
	
	public UserRestService() {
		super();
	}
	
	@GET
	@Path("login/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public String login(@PathParam("username") String username,
					  @PathParam("password") String password) {
		 User user = userService.findUserWithUsernameAndPassword(username, password);
		 return gson.toJson(user);
	}
	
	@GET
	@Path("userById/{idOfUser}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getUserById(@PathParam("idOfUser") String idOfUser){
		
		//User user = userService.findUserWithId(JSONMapper.getID(jsonRequest));
		User user = userService.findUserWithId(Integer.parseInt(idOfUser));
		return gson.toJson(user);
	}
	
	@GET
	@Path("userAll")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllUser(){
		//return JSONMapper.getJSONArray(userService.findAllUser());
		return gson.toJson(userService.findAllUser());
	}

}
