package com.es.zumeh.client.control;

import com.es.zumeh.client.model.to.UserTO;

public class ClientSessionManager {
	
	private UserTO userOwner;
	
	private UserTO userFriend;

	public UserTO getUserOwner() {
		return userOwner;
	}

	public void setUserOwner(UserTO userOwner) {
		this.userOwner = userOwner;
	}

	public UserTO getUserFriend() {
		return userFriend;
	}

	public void setUserFriend(UserTO userFriend) {
		this.userFriend = userFriend;
	}
	
	

}
