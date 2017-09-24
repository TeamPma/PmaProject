package com.dogAdopter.database.dao;

import java.util.ArrayList;

import com.dogAdopter.entity.FavoriteDog;

public interface FavoriteDogDao {

	void save(FavoriteDog dog);
	void update(FavoriteDog dog);
	void delete(FavoriteDog dog);

	ArrayList<FavoriteDog> getAll();
	ArrayList<FavoriteDog> getAllFavoriteForUser(int userId);
	boolean isFavoriteForUser(int dogId, int userId);
}
