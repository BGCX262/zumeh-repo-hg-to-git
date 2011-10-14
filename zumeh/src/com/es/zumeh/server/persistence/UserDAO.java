package com.es.zumeh.server.persistence;

import com.es.zumeh.client.model.user.User;

public class UserDAO extends GenericDAOImpl<User, Long>{

	public void saveUser(String login, String password) {
		User u = new User();
		u.setLogin(login);
		u.setPassword(password);
		
		makePersistent(u);
	}
	
}
