package com.es.zumeh.server.model;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.google.gdata.client.GoogleService;
import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.Feed;
import com.google.gdata.data.Person;
import com.google.gdata.util.ServiceException;

/*
 * Essa classe tah uma seboseira, mas acho que essa estrutura eh temporaria.
 */
public class GoogleUserInfo {
	private final String CONTACTS_URL = "http://www.google.com/m8/feeds/contacts/default/base";
	private final String SERVICE_NAME = "cp";
	private final String APP_NAME = "zumeh";
	
	private final String SPACE = " ";
	
	private String token;
	
	public GoogleUserInfo(String token) {
		this.token = token;
	}
	
	public HashMap<String, String> getGoogleUserInfo() {
		GoogleService googleService = new GoogleService(SERVICE_NAME, APP_NAME);
		googleService.setAuthSubToken(this.token);
	
		URL feedUrl = null;
		try {
			feedUrl = new URL(CONTACTS_URL);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		BaseFeed resultFeed = null;
		try {
			resultFeed = googleService.getFeed(feedUrl, Feed.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		List<Person> authors = resultFeed.getAuthors();
	
		Person person = authors.get(0);
		
		HashMap<String, String> userInfoMap = new HashMap<String, String>();
		
		userInfoMap.put("firstname", getFirstName(person.getName()).trim());
		userInfoMap.put("lastname", getLastName(person.getName()).trim());
		userInfoMap.put("email", person.getEmail().trim());
		return userInfoMap;
	}
	
	private String getFirstName(String info) {
		return info.split(SPACE)[0];
	}
	
	private String getLastName(String info) {
		int spaceIndex = info.indexOf(SPACE);
		return info.substring(spaceIndex);
	}
}