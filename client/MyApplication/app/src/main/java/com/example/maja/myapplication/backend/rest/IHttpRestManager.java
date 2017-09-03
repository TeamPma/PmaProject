package com.example.maja.myapplication.backend.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Maja on 27.8.2017.
 */

public interface IHttpRestManager {

    @GET("login/{username}/{password}")
    Call<ResponseBody> loginWithCredentials(@Path("username") String username, @Path("password") String password);

    @GET("addUser/{user}")
    Call<ResponseBody> createAccount(@Path("user") String user);

}
