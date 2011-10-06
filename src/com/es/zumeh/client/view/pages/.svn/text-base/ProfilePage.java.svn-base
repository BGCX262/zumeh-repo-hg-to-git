package com.es.zumeh.client.view.pages;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

public class ProfilePage implements EntryPoint{
	
//	estah comentado porque quebra se eu tentar usar aqui dentro
//	private Person person;
	
	public ProfilePage(Object person) {
//		this.person = person;
	}
	
	@Override
	public void onModuleLoad() {
		final Label errorLabel = new Label();

		// Add the nameField and sendButton to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get();
		RootPanel.get("errorLabelContainer").add(errorLabel, 10, 10);
		errorLabel.setSize("430px", "18px");
		
		Label lblName = new Label("Name:");
		rootPanel.add(lblName, 10, 54);
		
//		Label lblNameuser = new Label(person.getName());
//		rootPanel.add(lblNameuser, 65, 54);
		
		Label lblEmail = new Label("Email:");
		rootPanel.add(lblEmail, 10, 89);
		
//		Label label = new Label(person.getEmail());
//		rootPanel.add(label, 65, 89);
		
	}
}
