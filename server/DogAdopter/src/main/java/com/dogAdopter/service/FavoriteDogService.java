package com.dogAdopter.service;

import java.util.ArrayList;

import com.dogAdopter.entity.Dog;
import com.dogAdopter.entity.FavoriteDog;

public interface FavoriteDogService {
	
	void save(FavoriteDog dog);
	void update(FavoriteDog dog);
	void delete(FavoriteDog dog);

	ArrayList<FavoriteDog> getAll();
	ArrayList<Dog> getAllFavoriteForUser(int userId);

}
