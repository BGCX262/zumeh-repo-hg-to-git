package com.es.zumeh.client.view.screenfactory;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.client.view.pages.FirstAccessPage;
import com.es.zumeh.client.view.pages.LoginPage;
import com.es.zumeh.client.view.pages.ProfileReadOnlyPage;
import com.es.zumeh.client.view.pages.WorkPage;

public class ScreenFactory {
	
	private static ScreenFactory instance;
	private LoginPage loginPage;
	private FirstAccessPage pag;
	private ProfileReadOnlyPage profileReadOnlyPage;
	private WorkPage workPage;
	
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

	public ProfileReadOnlyPage getProfileReadOnlyPage(ClientSessionManager clienteSessionManger) {
		this.profileReadOnlyPage = new ProfileReadOnlyPage(clienteSessionManger);
		return profileReadOnlyPage;
	}
	
	public ProfileReadOnlyPage getFriendProfile(ClientSessionManager clienteSessionManger) {
		this.profileReadOnlyPage = new ProfileReadOnlyPage(clienteSessionManger);
		return profileReadOnlyPage;
	}
	
	public ProfileReadOnlyPage getFriendProfile(ClientSessionManager clienteSessionManger, boolean visitor) {
		this.profileReadOnlyPage = new ProfileReadOnlyPage(clienteSessionManger, visitor);
		return profileReadOnlyPage;
	}
	
	public WorkPage getWorkPage(ClientSessionManager clientSessionManger) {
		this.workPage = new WorkPage(clientSessionManger);
		return this.workPage;
	}
	
}
