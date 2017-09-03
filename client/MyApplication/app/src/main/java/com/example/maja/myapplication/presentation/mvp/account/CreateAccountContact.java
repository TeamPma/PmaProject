package com.example.maja.myapplication.presentation.mvp.account;

import com.example.maja.myapplication.backend.entity.User;

/**
 * Created by Maja on 3.9.2017.
 */

public class CreateAccountContact {

    public interface View{

        void createAccountSuccessfull();
        void createAccountUnsuccessfull(String messsage);
    }

    public interface Presenter{

        void createAccount(User user);
    }
}
