package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.control.ClientSessionManager;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WorkWritePage extends WorkPage {
	
	public WorkWritePage(ClientSessionManager clientSessionManger) {
		// TODO
	}
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		VerticalPanel verticalMasterWorkDescription = getMasterDescriptionPanel();
		
		WorkPanel wp = new WorkPanel();
		CommentPanel cp = new CommentPanel();
		RevisionPanel rp = new RevisionPanel(wp, cp);
		
		verticalWorkPanel.add(rp);
		verticalWorkPanel.add(wp);
		
		RootPanel.get().add(verticalMasterWorkDescription, 0, 0);
		RootPanel.get().add(verticalWorkPanel, 0, 200);
		RootPanel.get().add(cp, getScreenWidth()-390, 0);
	}
	
	private VerticalPanel getMasterDescriptionPanel() {
		VerticalPanel verticalMasterWorkDescription = new VerticalPanel();
		//verticalMasterWorkDescription.getElement().getStyle().setBackgroundColor("#FF0000");
		verticalMasterWorkDescription.setHeight("200px");
		verticalMasterWorkDescription.setWidth((getScreenWidth()-400) + "px");
		
		Hyperlink hprlnkWork = new Hyperlink("Home", false, "home");
		
		TextBox shortDescriptionArea = new TextBox();
		shortDescriptionArea.setWidth((getScreenWidth()-400) + "px");
		shortDescriptionArea.setHeight("30px");
		shortDescriptionArea.setText(getRevisionTO().getShortDescriptionText());
		shortDescriptionArea.getElement().getStyle().setFontSize(25, Unit.PX);
		
		TextArea fullDescriptionBox = new TextArea();
		fullDescriptionBox.setWidth((getScreenWidth()-400) + "px");
		fullDescriptionBox.setHeight("120px");
		fullDescriptionBox.setText(getRevisionTO().getFullDescriptionText());
		
		verticalMasterWorkDescription.add(hprlnkWork);
		verticalMasterWorkDescription.add(shortDescriptionArea);
		verticalMasterWorkDescription.add(fullDescriptionBox);
		
		return verticalMasterWorkDescription;
	}
}
