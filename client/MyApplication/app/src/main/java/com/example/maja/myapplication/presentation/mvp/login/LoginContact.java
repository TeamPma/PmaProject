package com.example.maja.myapplication.presentation.mvp.login;

/**
 * Created by Maja on 24.8.2017.
 */

public class LoginContact {

    public interface View{

        void loginSuccesfull();
    }

    public interface Presenter{

        void login(String username, String password);
    }
}
