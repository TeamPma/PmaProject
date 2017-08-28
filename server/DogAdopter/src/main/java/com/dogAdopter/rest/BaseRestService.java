package com.dogAdopter.rest;

import com.dogAdopter.service.*;
import com.google.gson.Gson;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class BaseRestService {

    protected UserService userService;
    protected DogService dogService;
    protected VeterinaryClinicService veterinaryService;
    protected ShelterService shelterService;
    protected AnnouncementService announcementService;
    protected Gson gson;

    public BaseRestService() {

        ApplicationContext appContext =
                new ClassPathXmlApplicationContext("spring/config/BeanLocations.xml");
        userService = (UserService) appContext.getBean("userService");
        dogService = (DogService) appContext.getBean("dogService");
        veterinaryService = (VeterinaryClinicService) appContext.getBean("veterinaryClinicService");
        shelterService = (ShelterService) appContext.getBean("shelterService");
        announcementService = (AnnouncementService) appContext.getBean("announcementService");
        gson = new Gson();

    }

}
