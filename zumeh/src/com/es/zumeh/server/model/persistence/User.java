package com.es.zumeh.server.model.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable {
	
	private static final long serialVersionUID = 6974269351580312846L;
	
	public User() {}
	
	private Long id;
	private String login;
	private String password;
	private String email;
	private String name;
	private String birthday;
	private String whoAreYou;
	private String interestedArea;
	private String gender;
	private String location;

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(length = 50)
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Column(length = 50)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(length = 50)
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Column(length = 50)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(length = 50)
	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	@Column(length = 50)
	public String getWhoAreYou() {
		return whoAreYou;
	}

	public void setWhoAreYou(String whoAreYou) {
		this.whoAreYou = whoAreYou;
	}

	@Column(length = 50)
	public String getInterestedArea() {
		return interestedArea;
	}

	public void setInterestedArea(String interestedArea) {
		this.interestedArea = interestedArea;
	}

	@Column(length = 50)
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	@Column(length = 50)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

}
