package com.dogAdopter.app;


import com.dogAdopter.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dogAdopter.rest.UserRestService;
import com.dogAdopter.service.DogService;

public class TestApp {

	public static void main(String[] args) {
		ApplicationContext appContext =
		    	  new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
		//testForUserService(appContext);
		//testForDogService(appContext);
		UserRestService service =  new UserRestService();
		Object a = service.getUserById("1");
	}
	public static void testForUserService(ApplicationContext appContext){
		UserService userService = (UserService)appContext.getBean("userService");
		System.out.println("Find all users:" + userService.findAllUser());
		System.out.println("Find findUserWithId:" + userService.findUserWithId(1));
	}
	public static void testForDogService(ApplicationContext appContext){
		DogService dogService = (DogService)appContext.getBean("dogService");
		System.out.println(dogService.getAll());
		System.out.println(dogService.getByIdOfShleter(1));

	}
	public static void testForShleterService(ApplicationContext appContext){
		
	}
	public static void testForVCService(ApplicationContext appContext){
		
	}
	public static void testForAnnouncementService(ApplicationContext appContext){
		
	}
}
