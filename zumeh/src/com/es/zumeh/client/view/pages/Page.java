package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.facade.ZumehServiceAsync;
import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.client.view.screenfactory.ScreenFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;

public class Page extends Widget{
	
	final ZumehServiceAsync zumehService = GWT.create(ZumehService.class);
	
	final RootPanel rootPanel = RootPanel.get("nameFieldContainer");
	
	@SuppressWarnings("deprecation")
	public void loadSignOutHyperLink() {
		Hyperlink hprlnkSignOut = new Hyperlink("sign out", false, "");
		rootPanel.add(hprlnkSignOut, 749, 10);
		hprlnkSignOut.asWidget();
		
		hprlnkSignOut.addClickHandler(createClickHandlerForSignOutHyperLink());
		
		
	}

	private ClickHandler createClickHandlerForSignOutHyperLink() {
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
	
	public void loadProfilePage(UserTO newUser) {
		rootPanel.clear();
		ProfileReadOnlyPage profilePage = ScreenFactory.getInstance().
				getProfileReadOnlyPage(newUser);
		profilePage.onModuleLoad();
	}
	
	public void firstPageAccess(String token) {
		rootPanel.clear();
		FirstAccessPage pagTest = ScreenFactory
				.getInstance().getFirstAccessPage(token);
		pagTest.onModuleLoad();
	}

}
