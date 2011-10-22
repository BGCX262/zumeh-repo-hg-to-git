package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.facade.ZumehServiceAsync;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;

public class Page extends Widget{
	
	final ZumehServiceAsync zumehService = GWT.create(ZumehService.class);

}
