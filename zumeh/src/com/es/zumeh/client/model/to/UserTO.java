package com.es.zumeh.client.model.to;

import java.io.Serializable;

import com.es.zumeh.client.model.Password;

public class UserTO implements Serializable{

	private static final long serialVersionUID = 5958457385870267972L;
	
	private String login;
	private String password;
	private String email;
	private String name;
	private String birthday;
	private String whoAreYou;
	private String interestedArea;
	private String gender;
	private String location;

	public UserTO() {}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) { //TODO retirar isso daqui
		Password pass = new Password();
		try {
			pass.setPassword(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.password = pass.getPassword();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getWhoAreYou() {
		return whoAreYou;
	}

	public void setWhoAreYou(String whoAreYou) {
		this.whoAreYou = whoAreYou;
	}

	public String getInterestedArea() {
		return interestedArea;
	}

	public void setInterestedArea(String interestedArea) {
		this.interestedArea = interestedArea;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}


}
