package com.dogAdopter.app;


import com.dogAdopter.service.UserService;
import com.dogAdopter.service.impl.FavoriteDogServiceImpl;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dogAdopter.entity.FavoriteDog;
import com.dogAdopter.entity.User;
import com.dogAdopter.rest.UserRestService;
import com.dogAdopter.service.DogService;
import com.dogAdopter.service.FavoriteDogService;

public class TestApp {

	public static void main(String[] args) {

        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
        FavoriteDogService favoriteService = (FavoriteDogService) appContext.getBean("favoriteService");
        favoriteService.save(new FavoriteDog(1, 2));
        favoriteService.save(new FavoriteDog(2, 2));
        favoriteService.save(new FavoriteDog(8, 2));
        
        ArrayList<FavoriteDog> dogs = favoriteService.getAllFavoriteForUser(2);
        for (FavoriteDog favoriteDog : dogs) {
			System.out.println(favoriteDog.getDogId());
		}

	}
	
}
