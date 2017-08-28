package com.dogAdopter.rest;

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

@Path("/veterinaryService")
public class VeterinaryRestService extends BaseRestService{
	
    public VeterinaryRestService() {
        super();
    }

    @GET
    @Path("veterinaryById/{idOfVet}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getUserById(@PathParam("idOfVet") String idOfVet){
        //return veterinaryService.findVeterinaryById(JSONMapper.getID(jsonRequest));
    	Object vet = veterinaryService.findVeterinaryById(Integer.parseInt(idOfVet));
    	return gson.toJson(vet);
    }

    @GET
    @Path("veterinaryAll")
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllUser(){
        //return JSONMapper.getJSONArray(veterinaryService.findAllVeterinary());
    	 return gson.toJson(veterinaryService.findAllVeterinary());
    }
}
