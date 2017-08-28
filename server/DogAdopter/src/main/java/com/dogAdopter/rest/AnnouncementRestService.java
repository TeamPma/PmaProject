package com.dogAdopter.rest;

import com.dogAdopter.util.JSONMapper;
import com.google.gson.Gson;

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
@Path("/announcementService")
public class AnnouncementRestService extends BaseRestService {

    public AnnouncementRestService() {
        super();
    }


    @GET
    @Path("announcementByShelterId/{idOfShelter}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAnnouncementByShelterId(@PathParam("idOfShelter") String idOfShelter){
       // return announcementService.getAnnouncementByShelterId(JSONMapper.getID(jsonRequest));
    	Object announcement = announcementService.getAnnouncementByShelterId(Integer.parseInt(idOfShelter));
    	return gson.toJson(announcement);
    }

    @GET
    @Path("announcementById/{idOfAnnouncement}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public String getAnnouncementId(@PathParam("idOfAnnouncement") String idOfAnnouncement){
       // return announcementService.getAnnouncementId(JSONMapper.getID(jsonRequest));
    	Object announcement = announcementService.getAnnouncementId(Integer.parseInt(idOfAnnouncement));
    	return gson.toJson(announcement);
    }


}
