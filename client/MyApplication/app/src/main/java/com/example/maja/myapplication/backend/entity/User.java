package com.example.maja.myapplication.backend.entity;

/**
 * Created by Maja on 27.8.2017.
 */

public class User {


    private int idUser;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String email;
    private String number;
    private String location;

    public User() {
    }

    public User(int idUser, String username, String password, String firstName, String lastName, String email,
                String number, String location) {
        super();
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.location = location;
    }
}
