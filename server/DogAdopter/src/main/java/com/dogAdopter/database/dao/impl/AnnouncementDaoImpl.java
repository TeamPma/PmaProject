package com.dogAdopter.database.dao.impl;

import com.dogAdopter.entity.User;
import org.springframework.stereotype.Repository;

import com.dogAdopter.database.dao.AnnouncementDao;
import com.dogAdopter.entity.Announcement;
import com.dogAdopter.util.CustomHibernateDaoSupport;

import java.util.ArrayList;
import java.util.List;

@Repository("announcementDao")
public class AnnouncementDaoImpl extends CustomHibernateDaoSupport implements AnnouncementDao{

	public void save(Announcement announcement) {
		getHibernateTemplate().save(announcement);
	}

	public void update(Announcement announcement) {
		getHibernateTemplate().update(announcement);
	}

	public void delete(Announcement announcement) {
		getHibernateTemplate().delete(announcement);
		
	}

	@Override
	public ArrayList<Announcement> getAnnouncementByShelterId(Integer id) {
		Object[] params = {id};
		String[] paramsS = {"idShelter"};
		List<Announcement> list = getHibernateTemplate().findByNamedQueryAndNamedParam(Announcement.GET_ANNOUNCMENT_BY_SHELTER_ID, paramsS, params);
		if(list.isEmpty()){
			return null;
		}
		return (ArrayList<Announcement>) list;
	}

	@Override
	public Announcement getAnnouncementById(Integer id) {
		Object[] params = {id};
		String[] paramsS = {"idAnnouncement"};
		List<Announcement> list = getHibernateTemplate().findByNamedQueryAndNamedParam(Announcement.GET_ANNOUNCMENT_BY_ID, paramsS, params);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}


}
