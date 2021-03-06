package com.es.zumeh.server.facade;

import java.util.HashMap;

import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.server.model.GoogleUserInfo;
import com.es.zumeh.server.model.SessionManager;
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

	@Override
	public UserTO[] getUserList() {
		return sessionManager.getDaoManager().getAllUsers();
	}

	@Override
	public UserTO getUser(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addUser(UserTO user) {
		return sessionManager.getDaoManager().addUser(user);
	}

	@Override
	public void sendEmail(String from, String to, String subject, String message) {
		sessionManager.sendMail(from, to, subject, message);
		
	}

	public SessionManager getSessionManager() {
		return sessionManager;
	}
	
	@Override
	public UserTO verifyUser(UserTO user) {
		return sessionManager.getDaoManager().verifyUserTO(user);
	}

	@Override
	public Long addRevision(RevisionTO revisionTO) {
		return sessionManager.getDaoManager().addRevision(revisionTO);
	}

	@Override
	public RevisionTO[] getAllRevisionsByOwner(String ownerEmail) {
		return sessionManager.getDaoManager().getRevisionsByOwner(ownerEmail);
	}

	@Override
	public boolean deleteRevision(RevisionTO revisionTO) {
		return sessionManager.getDaoManager().deleteRevision(revisionTO);
	}

	@Override
	public UserTO getUserByEmail(String email) {
		return sessionManager.getDaoManager().getUserByEmail(email);
	}
}
