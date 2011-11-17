package com.es.zumeh.client.main;

import com.es.zumeh.client.view.pages.LoginPage;
import com.es.zumeh.client.view.pages.Page;
import com.es.zumeh.client.view.screenfactory.ScreenFactory;
import com.google.gwt.core.client.EntryPoint;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Zumeh extends Page implements EntryPoint {
	
	private static ScreenFactory screenFactory = ScreenFactory.getInstance();

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		
		LoginPage loginPage = screenFactory.getLoginPage();
		loginPage.onModuleLoad();
		
		
	}
}
