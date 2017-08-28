package com.dogAdopter.service.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dogAdopter.database.dao.UserDao;
import com.dogAdopter.entity.User;
import com.dogAdopter.service.UserService;

@Service("userService")
public class UserSeviceImpl implements UserService {
	
	@Autowired
	UserDao userDao;

	public void save(User user) {
		userDao.save(user);
		
	}

	public void update(User user) {
		userDao.update(user);
		
	}

	public void delete(User user) {
		userDao.delete(user);
		
	}

	@Override
	public User findUserWithUsernameAndPassword(String username, String password) {
		return userDao.findUserWithUsernameAndPassword(username, password);
	}

	@Override
	public User findUserWithId(Integer id) {
		// TODO Auto-generated method stub
		return userDao.findUserWithId(id);
	}

	@Override
	public ArrayList<User> findAllUser() {
		// TODO Auto-generated method stub
		return userDao.findAllUser();
	}

}
