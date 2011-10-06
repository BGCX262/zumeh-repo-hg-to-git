package com.es.zumeh.client.facade;

import java.util.List;

import com.es.zumeh.client.model.user.User;
import com.google.gwt.user.client.rpc.AsyncCallback;

public interface UserServiceAsync {
	void getUserList(AsyncCallback<List<User>> callback);

	void getUser(String id, AsyncCallback<User> callback);

	void addUser(User user, AsyncCallback<User> callback);


}