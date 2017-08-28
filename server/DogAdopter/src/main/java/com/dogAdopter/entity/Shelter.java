package com.dogAdopter.entity;

import java.io.Serializable;
import javax.persistence.*;

import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;


import static javax.persistence.GenerationType.IDENTITY;


@Entity
@NamedQueries({
        @NamedQuery(name = Shelter.GET_ALL, query = "FROM Shelter shelter"),
        @NamedQuery(name = Shelter.GET_SHELTER_BY_ID, query = "FROM Shelter shelter WHERE shelter.idShelter = :id"),
})
@Table(name = "Shelter", catalog = "mydb")
public class Shelter implements Serializable {

    public final static String GET_ALL = "getAllShelters";
    public final static String GET_SHELTER_BY_ID = "getShelterById";
    private int idShelter;
    private String name;
    private String address;
    private String number;
    private String location;
    private String city;
    private int bankAccount;


    public Shelter() {
        super();
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

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idShelter", unique = true, nullable = false)
    public int getIdShelter() {
        return idShelter;
    }


    public void setIdShelter(int idShelter) {
        this.idShelter = idShelter;
    }

    @Column(name = "name", unique = true, nullable = false, length = 45)
    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "address", unique = true, nullable = false, length = 128)
    public String getAddress() {
        return address;
    }


    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "phone", unique = true, nullable = false, length = 45)
    public String getNumber() {
        return number;
    }


    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "location", unique = true, nullable = true, length = 45)
    public String getLocation() {
        return location;
    }


    public void setLocation(String location) {
        this.location = location;
    }

    @Column(name = "city", unique = false, nullable = true, length = 45)
    public String getCity() {
        return city;
    }


    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "bankAccount", unique = true, nullable = false, length = 45)
    public int getBankAccount() {
        return bankAccount;
    }


    public void setBankAccount(int bankAccount) {
        this.bankAccount = bankAccount;
    }


}
