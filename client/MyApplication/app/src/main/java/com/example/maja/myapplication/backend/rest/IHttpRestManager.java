package com.example.maja.myapplication.backend.rest;

import com.example.maja.myapplication.backend.entity.User;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Maja on 27.8.2017.
 */

public interface IHttpRestManager {

    // -------------User --------------------
    @GET("login/{username}/{password}")
    Call<ResponseBody> loginWithCredentials(@Path("username") String username, @Path("password") String password);

    @GET("addUser/{user}")
    Call<ResponseBody> createAccount(@Path("user") String user);

    @GET("updateUser/{user}")
    Call<ResponseBody> updateUser(@Path("user") User user);

    //-------------- Shelter ----------------------------
    @GET("shelterAll")
    Call<ResponseBody> getShelterList();

    @GET("shelterByID/{idOfShelter}")
    Call<ResponseBody> getShelterById(@Path("idOfShelter") String idOfShelter);

    @GET("addShelter/{shelter}")
    Call<ResponseBody> addShelter(@Path("shelter") String shelter);

    @GET("updateShelter/{shelter}")
    Call<ResponseBody> updateShelter(@Path("shelter") String shelterUrl);

    @GET("deleteShelter/{shelter}")
    Call<ResponseBody> deleteShelter(@Path("shelter") String shelterUrl);

    //--------------- Announcement ------------------------------
    @GET("announcementsAll")
    Call<ResponseBody> getListOfNews();

    @GET("addAnnouncement/{announcement}")
    Call<ResponseBody> addNews(@Path("announcement") String announcement);

    @GET("updateAnnouncement/{announcement}")
    Call<ResponseBody> updateNews(@Path("announcement") String announcementUrl);

    @GET("deleteAnnouncement/{announcement}")
    Call<ResponseBody> deleteNews(@Path("announcement") String announcementToJson);

    //------------------Dog ------------------------------------------
    @GET("dogAll")
    Call<ResponseBody> getDogList();

    @GET("addDog/{dog}")
    Call<ResponseBody> addDog(@Path("dog") String dog);

    @GET("updateDog/{dog}")
    Call<ResponseBody> updateDog(@Path("dog") String dogUrl);

    @GET("deleteDog/{dog}")
    Call<ResponseBody> deleteDog(@Path("dog") String dogToJson);
}
