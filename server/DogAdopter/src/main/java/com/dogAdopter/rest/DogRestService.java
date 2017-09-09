package com.dogAdopter.rest;

import com.dogAdopter.entity.Dog;
import com.dogAdopter.entity.Shelter;
import com.dogAdopter.util.JSONMapper;
import com.google.gson.Gson;

import org.codehaus.jackson.map.Serializers;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Maja on 25.6.2017.
 */
@Path("/dogService")
public class DogRestService extends BaseRestService {

    public DogRestService() {
        super();
    }

    @GET
    @Path("dogByShelterId/{idOfDog}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getDogById(@PathParam("idOfDog") String idOfDog){
    	
    	Object dog = dogService.getByIdOfShleter(Integer.parseInt(idOfDog));
    	return gson.toJson(dog);
    }

    @GET
    @Path("dogAll")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDogs(){
    	 return gson.toJson(dogService.getAll());
    }
    
    @GET
    @Path("addDog/{dog}")
    @Produces(MediaType.APPLICATION_JSON)
    public String addDog(@PathParam("dog") String dog) {
    	Dog dogFromJson = gson.fromJson(dog, Dog.class);
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		dogFromJson.setDogId(randomInt);
		dogService.save(dogFromJson);
		return gson.toJson(dogService.getAll());
    }
    
    @GET
    @Path("updateDog/{dog}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateDog(@PathParam("dog") String dog) {
    	Dog dogFromJson = gson.fromJson(dog, Dog.class);
		dogService.update(dogFromJson);
		return gson.toJson(dogService.getAll());
    }
    
    @GET
    @Path("deleteDog/{dog}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteDog(@PathParam("dog") String dog) {
    	Dog dogFromJson = gson.fromJson(dog, Dog.class);
		dogService.delete(dogFromJson);
		return gson.toJson(dogService.getAll());
    }
}
