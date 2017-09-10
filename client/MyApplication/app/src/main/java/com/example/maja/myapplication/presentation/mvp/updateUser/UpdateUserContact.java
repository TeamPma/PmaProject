package com.example.maja.myapplication.presentation.mvp.updateUser;

import com.example.maja.myapplication.backend.entity.User;

/**
 * Created by Jovana on 10.9.2017..
 */

public class UpdateUserContact {

    public interface View{

        void updateUserNotSuccessfull(String message);
        void updateUserSuccessfull();
    }

    public interface Presenter{

        void updateUser(User user);

    }
}
