package com.example.maja.myapplication.backend.rest;

import android.util.Log;

import com.example.maja.myapplication.backend.bus.SmartBus;

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

    public void login(String username, String password) {
        Log.d(TAG , "login: ");

        String url="http://192.168.0.12:8080/DogAdopter/rest/userservice/";

        Retrofit retrofit = getRetrofit(url);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);
        Log.d(TAG, "username: "+ username + "    password:   " + password);
        Call<ResponseBody> call = iHttpRestManager.loginWithCredentials(username, password);
        Log.d(TAG, "call:" + call);
        String resultOfConnection = connectOnServer(call);
        Log.d(TAG, "resultOfConnection:" + resultOfConnection);
    }

    public String connectOnServer(Call<ResponseBody> call){
        Log.d(TAG, "connectOnServer: ");
         call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        // get String from response
                        String stringResponse = response.body().string();
                        Log.d("Response",stringResponse +"");
                        result = stringResponse;
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
            }
        });
        return result;
    }


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
    //ovde implementiras retrofit
}
