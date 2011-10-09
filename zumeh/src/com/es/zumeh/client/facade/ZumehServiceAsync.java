package com.es.zumeh.client.facade;

import java.util.HashMap;
import java.util.List;

import com.es.zumeh.client.model.user.User;
import com.es.zumeh.client.model.work.Work;
import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>ZumehService</code>.
 */
public interface ZumehServiceAsync {
	
	@Deprecated
	void greetServer(String input, AsyncCallback<String> callback) throws IllegalArgumentException;
	void getUserName(String token, AsyncCallback<String> callback) throws IllegalArgumentException;
	void getUserEmail(String token, AsyncCallback<String> callback) throws IllegalArgumentException;
	void openSession(String token, AsyncCallback callback) throws IllegalArgumentException;
	void closeSession(String token, AsyncCallback callback) throws IllegalArgumentException;
	
	void getGoogleInfo(String token,
			AsyncCallback<HashMap<String, String>> callback);
	void getWork(int identifier, AsyncCallback<Work> callback);
	void getUser(String id, AsyncCallback<User> callback);
	void addUser(User user, AsyncCallback<User> callback);
	void getUserList(AsyncCallback<List<User>> callback);
}