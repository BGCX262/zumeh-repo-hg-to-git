package com.es.zumeh.client.view.pages;

import java.util.ArrayList;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.model.to.CommentTO;
import com.es.zumeh.client.model.to.RevisionTO;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WorkWritePage extends WorkPage {
	private VerticalPanel verticalMasterWorkDescription;
	final private TextBox shortDescriptionArea = new TextBox();
	final private TextArea fullDescriptionBox = new TextArea();
	private WorkWritePanel wp = new WorkWritePanel(this);
	private CommentPanel cp = new CommentPanel(this);
	private RevisionWritePanel rp = new RevisionWritePanel(wp, cp);
	
	public WorkWritePage(ClientSessionManager clientSessionManger) {
		// TODO
		verticalMasterWorkDescription = getMasterDescriptionPanel();
	}
	
	@Override
	public void onModuleLoad() {
		refreshAllComponents();
	}
	
	public void refreshToWorkPanel() {
		RootPanel.get().clear();
		RootPanel.get().add(wp, 0 , 0);
	}
	
	public void refreshAllComponents() {
		RootPanel.get().clear();
		verticalWorkPanel.add(rp);
		verticalWorkPanel.add(wp);
		
		RootPanel.get().add(verticalMasterWorkDescription, 0, 0);
		RootPanel.get().add(verticalWorkPanel, 0, 200);
		RootPanel.get().add(cp, getScreenWidth()-390, 0);
	}
	
	public void loadRevisionTO(RevisionTO revisionTO) {
		super.setRevisionTO(revisionTO);
		fullDescriptionBox.setText(getRevisionTO().getFullDescriptionText());
		shortDescriptionArea.setText(getRevisionTO().getShortDescriptionText());
		rp.setWorksFromTOList(getRevisionTO().getTOWorks());
		wp.setWorkFromWorkTO(getRevisionTO().getWork(revisionTO.getTOWorks().size()));
	}
	
	public void loadCommentTO(ArrayList<CommentTO> comments) {
		super.setCommentsTO(comments);
		cp.setCommentTO(comments);
		cp.refreshCommentPanel();
	}
	
	private VerticalPanel getMasterDescriptionPanel() {
		VerticalPanel verticalMasterWorkDescription = new VerticalPanel();
		Hyperlink hprlnkWork = new Hyperlink("Home", false, "home");
		Hyperlink seePreviowsLink = new Hyperlink("Preview", false, "preview");
		HorizontalPanel horizontalPanelLinks = new HorizontalPanel();
		
		verticalMasterWorkDescription.setHeight("200px");
		verticalMasterWorkDescription.setWidth((getScreenWidth()-400) + "px");
		
		horizontalPanelLinks.setSpacing(5);
		horizontalPanelLinks.add(hprlnkWork);
		horizontalPanelLinks.add(seePreviowsLink);
		
		shortDescriptionArea.setWidth((getScreenWidth()-400) + "px");
		shortDescriptionArea.setHeight("30px");
		shortDescriptionArea.setText(getRevisionTO().getShortDescriptionText());
		shortDescriptionArea.getElement().getStyle().setFontSize(25, Unit.PX);
		
		fullDescriptionBox.setWidth((getScreenWidth()-400) + "px");
		fullDescriptionBox.setHeight("120px");
		fullDescriptionBox.setText(getRevisionTO().getFullDescriptionText());
		
		verticalMasterWorkDescription.add(horizontalPanelLinks);
		verticalMasterWorkDescription.add(shortDescriptionArea);
		verticalMasterWorkDescription.add(fullDescriptionBox);
		
		seePreviowsLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				// TODO Auto-generated method stub
				RootPanel.get().clear();
				WorkReadOnlyPage w = new WorkReadOnlyPage(null);
				rp.setFullDescription(fullDescriptionBox.getText());
				rp.setShortDescription(shortDescriptionArea.getText());
				
				w.loadRevisionTO(rp.getRevisionTO());
				w.loadCommentTO(cp.getCommentTO());
				w.onModuleLoad();
				System.out.println("Clicou");
			}
		});
		
		return verticalMasterWorkDescription;
	}
}
