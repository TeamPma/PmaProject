package com.dogAdopter.database.dao;

import java.util.ArrayList;
import java.util.List;

import com.dogAdopter.entity.Dog;

public interface DogDao {
	
	void save(Dog dog);
	void update(Dog dog);
	void delete(Dog dog);
	
	ArrayList<Dog> getAll();
	ArrayList<Dog> getByIdOfShleter(int id);
	Dog getDogById(int id);


}
