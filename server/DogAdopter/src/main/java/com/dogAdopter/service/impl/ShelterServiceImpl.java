package com.dogAdopter.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogAdopter.database.dao.ShelterDao;
import com.dogAdopter.entity.Shelter;
import com.dogAdopter.service.ShelterService;

@Service("shelterService")
public class ShelterServiceImpl implements ShelterService {

	@Autowired
	ShelterDao shelterDao;
	
	public void save(Shelter shelter) {
		this.shelterDao.save(shelter);
	}

	public void update(Shelter shelter) {
		this.shelterDao.update(shelter);
	}

	public void delete(Shelter shelter) {
		this.shelterDao.delete(shelter);
	}
	public ArrayList<Shelter> getAll(){
		return shelterDao.getAll();
	}

	@Override
	public Shelter getShelterById(Integer id) {
		return shelterDao.getShelterById(id);
	}

}
