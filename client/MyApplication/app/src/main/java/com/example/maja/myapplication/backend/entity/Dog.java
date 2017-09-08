package com.example.maja.myapplication.backend.entity;

import java.io.Serializable;

/**
 * Created by Jovana on 3.9.2017..
 */

public class Dog implements Serializable{

    private int dogId;
    private String name;
    private String bread;
    private int gender;
    private int age;
    private double weight;
    private double height;
    private int isSterilized;
    private int isMarked;
    private String anamnesis;
    private int idShelter;

    public Dog(int dogId, String name, String bread, int gender, int age, double weight, double height, int isSterilized, int isMarked, String anamnesis, int idShelter) {
        this.dogId = dogId;
        this.name = name;
        this.bread = bread;
        this.gender = gender;
        this.age = age;
        this.weight = weight;
        this.height = height;
        this.isSterilized = isSterilized;
        this.isMarked = isMarked;
        this.anamnesis = anamnesis;
        this.idShelter = idShelter;
    }

    public int getDogId() {
        return dogId;
    }

    public String getName() {
        return name;
    }

    public String getBread() {
        return bread;
    }

    public int getGender() {
        return gender;
    }

    public int getAge() {
        return age;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public int getIsSterilized() {
        return isSterilized;
    }

    public int getIsMarked() {
        return isMarked;
    }

    public String getAnamnesis() {
        return anamnesis;
    }

    public int getIdShelter() {
        return idShelter;
    }
}
