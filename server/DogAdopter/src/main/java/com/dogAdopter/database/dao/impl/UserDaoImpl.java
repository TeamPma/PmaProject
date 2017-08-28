package com.dogAdopter.database.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dogAdopter.database.dao.UserDao;
import com.dogAdopter.entity.User;
import com.dogAdopter.util.CustomHibernateDaoSupport;

@Repository("userDao")
public class UserDaoImpl extends CustomHibernateDaoSupport implements UserDao {

	public void save(User user) {
		getHibernateTemplate().save(user);
	}

	public void update(User user) {
		getHibernateTemplate().update(user);
	}

	public void delete(User user) {
		getHibernateTemplate().delete(user);
	}

	@Override
	public User findUserWithUsernameAndPassword(String username, String password) {
		Object[] params = {username, password};
		String[] paramsS = {"username", "password"};
		List<User> list = getHibernateTemplate().findByNamedQueryAndNamedParam(User.FIND_USER_WITH_USERNAME_AND_PASSWORD, paramsS, params);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public User findUserWithId(Integer id) {
		Object[] params = {id};
		String[] paramsS = {"id"};
		List<User> list = getHibernateTemplate().findByNamedQueryAndNamedParam(User.FIND_USER_WITH_ID, paramsS, params);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);
	}

	@Override
	public ArrayList<User> findAllUser() {
		List<User> list = getHibernateTemplate().findByNamedQuery(User.FIND_ALL_USERS);
		return (ArrayList<User>) list;
	}
	
	

}
