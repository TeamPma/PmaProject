package com.example.maja.myapplication.backend.entity;

/**
 * Created by Jovana on 3.9.2017..
 */

public class Shelter {

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
