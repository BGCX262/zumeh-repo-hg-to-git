package com.es.zumeh.server.model;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.server.model.persistence.User;
import com.es.zumeh.server.persistence.UserDAO;
import com.es.zumeh.server.persistence.ZumehDAOFactory;
import com.es.zumeh.server.persistence.ZumehDAOFactoryImpl;
import com.es.zumeh.server.util.SendMail;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.Feed;
import com.google.gdata.data.Person;
import com.google.gdata.util.ServiceException;

public class SessionManager {
	
	private static SessionManager thisInstance;
	private HashMap<String, User> openedSessions;
	private ZumehDAOFactory factory;
	private UserDAO userDAO;
	private DAOManager daoManager;
	private SendMail sendEmail;
	private boolean oldUser;
	
	
	private SessionManager() {
		daoManager = new DAOManager();
		openedSessions = new HashMap<String, User>();
		factory = ZumehDAOFactoryImpl.sharedSessionFactory();
		//userDAO = factory.getUserDAO();
		userDAO = new UserDAO();
		sendEmail = new SendMail();
	}
	
	public User getLoggedUser(String token) {
		User user = null;
		user = openedSessions.get(token);
		return user;
	}
	
	public void openSession(String token) {
		try {
			oldUser = false;
			openedSessions.put(token, getUser(token));
			oldUser = verifyUser(getUser(token));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeSession(String token) {
		/* TODO not completely implemented, it must revoke token before remove
			from map. */
		openedSessions.remove(token);
	}

	public static SessionManager getInstance() {
		if(thisInstance == null) {
			thisInstance = new SessionManager();
		}
		
		return thisInstance;
	}

	private User getUser(String token) throws IOException, ServiceException {
		// TODO This is temporary.
		User user = new User();
		
		GoogleService googleService = new GoogleService("cp", "oauth-sample-app");
		googleService.setAuthSubToken(token);
		
		URL feedUrl = new URL("http://www.google.com/m8/feeds/contacts/default/base");
		
		BaseFeed resultFeed = googleService.getFeed(feedUrl, Feed.class);
		//System.out.println("getTitle: " + resultFeed.getTitle().getPlainText());
		List<Person> teste = resultFeed.getAuthors();
		
		Person person = teste.get(0);
		
		user.setName(person.getName());
		user.setEmail(person.getEmail());
		
		System.out.println("ENTROU");
		
		return user;
	}
	
	private boolean verifyUser(User user) {
		return getDaoManager().verifyUser(user);
	}

//	public boolean addUser(UserTO user) {
//		return daoManager.addUser(user.getLogin(), user.getPassword(), user.getEmail(),
//				user.getEmail(), user.getWhoAreYou(), user.getInterestedArea(), user.getGender(), user.getLocation(), user.getBirthday());
//	}

	private User convertToRealUser(UserTO user) {
		User newUser = new User();
		user.setLogin(user.getLogin());
		user.setPassword(user.getPassword());
		user.setEmail(user.getEmail());
		user.setBirthday(user.getBirthday());
		user.setGender(user.getGender());
		user.setInterestedArea(user.getInterestedArea());
		user.setLocation(user.getLocation());
		user.setName(user.getName());
		user.setWhoAreYou(user.getWhoAreYou());
		return newUser;
	}
	
	
	
	public DAOManager getDaoManager() {
		return daoManager;
	}
	
	public void sendMail(String from, String to, String subject, String message) {
		sendEmail.sendMail(from, to, subject, message);
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}
}

