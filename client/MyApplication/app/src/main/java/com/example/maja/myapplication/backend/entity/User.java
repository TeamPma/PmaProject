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
    private int isAdmin;

    public User() {
    }

    public User(int idUser, String username, String password, String firstName, String lastName, String email,
                String number, int isAdmin) {
        super();
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.isAdmin = isAdmin;
    }

    public User(String username, String password, String firstName, String lastName, String email, String number, int isAdmin) {
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.number = number;
        this.isAdmin = isAdmin;
    }

    public int getIdUser() {
        return idUser;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getNumber() {
        return number;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int isAdmin() {
        return isAdmin;
    }

    public void setAdmin(int admin) {
        isAdmin = admin;
    }
}
