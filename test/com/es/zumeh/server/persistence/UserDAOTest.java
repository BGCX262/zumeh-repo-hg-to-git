package com.es.zumeh.server.persistence;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.es.zumeh.client.model.user.User;
import com.es.zumeh.server.persistence.ZumehDAOFactory.UserDAO;

public class UserDAOTest {
	
	UserDAO userDAO;
	
	@Before
	public void setUp() {
		ZumehDAOFactory factory = ZumehDAOFactoryImpl.sharedSessionFactory();
		userDAO = factory.getUserDAO();
	}
	
	@Test
	public void test() {
		User user = new User();
		user.setLogin("login");
		user.setPassword("password");
		
		userDAO.makePersistent(user);
		User userRec = userDAO.findByPK(user.getId());
		Assert.assertNotNull(userRec);
	}

}
