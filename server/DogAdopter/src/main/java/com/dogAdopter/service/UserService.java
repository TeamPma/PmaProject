package com.dogAdopter.service;

import java.util.ArrayList;

import com.dogAdopter.entity.User;

public interface UserService {
	void save(User user);
	void update(User user);
	void delete(User user);
	
	User findUserWithUsernameAndPassword(String username, String password);
	User findUserWithId(Integer id);
	ArrayList<User> findAllUser();
}
