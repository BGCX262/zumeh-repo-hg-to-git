package com.es.zumeh.client.main;

import com.es.zumeh.client.view.pages.FirstAccessPage;
import com.es.zumeh.client.view.pages.LoginPage;
import com.es.zumeh.client.view.pages.SearchPage;
import com.es.zumeh.client.view.pages.WorkReadOnlyPage;
import com.es.zumeh.client.view.pages.WorkWritePage;
import com.es.zumeh.client.view.pages.WorkWritePanel;
import com.es.zumeh.client.view.screenfactory.ScreenFactory;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Zumeh implements EntryPoint {
	
	private static ScreenFactory screenFactory = ScreenFactory.getInstance();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
//		SearchPage s = screenFactory.getSeacrhPage();
//		s.onModuleLoad();
		
		LoginPage loginPage = screenFactory.getLoginPage();
		loginPage.onModuleLoad();
		
		//WorkWritePage wp = screenFactory.getWorkPage(null);
		//wp.onModuleLoad();
		
		//WorkReadOnlyPage wrop = screenFactory.getWorkReadOnlyPage(null);
		//wrop.onModuleLoad();
	}
}
