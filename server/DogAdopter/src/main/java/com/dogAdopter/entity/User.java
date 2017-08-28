package com.dogAdopter.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.Table;

import javax.persistence.NamedQuery;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@NamedQueries({
		@NamedQuery(name = User.FIND_USER_WITH_USERNAME_AND_PASSWORD, query = "FROM User user WHERE user.username = :username AND user.password = :password"),
		@NamedQuery(name = User.FIND_USER_WITH_ID, query = "FROM User user WHERE user.idUser = :id"),
		@NamedQuery(name = User.FIND_ALL_USERS, query = "FROM User user")
})
@Table(name = "User", catalog = "mydb")
public class User implements Serializable {

	public final static String FIND_USER_WITH_USERNAME_AND_PASSWORD = "findUserWithUsernameAndPassword";
	public final static String FIND_USER_WITH_ID = "findUserWithId";
	public static final String FIND_ALL_USERS = "findAllUser";

	private int idUser;
	private String username;
	private String password;
	private String firstName;
	private String lastName;
	private String email;
	private String number;
	private String location;

	public User() {
		// super();
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

	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "idUser", unique = true, nullable = false)

	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}

	@Column(name = "username", unique = true, nullable = false, length = 45)
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Column(name = "password", unique = true, nullable = false, length = 45)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "firstName", unique = false, nullable = false, length = 45)
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "lastName", unique = false, nullable = false, length = 45)
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "email", unique = false, nullable = true, length = 45)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(name = "phone", unique = false, nullable = true, length = 45)
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@Column(name = "location", unique = false, nullable = true, length = 45)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
