package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.model.to.UserTO;
import com.es.zumeh.client.view.screenfactory.ScreenFactory;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.DecoratedPopupPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.Widget;


public class ProfileReadOnlyPage extends Page implements EntryPoint {
	
	private UserTO user;
	
	public ProfileReadOnlyPage(UserTO user) {
		this.user = user;
	}

	@Override
	public void onModuleLoad() {
		
		final Label errorLabel = new Label();
		
		rootPanel.setSize("640", "480");
		RootPanel.get("errorLabelContainer").add(errorLabel);
		rootPanel.setStyleName(".teste");
		
		AbsolutePanel absoluteRootPanel = new AbsolutePanel();
		absoluteRootPanel.setStyleName("profileBackGround");
		rootPanel.add(absoluteRootPanel, 10, 0);
		absoluteRootPanel.setSize("942px", "648px");
		
		AbsolutePanel imageAbsolutePanel = new AbsolutePanel();
		imageAbsolutePanel.setStyleName("imagePanelSettings");
		absoluteRootPanel.add(imageAbsolutePanel, 38, 43);
		imageAbsolutePanel.setSize("121px", "164px");
		
		loadImage(imageAbsolutePanel);
		
		AbsolutePanel descriptionAbsolutePanel = 
				createDescriptionAbsolutePanel(absoluteRootPanel);
		
		nameEmailLabel(descriptionAbsolutePanel);
		
		whoAreYouText(descriptionAbsolutePanel);
		
		AbsolutePanel absolutePanel = createAbsolutePanel(absoluteRootPanel);
		
		loadStackLayoutPanel(absolutePanel);
		
		AbsolutePanel absolutePanel_1 = createAbsolutePanel1(absoluteRootPanel);
		
		AbsolutePanel absolutePanel_3 = createAbsolutePanel3(absolutePanel_1);
		
		loadHyperlinkWorker1(absolutePanel_3);
		
		loadHyperlinkWorker2(absolutePanel_3);
		
		loadHyperlinkWorker3(absolutePanel_3);
		
		AbsolutePanel absolutePanel_2 = createAbsolutePanel2(absoluteRootPanel);
		
		loadHyperlinkAboutUs(absoluteRootPanel, absolutePanel_2);
		
		//loadSignOut(absoluteRootPanel);
		loadSignOutHyperLink();
		
		
	}

	@SuppressWarnings("deprecation")
	private void loadHyperlinkAboutUs(AbsolutePanel absoluteRootPanel,
			AbsolutePanel absolutePanel_2) {
		Hyperlink hprlnkAboutUs = new Hyperlink("About Us", false, "newHistoryToken");
		absolutePanel_2.add(hprlnkAboutUs, 10, 10);
		
		hprlnkAboutUs.addClickHandler(new ClickHandler() {
			final DecoratedPopupPanel simplePopup = new DecoratedPopupPanel(true);
			
			
			@Override
			public void onClick(final ClickEvent event) { //TODO COMPLETAR
				simplePopup.ensureDebugId("cwBasicPopup-simplePopup");
			    simplePopup.setWidth("150px");
			    simplePopup.setHeight("150px");
			    simplePopup.setVisible(true);
			    simplePopup.setStyleName("gwt-PopupPanel");
			    simplePopup.setTitle("About us");
			    
				final Widget source = (Widget) event.getSource();
	            final int left = source.getAbsoluteLeft() + 10;
	            final int top = source.getAbsoluteTop() - 200;
	            simplePopup.setPopupPosition(left, top);

	            // Show the popup
	            simplePopup.show();
				
			}
		});
		

		
	}

	private void loadSignOut(AbsolutePanel absoluteRootPanel) {
		Hyperlink hyperlink = new Hyperlink("sign out", false, "");
		absoluteRootPanel.add(hyperlink, 749, 10);
		hyperlink.setSize("46px", "18px");
	}

	private AbsolutePanel createAbsolutePanel2(AbsolutePanel absoluteRootPanel) {
		AbsolutePanel absolutePanel_2 = new AbsolutePanel();
		absoluteRootPanel.add(absolutePanel_2, 10, 613);
		absolutePanel_2.setSize("783px", "25px");
		return absolutePanel_2;
	}

	private void loadHyperlinkWorker3(AbsolutePanel absolutePanel_3) {
		Hyperlink hprlnkWork = new Hyperlink("Work 3", false, "newHistoryToken");
		absolutePanel_3.add(hprlnkWork, 0, 58);
	}

