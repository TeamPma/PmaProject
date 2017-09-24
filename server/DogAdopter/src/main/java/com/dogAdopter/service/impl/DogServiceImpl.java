package com.dogAdopter.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogAdopter.database.dao.DogDao;
import com.dogAdopter.database.dao.FavoriteDogDao;
import com.dogAdopter.entity.Dog;
import com.dogAdopter.service.DogService;

@Service("dogService")
public class DogServiceImpl implements DogService {

	@Autowired
	DogDao dogDao;

	@Autowired
	FavoriteDogDao favoriteDogDao;

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
	public ArrayList<Dog> getAll(int userId) {
		ArrayList<Dog> dogList = dogDao.getAll();
		for (Dog dog : dogList) {
			dog.setFavoriteForUser(favoriteDogDao.isFavoriteForUser(dog.getDogId(), userId));
		}
		return dogList;
	}

	@Override
	public ArrayList<Dog> getByIdOfShleter(int id) {
		return dogDao.getByIdOfShleter(id);
	}

	@Override
	public Dog getById(int id) {
		// TODO Auto-generated method stub
		return dogDao.getDogById(id);
	}

}
