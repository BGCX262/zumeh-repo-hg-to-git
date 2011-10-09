package com.es.zumeh.client.view.pages;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.HTML;
import com.es.zumeh.client.model.user.User;


public class ProfileReadOnlyPage implements EntryPoint {
	
	private User user;
	
	//private User user; //TODO add the user.
	public ProfileReadOnlyPage(User user) {
		this.user = user;
	}

	@Override
	public void onModuleLoad() {
		

		
		final Label errorLabel = new Label();
		
		RootPanel rootPanel = RootPanel.get("nameFieldContainer");
		rootPanel.setSize("1024", "768");
		RootPanel.get("errorLabelContainer").add(errorLabel);
		rootPanel.setStyleName(".teste");
		
		AbsolutePanel absoluteRootPanel = new AbsolutePanel();
		absoluteRootPanel.setStyleName("rootPanelProfile");
		rootPanel.add(absoluteRootPanel, 10, 6);
		absoluteRootPanel.setSize("773px", "451px");
		
		AbsolutePanel imageAbsolutePanel = new AbsolutePanel();
		imageAbsolutePanel.setStyleName("imagePanelSettings");
		absoluteRootPanel.add(imageAbsolutePanel, 10, 10);
		imageAbsolutePanel.setSize("100px", "100px");
		
		Image image = new Image("images/.svn/text-base/macaco1.jpg.svn-base");
		imageAbsolutePanel.add(image, 0, 0);
		image.setSize("100px", "100px");
		
		AbsolutePanel descriptionAbsolutePanel = new AbsolutePanel();
		descriptionAbsolutePanel.setStyleName("descriptionPanelSettings");
		absoluteRootPanel.add(descriptionAbsolutePanel, 181, 43);
		descriptionAbsolutePanel.setSize("542px", "100px");
		
		Label lblTiagoHS = new Label("Tiago H S Leite ");
		lblTiagoHS.setStyleName("descriptionPanelSettings");
		descriptionAbsolutePanel.add(lblTiagoHS, 10, 10);
		lblTiagoHS.setSize("522px", "80px");
		
		StackLayoutPanel stackLayoutPanel = new StackLayoutPanel(Unit.EM);
		
		StackLayoutPanel stackLayoutPanel_1 = new StackLayoutPanel(Unit.EM);
		
		StackLayoutPanel stackLayoutPanel_2 = new StackLayoutPanel(Unit.EM);
		stackLayoutPanel_1.add(stackLayoutPanel_2, new HTML("New Widget"), 2.0);
		stackLayoutPanel.add(stackLayoutPanel_1, new HTML("New Widget"), 2.0);
		absoluteRootPanel.add(stackLayoutPanel, 10, 132);
		stackLayoutPanel.setSize("100px", "213px");
		
		
	}
}
