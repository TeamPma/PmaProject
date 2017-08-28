package com.dogAdopter.rest;

import com.dogAdopter.entity.Shelter;
import com.dogAdopter.util.JSONMapper;
import com.google.gson.Gson;

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
        //return shelterService.getShelterById(JSONMapper.getID(jsonRequest));
    	Shelter shelter = shelterService.getShelterById(Integer.parseInt(idOfShelter));
    	return gson.toJson(shelter);
    	
    }

    @GET
    @Path("shelterAll")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllShelter() {
       // return JSONMapper.getJSONArray(shelterService.getAll());
	   return gson.toJson(shelterService.getAll());
    }

}
