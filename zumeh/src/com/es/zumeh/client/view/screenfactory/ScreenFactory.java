package com.es.zumeh.client.view.screenfactory;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.view.pages.FirstAccessPage;
import com.es.zumeh.client.view.pages.LoginPage;
import com.es.zumeh.client.view.pages.ProfileReadOnlyPage;
import com.es.zumeh.client.view.pages.SearchPage;
import com.es.zumeh.client.view.pages.WorkIntermediario;
import com.es.zumeh.client.view.pages.WorkPage;

public class ScreenFactory {
	
	private static ScreenFactory instance;
	private LoginPage loginPage;
	private FirstAccessPage pag;
	private ProfileReadOnlyPage profileReadOnlyPage;
	private WorkIntermediario workPage;
	private SearchPage search;
	
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
	
	public WorkIntermediario getWorkPage(ClientSessionManager clientSessionManger) {
		this.workPage = new WorkIntermediario(clientSessionManger);
		return this.workPage;
	}
	
	public SearchPage getSeacrhPage() {
		this.search = new SearchPage();
		return this.search;
	}
	
}
