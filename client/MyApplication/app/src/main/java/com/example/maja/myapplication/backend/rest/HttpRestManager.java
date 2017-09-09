package com.example.maja.myapplication.backend.rest;

import android.util.Log;

import com.example.maja.myapplication.backend.bus.SmartBus;
import com.example.maja.myapplication.backend.database.DatabaseManager;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.events.AddDogEvent;
import com.example.maja.myapplication.backend.events.AddNewsEvent;
import com.example.maja.myapplication.backend.events.AddShelterEvent;
import com.example.maja.myapplication.backend.events.CreateAccountEvent;
import com.example.maja.myapplication.backend.events.DeleteNewsEvent;
import com.example.maja.myapplication.backend.events.DeleteShelterEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.GetAllDogsEvent;
import com.example.maja.myapplication.backend.events.GetAllNewsEvent;
import com.example.maja.myapplication.backend.events.GetAllSheltersEvent;
import com.example.maja.myapplication.backend.events.GetShelterByIdEvent;
import com.example.maja.myapplication.backend.events.LoginEvent;
import com.example.maja.myapplication.backend.events.UpdateNewsEvent;
import com.example.maja.myapplication.backend.events.UpdateShelterEvent;
import com.google.gson.Gson;
import com.google.gson.internal.bind.ArrayTypeAdapter;
import com.google.gson.reflect.TypeToken;

import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;

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

    private static final String userSericeUrl = "http://192.168.0.12:8080/DogAdopter/rest/userservice/";
    private static final String announcementsServiceUrl = "http://192.168.0.12:8080/DogAdopter/rest/announcementService/";
    private static final String shelterServiceUrl = "http://192.168.0.12:8080/DogAdopter/rest/shelterService/";
    private static final String dogServiceUrl = "http://192.168.0.12:8080/DogAdopter/rest/dogService/";

    private final Gson gson = new Gson();

    public HttpRestManager(){}
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
//----------------------------User ----------------------------------------------------------

    public void login(String username, String password) {
        Log.d(TAG , "login: ");

        Retrofit retrofit = getRetrofit(userSericeUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        Log.d(TAG, "username: "+ username + "    password:   " + password);
        iHttpRestManager.loginWithCredentials(username, password).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d("Response",stringResponse +"");

                        if(stringResponse.equals("NotExist")){
                            Log.d("Response","User not exist");
                            EventBus.getDefault().post(new ErrorEvent("Username or password are not correct."));
                        }
                        else
                        {
                            Log.d(TAG, "onResponse: Login is succesfull");
                            User user = gson.fromJson(stringResponse, User.class);
                            EventBus.getDefault().post(new LoginEvent(user));
                        }
                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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

        Retrofit retrofit = getRetrofit(userSericeUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String userJson = gson.toJson(user);
        iHttpRestManager.createAccount(userJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        EventBus.getDefault().post(new CreateAccountEvent());

                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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

    //----------------------------Shelter ----------------------------------------------------------

    public void getShelterList(){
        Log.d(TAG, "getShelterList: ");

        Retrofit retrofit = getRetrofit(shelterServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        iHttpRestManager.getShelterList().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        ArrayList<Shelter> shelterList = gson.fromJson(stringResponse, new TypeToken<ArrayList<Shelter>>(){}.getType());
                        SmartBus.getInstance().insertAllShelters(shelterList);
                        EventBus.getDefault().post(new GetAllSheltersEvent(shelterList));
                        Log.d(TAG, "onResponse: " + stringResponse);
                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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

    public void getShelterById(int shelterId) {
        Log.d(TAG, "getShelterById: ");

        Retrofit retrofit = getRetrofit(shelterServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String shelteridToJson = gson.toJson(shelterId);
        iHttpRestManager.getShelterById(shelteridToJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Shelter shelter = gson.fromJson(stringResponse, Shelter.class);
                        EventBus.getDefault().post(new GetShelterByIdEvent(shelter));
                        Log.d(TAG, "onResponse: " + stringResponse);
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

    public void addShelter(Shelter shelter) {
        Log.d(TAG, "addShelter: ");
        Retrofit retrofit = getRetrofit(shelterServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String shelterToJson = gson.toJson(shelter);
        iHttpRestManager.addShelter(shelterToJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        EventBus.getDefault().post(new AddShelterEvent());

                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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

    //----------------------------Dog----------------------------------------------------------

    public void getDogList() {
        Log.d(TAG, "getDogList: ");

        Retrofit retrofit = getRetrofit(dogServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        iHttpRestManager.getDogList().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        ArrayList<Dog> dogList = gson.fromJson(stringResponse, new TypeToken<ArrayList<Dog>>(){}.getType());
                        EventBus.getDefault().post(new GetAllDogsEvent(dogList));

                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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

    public void addDog(Dog dog) {
        Log.d(TAG, "addDog: ");
        Retrofit retrofit = getRetrofit(dogServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String dogToJson = gson.toJson(dog);
        iHttpRestManager.addDog(dogToJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        EventBus.getDefault().post(new AddDogEvent());

                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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

    //----------------------------Announcements ----------------------------------------------------------
    public void getAllNews() {
        Log.d(TAG, "getAllNews: ");

        Retrofit retrofit = getRetrofit(announcementsServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);
        iHttpRestManager.getListOfNews().enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        ArrayList<Announcement> news = gson.fromJson(stringResponse, new TypeToken<ArrayList<Announcement>>(){}.getType());
                        SmartBus.getInstance().insertAllNews(news);
                        EventBus.getDefault().post(new GetAllNewsEvent());
                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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


    public void addNews(Announcement announcement) {

        Log.d(TAG, "addNews: ");
        Retrofit retrofit = getRetrofit(announcementsServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String announcementToJson = gson.toJson(announcement);
        iHttpRestManager.addNews(announcementToJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        EventBus.getDefault().post(new AddNewsEvent());

                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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

    public void updateNews(Announcement announcement) {
        Log.d(TAG, "updateNews: ");
        Retrofit retrofit = getRetrofit(announcementsServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String announcementUrl = gson.toJson(announcement);
        iHttpRestManager.updateNews(announcementUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        EventBus.getDefault().post(new UpdateNewsEvent());

                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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
    

    public void deleteNews(Announcement announcement) {

        Log.d(TAG, "deleteNews: ");
        Retrofit retrofit = getRetrofit(announcementsServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String announcementToJson = gson.toJson(announcement);
        iHttpRestManager.deleteNews(announcementToJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                         EventBus.getDefault().post(new DeleteNewsEvent());

                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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

    public void updateShelter(Shelter shelter) {
        Log.d(TAG, "updateShelter: ");
        Retrofit retrofit = getRetrofit(shelterServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String shelterUrl = gson.toJson(shelter);
        iHttpRestManager.updateShelter(shelterUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        EventBus.getDefault().post(new UpdateShelterEvent());

                    } catch (IOException e) {
                        Log.d("exception",e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
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

    public void deleteShelter(Shelter shelter) {

        Log.d(TAG, "deleteShelter: ");
        Retrofit retrofit = getRetrofit(shelterServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String shelterToJson = gson.toJson(shelter);
        iHttpRestManager.deleteShelter(shelterToJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG", response.code() + "");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        EventBus.getDefault().post(new DeleteShelterEvent());

                    } catch (IOException e) {
                        Log.d("exception", e.getMessage());
                        EventBus.getDefault().post(new ErrorEvent(e.getMessage()));
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("Failure", t.getMessage());
                EventBus.getDefault().post(new ErrorEvent(t.getMessage()));
            }
        });
    }
}
