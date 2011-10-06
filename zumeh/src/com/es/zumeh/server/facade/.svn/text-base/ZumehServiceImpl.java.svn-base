package com.es.zumeh.server.facade;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;

import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.model.work.Work;
import com.es.zumeh.server.model.GoogleUserInfo;
import com.es.zumeh.server.model.SessionManager;
import com.es.zumeh.server.model.User;
import com.google.gdata.client.GoogleService;
import com.google.gdata.data.BaseFeed;
import com.google.gdata.data.Feed;
import com.google.gdata.data.Person;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * The server side implementation of the RPC service.
 */
@SuppressWarnings("serial")
public class ZumehServiceImpl extends RemoteServiceServlet implements ZumehService {
	private SessionManager sessionManager;
	private GoogleUserInfo googleUserInfo;
	
	public ZumehServiceImpl() {
		sessionManager = SessionManager.getInstance();
	}
	
	@Override
	public void openSession(String token) throws IllegalArgumentException {
		sessionManager.openSession(token);
	}
	
	@Override
	public HashMap<String, String> getGoogleInfo(String token) {
		googleUserInfo = new GoogleUserInfo(token);
		return googleUserInfo.getGoogleUserInfo();
	}
	
	@Override
	public Work getWork(int identifier) {
		Work w = null;
		if(identifier == 0) {
			w = new Work();
			w.setWorkName("Status 0 => Teste");
		} else if (identifier == 1) {
			w = new Work();
			w.setWorkName("Status 1 => Teste");
		}
		return w;
	}
	
	
	/*======================== DEPRECATED ==========================*/
	
	@Override
	public String getUserName(String token) throws IllegalArgumentException {
		//return sessionManager.getLoggedUser(token).getName();
		return null;
	}


	@Override
	public String getUserEmail(String token) throws IllegalArgumentException {
		//return sessionManager.getLoggedUser(token).getEmail();
		return null;
	}

	@Override
	public void closeSession(String token) throws IllegalArgumentException {
		//sessionManager.openSession(token);
	}
	
	@Deprecated
	public String greetServer(String input) throws IllegalArgumentException {

		/*try {
			
			GoogleService googleService = new GoogleService("cp", "oauth-sample-app");
			googleService.setAuthSubToken(input);
			//googleService.setOAuthCredentials(oauthParameters, signer);
			
			
			URL feedUrl = new URL("http://www.google.com/m8/feeds/contacts/default/base");
			//URL feedUrl = new URL("http://www.google.com/m8/feeds/contacts/chalanger@gmail.com/full");
			
			BaseFeed resultFeed = googleService.getFeed(feedUrl, Feed.class);
			System.out.println("getTitle: " + resultFeed.getTitle().getPlainText());
			List<Person> teste = resultFeed.getAuthors();
			
			for (Person person : teste) {
				System.out.println("NOME: " + person.getName());
				System.out.println("\nEMAIL: " + person.getEmail());
				System.out.println("\nExtensionLocalName: " + person.getExtensionLocalName());
				System.out.println("\nNameLang: " + person.getNameLang());
				System.out.println("\nURI: " + person.getUri());
//				user = person;
			}
			
			//System.out.println("getExtensionLocalName: " + resultFeed.getExtensionLocalName());
			//System.out.println("getSubtitle: "+ resultFeed.getSubtitle());

	} catch (MalformedURLException e) {
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (com.google.gdata.util.ServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}*/
		return input;
	}
}
