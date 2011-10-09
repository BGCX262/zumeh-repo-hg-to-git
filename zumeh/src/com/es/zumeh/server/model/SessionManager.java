package com.es.zumeh.server.model;

import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.es.zumeh.client.model.user.User;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.Feed;
import com.google.gdata.data.Person;
import com.google.gdata.util.ServiceException;

public class SessionManager {
	private static SessionManager thisInstance;
	private HashMap<String, User> openedSessions;
	
	private SessionManager() {
		openedSessions = new HashMap<String, User>();
	}
	
	public User getLoggedUser(String token) {
		User user = null;
		user = openedSessions.get(token);
		return user;
	}
	
	public void openSession(String token) {
		try {
			openedSessions.put(token, getUser(token));
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
		
		return user;
	}
}

