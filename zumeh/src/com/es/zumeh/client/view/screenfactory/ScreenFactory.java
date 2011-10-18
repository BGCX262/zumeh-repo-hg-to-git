package com.es.zumeh.client.view.screenfactory;

import com.es.zumeh.client.view.pages.FirstAccessPage;
import com.es.zumeh.client.view.pages.LoginPage;

import com.es.zumeh.client.view.pages.ProfileReadOnlyPage;
import com.es.zumeh.server.model.persistence.User;

public class ScreenFactory {
	
	private static ScreenFactory instance;
	private LoginPage loginPage;
	private FirstAccessPage pag;
	private ProfileReadOnlyPage profileReadOnlyPage;
	
	private ScreenFactory() {}
	
	public static ScreenFactory getInstance() {
		if (instance == null){
			instance = new ScreenFactory();
		}
		return instance;
	}
	
	public LoginPage getLoginPage() {
		loginPage = new LoginPage();
		return loginPage;
	}
	
	public FirstAccessPage getFirstAccessPage(String token) {
		this.pag = new FirstAccessPage(token);
		return pag;
	}

	public ProfileReadOnlyPage getProfileReadOnlyPage(User user) {
		this.profileReadOnlyPage = new ProfileReadOnlyPage(user);
		return profileReadOnlyPage;
	}
	
}
