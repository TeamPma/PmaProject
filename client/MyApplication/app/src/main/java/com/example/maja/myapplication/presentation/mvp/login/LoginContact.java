package com.example.maja.myapplication.presentation.mvp.login;

import com.example.maja.myapplication.backend.entity.User;

/**
 * Created by Maja on 24.8.2017.
 */

public class LoginContact {

    public interface View{

        void loginSuccesfull(User user);

        void loginNotSuccesfull(String message);
    }

    public interface Presenter{

        void login(String username, String password);
    }
}
