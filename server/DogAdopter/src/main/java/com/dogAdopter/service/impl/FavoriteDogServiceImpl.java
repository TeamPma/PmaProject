package com.dogAdopter.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogAdopter.database.dao.FavoriteDogDao;
import com.dogAdopter.entity.FavoriteDog;
import com.dogAdopter.service.FavoriteDogService;

@Service("favoriteService")
public class FavoriteDogServiceImpl implements FavoriteDogService {
	
	@Autowired
	FavoriteDogDao favoriteDao;

	@Override
	public void save(FavoriteDog dog) {
		favoriteDao.save(dog);		
	}

	@Override
	public void update(FavoriteDog dog) {
		favoriteDao.update(dog);
		
	}

	@Override
	public void delete(FavoriteDog dog) {
		favoriteDao.delete(dog);
		
	}

	@Override
	public ArrayList<FavoriteDog> getAll() {
		// TODO Auto-generated method stub
		return favoriteDao.getAll();
	}

	@Override
	public ArrayList<FavoriteDog> getAllFavoriteForUser(int userId) {
		// TODO Auto-generated method stub
		return favoriteDao.getAllFavoriteForUser(userId);
	}

}
