package com.dogAdopter.rest;

import com.dogAdopter.entity.Dog;
import com.dogAdopter.entity.Shelter;
import com.dogAdopter.util.JSONMapper;
import com.google.gson.Gson;

import org.codehaus.jackson.map.Serializers;

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
        //return dogService.getByIdOfShleter(JSONMapper.getID(jsonRequest));
    }

    @GET
    @Path("dogAll")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllDogs(){
        //return JSONMapper.getJSONArray(dogService.getAll());
    	 return gson.toJson(dogService.getAll());
    }
}
