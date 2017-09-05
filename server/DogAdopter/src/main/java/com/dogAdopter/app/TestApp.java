package com.dogAdopter.app;


import com.dogAdopter.service.UserService;
import com.google.gson.Gson;

import java.util.Random;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dogAdopter.entity.User;
import com.dogAdopter.rest.UserRestService;
import com.dogAdopter.service.DogService;

public class TestApp {

	public static void main(String[] args) {
		//ApplicationContext appContext =
		//    	  new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
		//testForUserService(appContext);
		//testForDogService(appContext);

		UserRestService service =  new UserRestService();
		Object a = service.addUser("");
	}
	
}
