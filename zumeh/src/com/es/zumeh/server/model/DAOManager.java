package com.es.zumeh.server.model;

import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.server.model.persistence.User;
import com.es.zumeh.server.persistence.HibernateUtil;
import com.es.zumeh.server.persistence.UserDAO;

public class DAOManager {
	
	private UserDAO userDAO;
	
	private static final String HIBERNATE_CFG_XML = "hibernate.cfg.xml";
	
	public DAOManager() {
		HibernateUtil.setUp(HIBERNATE_CFG_XML);
		userDAO = new UserDAO();
		
	}
	
	public boolean addUser(UserTO user) {
		boolean addUser = false;
		
		try {
			addUser = userDAO.addUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			HibernateUtil.rollbackAndCloseSession();
		} finally {
			HibernateUtil.closeSession();
		}
		
		return addUser; 
	}
	
//	public boolean addUser(String login, String password, String email,
//			String name, String whoAreYou, String interestedArea,
//			String gender, String location, String birthday) {
//		boolean addUser = false;
//		
//		try {
//			addUser = userDAO.addUser(login, password, email, name, whoAreYou,
//					interestedArea, gender, location, birthday);
//		} catch (Exception e) {
//			e.printStackTrace();
//			HibernateUtil.rollbackAndCloseSession();
//		} finally {
//			HibernateUtil.closeSession();
//		}
//		
//		return addUser; 
//	}
	
	public void deleteAllUsers() {
		getUserDAO().deleteFromUsers();
	}
	
	public UserTO verifyUserTO(UserTO user) {
		return getUserDAO().verifyUserTO(user);
	}
	
	public boolean verifyUser(User user) {
		return getUserDAO().userExists(user);
	}
	
	public User getUser(String login) {
		return getUserDAO().getUserByLogin(login);
	}
	
	public UserTO getUserByEmail(String email) {
		return getUserDAO().convertToTransferObjectUser(
				getUserDAO().getUserByEmail(email));
	}
	
	public UserDAO getUserDAO() {
		return userDAO;
	}
	
	public UserTO[] getAllUsers() {
		return userDAO.getAllUsers();
	}

	public void deleteUserData() {
		//getUserDAO().deleteUserData();
		
	}
	
	public boolean verifyUserByEmail(String email) {
		return getUserDAO().verifyUserByEmail(email);
	}

}
