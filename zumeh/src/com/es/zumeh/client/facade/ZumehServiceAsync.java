package com.es.zumeh.client.facade;

import java.util.HashMap;

import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.model.to.UserTO;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ZumehService</code>.
 */
public interface ZumehServiceAsync {
	
	void getUserName(String token, AsyncCallback<String> callback) throws IllegalArgumentException;
	void getUserEmail(String token, AsyncCallback<String> callback) throws IllegalArgumentException;
	void openSession(String token, AsyncCallback<Void> callback) throws IllegalArgumentException;
	void closeSession(String token, AsyncCallback callback) throws IllegalArgumentException;
	
	void getGoogleInfo(String token,
			AsyncCallback<HashMap<String, String>> callback);
	void getUser(String id, AsyncCallback<UserTO> callback);
	void getUserList(AsyncCallback<UserTO[]> callback);
	void sendEmail(String from, String to, String title, String text,
			AsyncCallback<Void> callback);
	
	void addUser(UserTO newUser, AsyncCallback<Boolean> w);
	void verifyUser(UserTO user, AsyncCallback<UserTO> callback);
	void addRevision(RevisionTO revisionTO, AsyncCallback<Long> revisionId);
	void getAllRevisionsByOwner(String email, AsyncCallback<RevisionTO[]> revisions);
	void deleteRevision(RevisionTO revisionTO, AsyncCallback<Boolean> result);
	void getUserByEmail(String email, AsyncCallback<UserTO> user);
}
