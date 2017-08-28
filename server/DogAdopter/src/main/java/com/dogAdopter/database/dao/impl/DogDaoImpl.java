package com.dogAdopter.database.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.dogAdopter.entity.User;
import org.springframework.stereotype.Repository;

import com.dogAdopter.database.dao.DogDao;
import com.dogAdopter.entity.Dog;
import com.dogAdopter.util.CustomHibernateDaoSupport;

@Repository("dogDao")
public class DogDaoImpl extends CustomHibernateDaoSupport implements DogDao{

	public void save(Dog dog) {
		getHibernateTemplate().save(dog);
	}

	public void update(Dog dog) {
		getHibernateTemplate().update(dog);
	}

	public void delete(Dog dog) {
		getHibernateTemplate().delete(dog);
	}
	
	public ArrayList<Dog> getAll(){
		List<Dog> list = getHibernateTemplate().findByNamedQuery(Dog.FIND_ALL_DOGS);
		if(list.isEmpty()){
			return null;
		}
		return (ArrayList<Dog>) list;
	}
	
	public ArrayList<Dog> getByIdOfShleter(int idShelter){
		Object[] params = {idShelter};
		String[] paramsS = {"idShelter"};
		List<Dog> list = getHibernateTemplate().findByNamedQueryAndNamedParam(Dog.FIND_DOGS_BY_ID_OF_SHELTER, paramsS, params);
		if(list.isEmpty()){
			return null;
		}
		return (ArrayList<Dog>) list;
	}
	

}
