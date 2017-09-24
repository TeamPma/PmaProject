package com.dogAdopter.rest;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.dogAdopter.entity.Dog;
import com.dogAdopter.entity.FavoriteDog;

@Path("/favoriteDogService")
public class FavoriteDogRestService extends BaseRestService {

	public FavoriteDogRestService() {
		super();
	}
	
    @GET
    @Path("favoriteDogByUserId/{userID}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getDogById(@PathParam("userID") String userID){
    	return gson.toJson(favoriteService.getAllFavoriteForUser(Integer.parseInt(userID)));
    }
    
    @GET
    @Path("addFavoriteDog/{userId}/{dogId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String addFavoriteDog(@PathParam("userId") String userId, @PathParam("dogId") String dogId) {
    	FavoriteDog dogFromJson = new FavoriteDog(Integer.parseInt(userId),Integer.parseInt(dogId));
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		dogFromJson.setId(randomInt);
		favoriteService.save(dogFromJson);
		return gson.toJson(favoriteService.getAllFavoriteForUser(dogFromJson.getIdUser()));
    }
	
	

}
