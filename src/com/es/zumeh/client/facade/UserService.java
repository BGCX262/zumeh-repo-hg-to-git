package com.es.zumeh.client.facade;

import java.util.List;

import com.es.zumeh.client.model.user.User;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("userService")
public interface UserService extends RemoteService {
	
	List<User> getUserList();

	User getUser(String id);

	User addUser(User user);
}