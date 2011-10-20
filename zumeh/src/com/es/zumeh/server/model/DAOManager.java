package com.es.zumeh.server.model;

import com.es.zumeh.server.persistence.HibernateUtil;
import com.es.zumeh.server.persistence.UserDAO;

public class DAOManager {
	
	private UserDAO userDAO;
	
	private static final String HIBERNATE_CFG_XML = "/com/es/zumeh/server/hibernate.cfg.xml";
	
	public DAOManager() {
		HibernateUtil.setUp(HIBERNATE_CFG_XML);
		userDAO = new UserDAO();
		
	}
	
	public boolean addUser(String login, String password, String email,
			String name, String whoAreYou, String interestedArea,
			String gender, String location, String birthday) {
		boolean addUser = false;
		
		try {
			addUser = userDAO.addUser(login, password, email, name, whoAreYou,
					interestedArea, gender, location, birthday);
		} catch (Exception e) {
			HibernateUtil.rollbackAndCloseSession();
		}
		
		return addUser; 
	}

}
