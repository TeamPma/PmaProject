package com.example.maja.myapplication.backend.entity;

import java.io.Serializable;

/**
 * Created by Jovana on 3.9.2017..
 */

public class Shelter implements Serializable{

    private int idShelter;
    private String name;
    private String address;
    private String number;
    private String location;
    private String city;
    private int bankAccount;

    public Shelter() {
    }


    public Shelter(int idShelter, String name, String address, String number, String location, String city,
                   int bankAccount) {
        super();
        this.idShelter = idShelter;
        this.name = name;
        this.address = address;
        this.number = number;
        this.location = location;
        this.city = city;
        this.bankAccount = bankAccount;
    }

    public void setIdShelter(int idShelter) {
        this.idShelter = idShelter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setBankAccount(int bankAccount) {
        this.bankAccount = bankAccount;
    }

    public int getIdShelter() {
        return idShelter;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getNumber() {
        return number;
    }

    public String getLocation() {
        return location;
    }

    public String getCity() {
        return city;
    }

    public int getBankAccount() {
        return bankAccount;
    }
}
