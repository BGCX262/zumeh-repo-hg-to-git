package com.es.zumeh.client.view.pages;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.InlineLabel;
import com.google.gwt.user.client.ui.RootPanel;

public class SearchPage extends Page implements EntryPoint {

	final RootPanel rootPanel = RootPanel.get("nameFieldContainer");
	
	@Override
	public void onModuleLoad() {
		
		rootPanel.setStyleName("body");
		
		Button btnNewButton_1 = new Button("Profile");
		btnNewButton_1.setStyleName("botaoTeste");
		rootPanel.add(btnNewButton_1, 96, 10);
		btnNewButton_1.setSize("93px", "24px");
		
		Button btnNewButton = new Button("My Works");
		btnNewButton.setStyleName("botaoTeste");
		rootPanel.add(btnNewButton, 177, 10);
		btnNewButton.setSize("93px", "24px");
		
		InlineLabel nlnlblZumeh = new InlineLabel("Zumeh");
		rootPanel.add(nlnlblZumeh, 10, 10);
		nlnlblZumeh.setStyleName("h1");
		nlnlblZumeh.setSize("48px", "24px");
		// TODO Auto-generated method stub
		
	}
}
