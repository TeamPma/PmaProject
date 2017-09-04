package com.dogAdopter.service;

import com.dogAdopter.entity.Announcement;

import java.util.ArrayList;

public interface AnnouncementService {
	void save(Announcement announcement);
	void update(Announcement announcement);
	void delete(Announcement announcement);

	ArrayList<Announcement> getAnnouncementByShelterId(Integer id);
	ArrayList<Announcement> getAllAnnouncements();
	Announcement getAnnouncementId(Integer id);
}
