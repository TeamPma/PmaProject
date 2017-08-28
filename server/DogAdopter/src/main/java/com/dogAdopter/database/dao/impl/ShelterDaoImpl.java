package com.dogAdopter.database.dao.impl;

import java.util.ArrayList;
import java.util.List;

import com.dogAdopter.entity.User;
import org.springframework.stereotype.Repository;

import com.dogAdopter.database.dao.ShelterDao;
import com.dogAdopter.entity.Shelter;
import com.dogAdopter.util.CustomHibernateDaoSupport;

@Repository("shelterDao")
public class ShelterDaoImpl extends CustomHibernateDaoSupport implements ShelterDao {

	public void save(Shelter shelter) {
		getHibernateTemplate().save(shelter);
	}

	public void update(Shelter shelter) {
		getHibernateTemplate().update(shelter);
	}

	public void delete(Shelter shelter) {
		getHibernateTemplate().delete(shelter);
	}
	
	public ArrayList<Shelter> getAll() {
		List<Shelter> list = getHibernateTemplate().findByNamedQuery(Shelter.GET_ALL);
		if(list.isEmpty()){
			return null;
		}
		return (ArrayList<Shelter>) list;
	}

	@Override
	public Shelter getShelterById(Integer id) {
		Object[] params = {id};
		String[] paramsS = {"idShelter"};
		List<Shelter> list = getHibernateTemplate().findByNamedQueryAndNamedParam(Shelter.GET_SHELTER_BY_ID, paramsS, params);
		if(list.isEmpty()){
			return null;
		}
		return list.get(0);	}

}
