package com.example.maja.myapplication.backend.rest;

import android.util.Log;

import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.events.CreateAccountEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.LoginEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Maja on 25.8.2017.
 */

public class HttpRestManager  {

    private static final String TAG = "HttpRestManager";

    public HttpRestManager(){}
    public String result;

    private IHttpRestManager iHttpRestManager ;

    public Retrofit getRetrofit(String url){

        Log.d(TAG, "getRetrofit: ");
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url)
                .build();
        return retrofit;

    }

    public void login(String username, String password) {
        Log.d(TAG , "login: ");

        String url="http://192.168.0.12:8080/DogAdopter/rest/userservice/";
        Retrofit retrofit = getRetrofit(url);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);
        Log.d(TAG, "username: "+ username + "    password:   " + password);
        iHttpRestManager.loginWithCredentials(username, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        // get String from response
                        String stringResponse = response.body().string();

                        Log.d("Response",stringResponse +"");
                        result = stringResponse;
                        if(result.equals("NotExist")){
                            Log.d("Response","User not exist");
                            EventBus.getDefault().post(new ErrorEvent("Username or password are not correct."));
                        }
                        else
                        {
                            Log.d(TAG, "onResponse: Login is succesfull");
                            Gson gson = new Gson();
                            User user = gson.fromJson(stringResponse, User.class);
                            Log.d("User", user.getUsername() + "   " + user.getPassword());
                            EventBus.getDefault().post(new LoginEvent(user));
                        }
                        // Do whatever you want with the String
                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Failure",t.getMessage());
                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
            }
        });
    }

    public void createAccount(User user) {
        Log.d(TAG, "createAccount: ");
        String url="http://192.168.0.12:8080/DogAdopter/rest/userservice/";
        Retrofit retrofit = getRetrofit(url);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        Log.d(TAG, "username:" + user.getUsername());
        Gson gson = new Gson();
        String userJson = gson.toJson(user);
        Log.d(TAG, "user: " + userJson);
        iHttpRestManager.createAccount(userJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        if(stringResponse.equals("NotSave")){
                            Log.d("Response","User not saved");
                            EventBus.getDefault().post(new ErrorEvent("Problem at server. User is not saved."));
                        }else {
                            Log.d("Response", "User is saved:");
                            EventBus.getDefault().post(new CreateAccountEvent());
                        }
                        // Do whatever you want with the String
                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Failure",t.getMessage());
                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
            }
        });
    }

    public void getAllNews() {
        // Need to be implemented
    }
    public void getShelterList(){
        Log.d(TAG, "getShelterList: ");
        String url="http://192.168.0.12:8080/DogAdopter/rest/shelterService/";
        Retrofit retrofit = getRetrofit(url);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        Gson gson = new Gson();

        iHttpRestManager.getShelterList().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        // Do whatever you want with the String
                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Failure",t.getMessage());
                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
            }
        });
    }
}
