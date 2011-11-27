package com.es.zumeh.client.facade;

import java.util.HashMap;

import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.model.to.UserTO;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("zumeh")
public interface ZumehService extends RemoteService {
	String getUserName(String token) throws IllegalArgumentException;
	String getUserEmail(String token) throws IllegalArgumentException;
	void openSession(String token) throws IllegalArgumentException;
	void closeSession(String token) throws IllegalArgumentException;
	
	HashMap<String, String> getGoogleInfo(String token) throws IllegalArgumentException;
	
	boolean addUser(UserTO newUser);
	
	void sendEmail(String from, String to, String title, String text);

	UserTO getUser(String id);

	UserTO[] getUserList();

	UserTO verifyUser(UserTO user);

	boolean addRevision(RevisionTO revisionTO);

	RevisionTO[] getAllRevisionsByOwner(String email);

	boolean deleteRevision(RevisionTO revisionTO);
}
