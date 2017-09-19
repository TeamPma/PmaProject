package com.dogAdopter.database.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.dogAdopter.database.dao.FavoriteDogDao;
import com.dogAdopter.entity.Dog;
import com.dogAdopter.entity.FavoriteDog;
import com.dogAdopter.util.CustomHibernateDaoSupport;

@Repository("favoriteDao")
public class FavoriteDogDaoImpl extends CustomHibernateDaoSupport implements FavoriteDogDao {
	
	

	@Override
	public void save(FavoriteDog dog) {
		getHibernateTemplate().save(dog);
		
	}

	@Override
	public void update(FavoriteDog dog) {
		getHibernateTemplate().update(dog);
		
	}

	@Override
	public void delete(FavoriteDog dog) {
		getHibernateTemplate().delete(dog);
		
	}

	@Override
	public ArrayList<FavoriteDog> getAll() {
		return new ArrayList<>();
	}

	@Override
	public ArrayList<FavoriteDog> getAllFavoriteForUser(int userId) {
		Object[] params = {userId};
		String[] paramsS = {"idUser"};
		List<FavoriteDog> list = getHibernateTemplate().findByNamedQueryAndNamedParam(FavoriteDog.FIND_ALL_FAVORITE_DOGS_BY_USER_ID, paramsS, params);
		if(list.isEmpty()){
			return null;
		}
		return (ArrayList<FavoriteDog>) list;
	}

}
