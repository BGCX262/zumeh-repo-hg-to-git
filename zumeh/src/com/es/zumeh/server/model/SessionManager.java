package com.es.zumeh.server.model;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.es.zumeh.server.model.persistence.User;
import com.es.zumeh.server.util.SendMail;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.Feed;
import com.google.gdata.data.Person;
import com.google.gdata.util.ServiceException;

public class SessionManager {
	
	private static SessionManager thisInstance;
	private HashMap<String, User> openedSessions;
	private DAOManager daoManager;
	private SendMail sendEmail;
	
	
	private SessionManager() {
		daoManager = new DAOManager();
		openedSessions = new HashMap<String, User>();
		sendEmail = new SendMail();
	}
	
	public User getLoggedUser(String token) {
		User user = null;
		user = openedSessions.get(token);
		return user;
	}
	
	public void openSession(String token) {
		try {
			//User user = ;
			openedSessions.put(token, getUser(token));
			
//			if(!getDaoManager().verifyUserByEmail(user.getEmail())) {
//				return getDaoManager().getUserByEmail(user.getEmail());
//			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		//return null;
		
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
	
	public DAOManager getDaoManager() {
		return daoManager;
	}
	
	public void sendMail(String from, String to, String subject, String message) {
		sendEmail.sendMail(from, to, subject, message);
	}

}

