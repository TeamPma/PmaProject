package com.dogAdopter.rest;

import com.dogAdopter.entity.Announcement;
import com.dogAdopter.entity.User;
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

/**
 * Created by Maja on 25.6.2017.
 */
@Path("/announcementService")
public class AnnouncementRestService extends BaseRestService {

	public AnnouncementRestService() {
		super();
	}

	// getAnnByDate

	@GET
	@Path("announcementByShelterId/{idOfShelter}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAnnouncementByShelterId(@PathParam("idOfShelter") String idOfShelter) {
		int shleterId = gson.fromJson(idOfShelter, int.class);
		Object announcement = announcementService.getAnnouncementByShelterId(shleterId);
		return gson.toJson(announcement);
	}

	@GET
	@Path("announcementById/{idOfAnnouncement}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public String getAnnouncementId(@PathParam("idOfAnnouncement") String idOfAnnouncement) {
		Object announcement = announcementService.getAnnouncementId(Integer.parseInt(idOfAnnouncement));
		return gson.toJson(announcement);
	}

	@GET
	@Path("announcementsAll")
	@Produces(MediaType.APPLICATION_JSON)
	public String getAllAnnouncements() {
		return gson.toJson(announcementService.getAllAnnouncements());
	}

	@GET
	@Path("addAnnouncement/{announcement}")
	@Produces(MediaType.APPLICATION_JSON)
	public String addAnnouncement(@PathParam("announcement") String announcement) {
		Announcement announcementFromJson = gson.fromJson(announcement, Announcement.class);
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		announcementFromJson.setIdAnnouncement(randomInt);
		announcementService.save(announcementFromJson);
		return gson.toJson(announcementService.getAllAnnouncements());
	}

	@GET
	@Path("updateAnnouncement/{announcement}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateAnnouncement(@PathParam("announcement") String announcement) {
		Announcement announcementFromJson = gson.fromJson(announcement, Announcement.class);
		announcementService.update(announcementFromJson);
		return announcement;
	}

	@GET
	@Path("updateRankingScore/{idOfAnnouncement}/{score}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateRankingScore(@PathParam("idOfAnnouncement") String idOfAnnouncement,
			@PathParam("score") String score) {
		Announcement announcement = announcementService.getAnnouncementId(Integer.parseInt(idOfAnnouncement));
		announcement.setRankingSize(announcement.getRankingSize() + 1);
		announcement.setRankingScore(announcement.getRankingScore() + Integer.parseInt(score));
		announcementService.update(announcement);
		return gson.toJson(announcement);
	}

	@GET
	@Path("deleteAnnouncement/{announcement}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteAnnouncement(@PathParam("announcement") String announcement) {
		Announcement announcementFromJson = gson.fromJson(announcement, Announcement.class);
		announcementService.delete(announcementFromJson);
		return "true";
	}

}
