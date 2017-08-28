package com.dogAdopter.database.dao;

import java.util.ArrayList;

import com.dogAdopter.entity.User;

public interface UserDao {
	
	void save(User user);
	void update(User user);
	void delete(User user);
	
	User findUserWithUsernameAndPassword(String username, String password);
	User findUserWithId(Integer id);
	ArrayList<User> findAllUser();

}
