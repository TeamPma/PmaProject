package com.dogAdopter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogAdopter.database.dao.DogDao;
import com.dogAdopter.entity.Dog;
import com.dogAdopter.service.DogService;

@Service("dogService")
public class DogServiceImpl implements DogService {

	@Autowired
	DogDao dogDao;
	
	public void save(Dog dog) {
		dogDao.save(dog);
	}

	public void update(Dog dog) {
		dogDao.update(dog);
	}

	public void delete(Dog dog) {
		dogDao.delete(dog);
	}

	@Override
	public ArrayList<Dog> getAll() {
		return dogDao.getAll();
	}

	@Override
	public ArrayList<Dog> getByIdOfShleter(int id) {
		return dogDao.getByIdOfShleter(id);
	}

}
