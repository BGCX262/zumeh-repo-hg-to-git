package com.es.zumeh.server.model;
import java.util.ArrayList;
import java.util.List;

import com.es.zumeh.client.facade.UserService;
import com.es.zumeh.client.model.user.User;
import com.es.zumeh.server.persistence.ZumehDAOFactory;
import com.es.zumeh.server.persistence.ZumehDAOFactory.UserDAO;
import com.es.zumeh.server.persistence.ZumehDAOFactoryImpl;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;


@SuppressWarnings("serial")
public class UserServiceImpl extends RemoteServiceServlet implements
		UserService {
	

	private static final long serialVersionUID = 1L;

	private List<User> userList = new ArrayList<User>();

	public UserServiceImpl(User user) {
		userList.add(user);
	}

	public User getUser(String id) {

		for (Object object : userList) {
			if (((User) object).getId().equals(id))
				return ((User) object);
		}
		return null;
	}
	
	public User addUser(User user) {
		userList.add(user);
		
		ZumehDAOFactory factory = ZumehDAOFactoryImpl.sharedSessionFactory();
		UserDAO userDAO = factory.getUserDAO();
		userDAO.makePersistent(user);
		return user;
	}

	public List<User> getUserList() {
		return userList;
	}
}
