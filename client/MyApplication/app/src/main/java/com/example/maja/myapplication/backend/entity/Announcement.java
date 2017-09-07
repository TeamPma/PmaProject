package com.example.maja.myapplication.backend.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Maja on 4.9.2017.
 */

public class Announcement implements Serializable{

    private int idAnnouncement;
    private int idShelter;
    private String comment;
    private Date date;
    private String title;

    public Announcement() {
    }

    public Announcement(int idShelter, String comment, Date date, String title) {
        this.idShelter = idShelter;
        this.comment = comment;
        this.date = date;
        this.title = title;
    }

    public Announcement(int idAnnouncement, int idShelter, String comment, Date date, String title) {
        this.idAnnouncement = idAnnouncement;
        this.idShelter = idShelter;
        this.comment = comment;
        this.date = date;
        this.title = title;
    }

    public int getIdAnnouncement() {
        return idAnnouncement;
    }

    public void setIdAnnouncement(int idAnnouncement) {
        this.idAnnouncement = idAnnouncement;
    }

    public int getIdShelter() {
        return idShelter;
    }

    public void setIdShelter(int idShelter) {
        this.idShelter = idShelter;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
