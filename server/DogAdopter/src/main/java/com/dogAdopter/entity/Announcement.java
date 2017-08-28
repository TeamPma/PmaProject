package com.dogAdopter.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

@Entity
@NamedQueries({
        @NamedQuery(name = Announcement.GET_ANNOUNCMENT_BY_SHELTER_ID, query = "FROM Announcement ann WHERE ann.idShelter = :id"),
        @NamedQuery(name = Announcement.GET_ANNOUNCMENT_BY_ID, query = "FROM Announcement ann WHERE ann.idAnnouncement = :id")
})
@Table(name = "Announcement", catalog = "mydb")
public class Announcement implements Serializable {

    public static final String GET_ANNOUNCMENT_BY_SHELTER_ID = "getAnnouncementByShelterId";
    public static final String GET_ANNOUNCMENT_BY_ID = "getAnnouncementById";

    private int idAnnouncement;
    private int idShelter;
    private String comment;
    private Date date;
    private String imageUrl;

    public Announcement() {
        super();
    }

    public Announcement(int idAnnouncement, String comment, Date date, int idShelter, String imageUrl) {
        super();
        this.idAnnouncement = idAnnouncement;
        this.comment = comment;
        this.date = date;
        this.idShelter = idShelter;
        this.imageUrl = imageUrl;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "idAnnouncement", unique = true, nullable = false)
    public int getIdAnnouncement() {
        return idAnnouncement;
    }

    public void setIdAnnouncement(int idAnnouncement) {
        this.idAnnouncement = idAnnouncement;
    }

    @Column(name = "comment", unique = false, nullable = false, length = 512)
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Column(name = "date", unique = false, nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name = "idShelter", unique = true, nullable = false, length = 11)
    public int getIdShelter() {
        return idShelter;
    }

    public void setIdShelter(int idShelter) {
        this.idShelter = idShelter;
    }

    @Column(name = "url", unique = true, nullable = true, length = 128)
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