	private void loadHyperlinkWorker2(AbsolutePanel absolutePanel_3) {
		Hyperlink hprlnkWorker_1 = new Hyperlink("Work 2", false, "newHistoryToken");
		absolutePanel_3.add(hprlnkWorker_1, 0, 34);
	}

	@SuppressWarnings("deprecation")
	private void loadHyperlinkWorker1(AbsolutePanel absolutePanel_3) {
		Hyperlink hprlnkWorker1 = new Hyperlink("Work 1", false, "newHistoryToken");
		absolutePanel_3.add(hprlnkWorker1, 0, 10);
		hprlnkWorker1.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(final ClickEvent event) {
				// TODO Auto-generated method stub
				rootPanel.clear();
				final WorkPage workPage = ScreenFactory.getInstance().getWorkPage();
				workPage.onModuleLoad();
			}
		});
	}

	private AbsolutePanel createAbsolutePanel3(AbsolutePanel absolutePanel_1) {
		AbsolutePanel absolutePanel_3 = new AbsolutePanel();
		absolutePanel_1.add(absolutePanel_3, 115, 35);
		absolutePanel_3.setSize("100px", "158px");
		return absolutePanel_3;
	}

	private AbsolutePanel createAbsolutePanel1(AbsolutePanel absoluteRootPanel) {
		AbsolutePanel absolutePanel_1 = new AbsolutePanel();
		absolutePanel_1.setStyleName("profileBackGround-works");
		absoluteRootPanel.add(absolutePanel_1, 181, 156);
		absolutePanel_1.setSize("542px", "261px");
		return absolutePanel_1;
	}

	private AbsolutePanel createAbsolutePanel(AbsolutePanel absoluteRootPanel) {
		AbsolutePanel absolutePanel = new AbsolutePanel();
		absoluteRootPanel.add(absolutePanel, 38, 239);
		absolutePanel.setSize("121px", "261px");
		return absolutePanel;
	}

	private AbsolutePanel createDescriptionAbsolutePanel(
			AbsolutePanel absoluteRootPanel) {
		AbsolutePanel descriptionAbsolutePanel = new AbsolutePanel();
		descriptionAbsolutePanel.setStyleName("teste");
		absoluteRootPanel.add(descriptionAbsolutePanel, 181, 43);
		descriptionAbsolutePanel.setSize("542px", "100px");
		return descriptionAbsolutePanel;
	}

	private void loadImage(AbsolutePanel imageAbsolutePanel) {
		Image image = new Image("images/sheldon.jpg");
		imageAbsolutePanel.add(image, 0, 0);
		image.setStyleName("imageProfileBoard");
		image.setSize("121px", "164px");
	}

	private void whoAreYouText(AbsolutePanel descriptionAbsolutePanel) {
		TextArea whoAreYoutextArea = new TextArea();
		whoAreYoutextArea.setReadOnly(true);
		whoAreYoutextArea.setText(getUser().getWhoAreYou());
		descriptionAbsolutePanel.add(whoAreYoutextArea, 10, 36);
		whoAreYoutextArea.setSize("512px", "48px");
	}

	private void nameEmailLabel(AbsolutePanel descriptionAbsolutePanel) {
		Label nameLabel = new Label("Name: " + getUser().getName());
		nameLabel.setStyleName("descriptionPanelSettings");
		descriptionAbsolutePanel.add(nameLabel, 10, 10);
		nameLabel.setSize("261px", "20px");
		
		Label lblNewLabel = new Label("Email: " + getUser().getEmail());
		descriptionAbsolutePanel.add(lblNewLabel, 277, 10);
		lblNewLabel.setSize("255px", "18px");
	}

	private void loadStackLayoutPanel(AbsolutePanel absolutePanel) {
		StackLayoutPanel stackLayoutPanel = new StackLayoutPanel(Unit.EM);
		
		StackLayoutPanel stackLayoutPanel_1 = new StackLayoutPanel(Unit.EM);
		
		StackLayoutPanel stackLayoutPanel_2 = new StackLayoutPanel(Unit.EM);
		
		stackLayoutPanel_1.add(stackLayoutPanel_2, new HTML("New Widget"), 2.0);
		stackLayoutPanel.add(stackLayoutPanel_1, new HTML("New Widget"), 2.0);
		absolutePanel.add(stackLayoutPanel, 0, 0);
		stackLayoutPanel.setSize("100px", "101px");
	}
	
	public UserTO getUser() {
		return user;
	}

	public void setUser(UserTO user) {
		this.user = user;
	}
}
