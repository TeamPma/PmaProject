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
@NamedQueries({
@NamedQuery(name = Dog.FIND_ALL_DOGS, query = "FROM Dog dog"),
@NamedQuery(name = Dog.FIND_DOGS_BY_ID_OF_SHELTER, query = "FROM Dog dog WHERE dog.idShelter = :idShelter")
})
@Table(name = "Dog", catalog = "mydb")
public class Dog implements Serializable{

	public final static String FIND_ALL_DOGS = "findAllDogs";
	public final static String FIND_DOGS_BY_ID_OF_SHELTER = "findDogsByIdOfShelter";
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
	
	
	
	public Dog() {
		super();
	}


	public Dog(int dogId, String name, String bread, int gender, int age, double weight, double height,
			int isSterilized, int isMarked, String anamnesis, int idShelter) {
		super();
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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "dogId", unique = true, nullable = false)
	public int getDogId() {
		return dogId;
	}


	public void setDogId(int dogId) {
		this.dogId = dogId;
	}

	@Column(name = "name", unique = false, nullable = false, length = 45)
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "bread", unique = false, nullable = false, length = 45)
	public String getBread() {
		return bread;
	}


	public void setBread(String bread) {
		this.bread = bread;
	}

	@Column(name = "gender", unique = false, nullable = false, length = 11)
	public int getGender() {
		return gender;
	}


	public void setGender(int gender) {
		this.gender = gender;
	}

	@Column(name = "age", unique = false, nullable = false, length = 11)
	public int getAge() {
		return age;
	}


	public void setAge(int age) {
		this.age = age;
	}

	@Column(name = "weight", unique = false, nullable = false, length = 10)
	public double getWeight() {
		return weight;
	}


	public void setWeight(double weight) {
		this.weight = weight;
	}

	@Column(name = "height", unique = false, nullable = false, length = 10)
	public double getHeight() {
		return height;
	}


	public void setHeight(double height) {
		this.height = height;
	}

	@Column(name = "sterilizied", unique = false, nullable = false, length = 1)
	public int getIsSterilized() {
		return isSterilized;
	}


	public void setIsSterilized(int isSterilized) {
		this.isSterilized = isSterilized;
	}

	@Column(name = "marked", unique = false, nullable = false, length = 1)
	public int getIsMarked() {
		return isMarked;
	}


	public void setIsMarked(int isMarked) {
		this.isMarked = isMarked;
	}

	@Column(name = "anamnesis", unique = false, nullable = false, length = 128)
	public String getAnamnesis() {
		return anamnesis;
	}


	public void setAnamnesis(String anamnesis) {
		this.anamnesis = anamnesis;
	}

	@Column(name = "idShelter", unique = true, nullable = false, length = 11)
	public int getIdShelter() {
		return idShelter;
	}


	public void setIdShelter(int idShelter) {
		this.idShelter = idShelter;
	}
	
	
	
	
}
