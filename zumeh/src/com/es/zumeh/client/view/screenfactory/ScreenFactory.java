package com.es.zumeh.client.view.screenfactory;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.view.pages.FirstAccessPage;
import com.es.zumeh.client.view.pages.LoginPage;
import com.es.zumeh.client.view.pages.ProfileReadOnlyPage;
import com.es.zumeh.client.view.pages.SearchPage;
import com.es.zumeh.client.view.pages.work.WorkReadOnlyPage;
import com.es.zumeh.client.view.pages.work.WorkWritePage;

public class ScreenFactory {
	
	private static ScreenFactory instance;
	private LoginPage loginPage;
	private FirstAccessPage pag;
	private ProfileReadOnlyPage profileReadOnlyPage;
	private WorkWritePage workWritePage;
	private WorkReadOnlyPage workReadOnlyPage;
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
	
	public WorkWritePage getWorkPage(ClientSessionManager clientSessionManger) {
		this.workWritePage = new WorkWritePage(clientSessionManger);
		return this.workWritePage;
	}
	
	public WorkReadOnlyPage getWorkReadOnlyPage(ClientSessionManager clientSessionManger) {
		this.workReadOnlyPage = new WorkReadOnlyPage(clientSessionManger);
		return this.workReadOnlyPage;
	}
	
	public SearchPage getSeacrhPage() {
		this.search = new SearchPage();
		return this.search;
	}
	
}
