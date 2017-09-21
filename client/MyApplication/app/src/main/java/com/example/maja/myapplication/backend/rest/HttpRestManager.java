package com.example.maja.myapplication.backend.rest;

import android.util.Log;

import com.example.maja.myapplication.backend.bus.SmartBus;
import com.example.maja.myapplication.backend.entity.Dog;
import com.example.maja.myapplication.backend.entity.Shelter;
import com.example.maja.myapplication.backend.entity.Announcement;
import com.example.maja.myapplication.backend.entity.User;
import com.example.maja.myapplication.backend.events.AddDogEvent;
import com.example.maja.myapplication.backend.events.AddNewsEvent;
import com.example.maja.myapplication.backend.events.AddShelterEvent;
import com.example.maja.myapplication.backend.events.CreateAccountEvent;
import com.example.maja.myapplication.backend.events.DeleteDogEvent;
import com.example.maja.myapplication.backend.events.DeleteNewsEvent;
import com.example.maja.myapplication.backend.events.DeleteShelterEvent;
import com.example.maja.myapplication.backend.events.ErrorEvent;
import com.example.maja.myapplication.backend.events.GetAllDogsEvent;
import com.example.maja.myapplication.backend.events.GetAllFavoriteDogsEvent;
import com.example.maja.myapplication.backend.events.GetAllNewsEvent;
import com.example.maja.myapplication.backend.events.GetAllSheltersEvent;
import com.example.maja.myapplication.backend.events.GetShelterByIdEvent;
import com.example.maja.myapplication.backend.events.GetUserByidEvent;
import com.example.maja.myapplication.backend.events.LoginEvent;
import com.example.maja.myapplication.backend.events.UpdateDogEvent;
import com.example.maja.myapplication.backend.events.UpdateNewsEvent;
import com.example.maja.myapplication.backend.events.UpdateShelterEvent;
import com.example.maja.myapplication.backend.events.UpdateUserEvent;
import com.google.gson.Gson;
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
    private static final String favoriteServiceUrl = "http://192.168.0.12:8080/DogAdopter/rest/favoriteDogService/";

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

    public void updateUser(User user) {
        Log.d(TAG, "updateUser: ");
        Retrofit retrofit = getRetrofit(userSericeUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String userSericeUrl = gson.toJson(user);
        iHttpRestManager.updateUser(userSericeUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        EventBus.getDefault().post(new UpdateUserEvent());

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
                        Shelter shelter = gson.fromJson(stringResponse, Shelter.class);
                        SmartBus.getInstance().updateShelterDB(shelter);
                        EventBus.getDefault().post(new UpdateShelterEvent(shelter));

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

    public void deleteShelter(final Shelter shelter) {

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
                        SmartBus.getInstance().deleteShelterDB(shelter.getIdShelter());
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

    //----------------------------Favorite dogs -----------------------------------------------
    public void getFavoriteDogs(int userId) {
        Log.d(TAG, "getFavoriteDogs: ");
        Retrofit retrofit = getRetrofit(favoriteServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);
        iHttpRestManager.getFavoriteDogs(userId+"").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        ArrayList<Dog> dogList = gson.fromJson(stringResponse, new TypeToken<ArrayList<Dog>>(){}.getType());
                        SmartBus.getInstance().insertFavoriteDogs(dogList);
                        EventBus.getDefault().post(new GetAllFavoriteDogsEvent());
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
                        SmartBus.getInstance().insertAllDogsDB(dogList);
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

    public void addDog(final Dog dog) {
        Log.d(TAG, "addDog: ");
        Retrofit retrofit = getRetrofit(dogServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String dogToJson = gson.toJson(dog);
        Log.d(TAG, "addDog: "+ dogToJson);
        iHttpRestManager.addDog(dogToJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Dog dogFromJson = gson.fromJson(stringResponse, Dog.class);
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

    public void updateDog(Dog dog) {
        Log.d(TAG, "updateDog: ");
        Retrofit retrofit = getRetrofit(dogServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String dogUrl = gson.toJson(dog);
        Log.d(TAG, "updateDog: "+ dogUrl);
        iHttpRestManager.updateDog(dogUrl).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        Dog dog = gson.fromJson(stringResponse, Dog.class);
                        SmartBus.getInstance().updateDogDB(dog);
                        EventBus.getDefault().post(new UpdateDogEvent(dog));

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
                        SmartBus.getInstance().insertAllNewsDB(news);
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
                        Announcement announcement = gson.fromJson(stringResponse, Announcement.class);
                        SmartBus.getInstance().updateNewsDB(announcement);
                        EventBus.getDefault().post(new UpdateNewsEvent(announcement));

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
    

    public void deleteNews(final Announcement announcement) {

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
                        SmartBus.getInstance().delteAnnouncementDB(announcement.getIdAnnouncement());
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

    public void deleteDog(final Dog dog) {
        Log.d(TAG, "deleteDog: ");
        Retrofit retrofit = getRetrofit(dogServiceUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String dogToJson = gson.toJson(dog);
        iHttpRestManager.deleteDog(dogToJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        SmartBus.getInstance().deleteDogDB(dog.getDogId());
                        EventBus.getDefault().post(new DeleteDogEvent());

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

    public void getUserById(int userId) {
        Log.d(TAG, "getUserById: ");
        Retrofit retrofit = getRetrofit(userSericeUrl);
        iHttpRestManager = retrofit.create(IHttpRestManager.class);

        String userJson = gson.toJson(userId);
        iHttpRestManager.getUserById(userJson).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d("TAG",response.code()+"");
                if (response.isSuccessful()) {
                    try {
                        String stringResponse = response.body().string();
                        Log.d(TAG, "onResponse: " + stringResponse);
                        User user = gson.fromJson(stringResponse,User.class);
                        Log.d(TAG, "onResponse: "+ user);
                        EventBus.getDefault().post(new GetUserByidEvent(user));

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
}
