package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.facade.ZumehServiceAsync;
import com.es.zumeh.client.view.screenfactory.ScreenFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Page extends Widget{
	
	public final ZumehServiceAsync zumehService = GWT.create(ZumehService.class);
	
	RootPanel rootPanel = RootPanel.get("nameFieldContainer");
	
	public void loadSignOutHyperLink() {
		Anchor signOutLink = new Anchor("sign out");
		rootPanel.add(signOutLink, 749, 10);
		
		signOutLink.addClickHandler(createClickHandlerForSignOutLink());
	}

	private ClickHandler createClickHandlerForSignOutLink() { //FIXME ADD IMAGE
		return new ClickHandler() {
			@Override
			public void onClick(final ClickEvent event) {
				loadLoginPageAccess();
			}
		};
	}
	
	public void loadLoginPageAccess() {
		rootPanel.clear();
		LoginPage loginPage = ScreenFactory.getInstance().getLoginPage();
		loginPage.onModuleLoad();
		
	}
	
	public void loadProfilePage(ClientSessionManager clienteSessionManger) {
		rootPanel.clear();
		ProfileReadOnlyPage profilePage = ScreenFactory.getInstance().
				getProfileReadOnlyPage(clienteSessionManger);
		profilePage.onModuleLoad();
	}
	
	public void loadFriendsProfile(ClientSessionManager clienteSessionManger, boolean isVisitor) {
		rootPanel.clear();
		ProfileReadOnlyPage profilePage = ScreenFactory.getInstance().
				getFriendProfile(clienteSessionManger, isVisitor);
		profilePage.onModuleLoad();
	}
	
	public void firstPageAccess(String token) {
		rootPanel.clear();
		FirstAccessPage pagTest = ScreenFactory
				.getInstance().getFirstAccessPage(token);
		pagTest.onModuleLoad();
	}
	
	public void loadWorkPage(ClientSessionManager clientSessionManger) {
		rootPanel.clear();
		WorkPage workPage = ScreenFactory.getInstance().getWorkPage(clientSessionManger);
		workPage.onModuleLoad();
	}

}
