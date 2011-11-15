package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.control.ClientSessionManager;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WorkWritePage extends WorkPage {
	private VerticalPanel verticalMasterWorkDescription = getMasterDescriptionPanel();
	private WorkPanel wp = new WorkPanel(this);
	private CommentPanel cp = new CommentPanel();
	private RevisionPanel rp = new RevisionPanel(wp, cp);
	
	public WorkWritePage(ClientSessionManager clientSessionManger) {
		// TODO
	}
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		refreshAllComponents();
		//refreshToWorkPanel();
	}
	
	public void refreshToWorkPanel() {
		RootPanel.get().clear();
		RootPanel.get().add(wp, 0 , 0);
		/*verticalWorkPanel.add(rp);
		verticalWorkPanel.add(wp);
		
		RootPanel.get().add(verticalMasterWorkDescription, 0, 0);
		RootPanel.get().add(verticalWorkPanel, 0, 200);
		RootPanel.get().add(cp, getScreenWidth()-390, 0);*/
	}
	
	public void refreshAllComponents() {
		RootPanel.get().clear();
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
	
	private void switchPanelSize() {
		/*if(getOffsetWidth() == WIDTH_FULL && getOffsetHeight() == HEIGHT_FULL) {
			setSize(WIDTH+"px", HEIGHT+"px");
			workArea.setPixelSize(WIDTH, HEIGHT);
		} else {
			setSize(WIDTH_FULL+"px", HEIGHT_FULL+"px");
			workArea.setPixelSize(WIDTH_FULL, HEIGHT_FULL);
		}
		root.refreshPositions(root.getMaxColumn()+1);*/
	}
}
