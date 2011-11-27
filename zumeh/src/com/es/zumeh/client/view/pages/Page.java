package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.facade.ZumehServiceAsync;
import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.view.pages.work.WorkReadOnlyPage;
import com.es.zumeh.client.view.pages.work.WorkWritePage;
import com.es.zumeh.client.view.screenfactory.ScreenFactory;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class Page extends Widget{
	
	public final ZumehServiceAsync zumehService = GWT.create(ZumehService.class);
	
	protected final int WIDTH = getScreenWidth() - 400;
	protected final int HEIGHT = getScreenHeight() - 400;
	
	
	protected RootPanel rootPanel = RootPanel.get("nameFieldContainer");
	
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
	
	public void loadWorkPage(ClientSessionManager clientSessionManger, RevisionTO revisionTO) {
		rootPanel.clear();
		WorkWritePage workPage = ScreenFactory.getInstance().
				getWorkPage(clientSessionManger, revisionTO);
		workPage.onModuleLoad();
	}
	
	public void loadWorkReadOnlyPage(ClientSessionManager clientSessionManger, RevisionTO revisionTO) {
		rootPanel.clear();
		WorkReadOnlyPage workReadOnlyPage = ScreenFactory.getInstance().
				getWorkReadOnlyPage(clientSessionManger, revisionTO);
		workReadOnlyPage.onModuleLoad();
	}
	
	public Widget createAboutUsLink() {

		final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
		simplePopup.ensureDebugId("cwBasicPopup-simplePopup");
		simplePopup.setWidth("150px");
		simplePopup.setWidget(new HTML("Algumas coisas sobre o nosso projeto"));

		Anchor link = new Anchor("About Us");
		link.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Widget source = (Widget) event.getSource();
				int left = source.getAbsoluteLeft() + 10;
				int top = source.getAbsoluteTop() - 100;
				simplePopup.setPopupPosition(left, top);

				simplePopup.show();

			}
		});
		VerticalPanel vPanel = new VerticalPanel();
		vPanel.setSpacing(5);
		vPanel.add(link);

		return vPanel;
	}
	
	public ZumehServiceAsync getZumehServiceAsync() {
		return this.zumehService;
	}
	
	/*
	 * Natives
	 */
	public static native int getScreenWidth() /*-{
		return $wnd.screen.width;
	}-*/;

	public static native int getScreenHeight() /*-{
		return $wnd.screen.height;
	}-*/;

}
