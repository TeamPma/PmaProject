package com.dogAdopter.rest;

import com.dogAdopter.entity.Announcement;
import com.dogAdopter.entity.Shelter;
import com.dogAdopter.util.JSONMapper;
import com.google.gson.Gson;

import java.util.Random;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/shelterService")
public class ShelterRestService extends BaseRestService {
	
    public ShelterRestService() {
        super();
    }

    @GET
    @Path("shelterByID/{idOfShelter}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getShelterById(@PathParam("idOfShelter") String idOfShelter) {
    	Shelter shelter = shelterService.getShelterById(Integer.parseInt(idOfShelter));
    	return gson.toJson(shelter);
    	
    }

    @GET
    @Path("shelterAll")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllShelter() {
	   return gson.toJson(shelterService.getAll());
    }
    
    @GET
    @Path("addShelter/{shelter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String addShelter(@PathParam("shelter") String shelter) {
    	Shelter shelterFromJson = gson.fromJson(shelter, Shelter.class);
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		shelterFromJson.setIdShelter(randomInt);
		shelterService.save(shelterFromJson);
		return gson.toJson(shelterService.getAll());
    }
    
    @GET
    @Path("updateShelter/{shelter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String updateShelter(@PathParam("shelter") String shelter) {
    	Shelter shelterFromJson = gson.fromJson(shelter, Shelter.class);
		shelterService.update(shelterFromJson);
		return gson.toJson(shelterService.getAll());
    }
    
    @GET
    @Path("deleteShelter/{shelter}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteShelter(@PathParam("shelter") String shelter) {
    	Shelter shelterFromJson = gson.fromJson(shelter, Shelter.class);
		shelterService.delete(shelterFromJson);
		return gson.toJson(shelterService.getAll());
    }

}
