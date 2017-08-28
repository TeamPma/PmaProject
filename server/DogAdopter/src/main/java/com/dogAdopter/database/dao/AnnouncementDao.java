package com.dogAdopter.database.dao;

import com.dogAdopter.entity.Announcement;

import java.util.ArrayList;

public interface AnnouncementDao {
	void save(Announcement announcement);
	void update(Announcement announcement);
	void delete(Announcement announcement);

    ArrayList<Announcement> getAnnouncementByShelterId(Integer id);
	Announcement getAnnouncementById(Integer id);
}
