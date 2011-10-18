package com.es.zumeh.server.persistence;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.es.zumeh.server.model.DAOManager;
import com.es.zumeh.server.model.persistence.User;

public class UserDAOTest {
	
	DAOManager daoManager;
	
	@Before
	public void setUp() {
		daoManager = new DAOManager();
	}
	
	@Test
	public void test() {
		User user = new User();
		user.setLogin("teste");
		user.setPassword("leite");
		user.setBirthday("27/12/1987");
		user.setEmail("tiagooleite@gmail.com");
		user.setWhoAreYou("um cara");
		user.setGender("M");
		user.setLocation("Pesqueira");
		user.setName("PESAO DA SILVA");
		
		boolean result = false;
		
		
		
		result = daoManager.addUser(user.getLogin(), user.getPassword(), user.getEmail(),
				user.getEmail(), user.getWhoAreYou(), user.getInterestedArea(), user.getGender(), user.getLocation(), user.getBirthday());
		Assert.assertEquals(true, result);
	}

}
