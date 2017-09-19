package com.dogAdopter.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;

@Entity
@Table(name = "Favorite_dogs", catalog = "mydb")
@NamedQueries({
		@NamedQuery(name = FavoriteDog.FIND_ALL_FAVORITE_DOGS_BY_USER_ID, query = "FROM FavoriteDog dog WHERE dog.idUser = :idUser") })
public class FavoriteDog implements Serializable {

	public final static String FIND_ALL_FAVORITE_DOGS_BY_USER_ID = "findAllFDogsByUserID";

	private int id;
	private int dogId;
	private int idUser;

	public FavoriteDog() {
		super();
	}

	public FavoriteDog(int dogId, int idUser) {
		super();
		this.dogId = dogId;
		this.idUser = idUser;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "favoriteId", unique = true, nullable = false)
	public int getId() {
		return id;
	}

	@Column(name = "dogId", unique = false, nullable = false, length = 11)
	public int getDogId() {
		return dogId;
	}

	@Column(name = "userId", unique = false, nullable = false, length = 11)
	public int getIdUser() {
		return idUser;
	}

	public void setDogId(int dogId) {
		this.dogId = dogId;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	public void setId(int id) {
		this.id = id;
	}

}
