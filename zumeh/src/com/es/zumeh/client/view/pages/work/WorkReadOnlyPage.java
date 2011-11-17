package com.es.zumeh.client.view.pages.work;

import java.util.ArrayList;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.model.to.CommentTO;
import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.view.pages.CommentPanel;
import com.es.zumeh.client.view.pages.RevisionReadOnlyPanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WorkReadOnlyPage extends WorkPage {
	private VerticalPanel verticalMasterWorkDescription;
	private WorkReadOnlyPanel wp = new WorkReadOnlyPanel(this);
	private CommentPanel cp = new CommentPanel(this);
	private RevisionReadOnlyPanel rp = new RevisionReadOnlyPanel(wp, cp);
	final Label fullDescriptionLabel = new Label();
	final Label shortDescriptionLabel = new Label();
	
	public WorkReadOnlyPage(ClientSessionManager clientSessionManger) {
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
		System.out.println("Revision Final: " + super.getRevisionTO().getShortDescriptionText());
		fullDescriptionLabel.setText(getRevisionTO().getFullDescriptionText());
		shortDescriptionLabel.setText(getRevisionTO().getShortDescriptionText());
		rp.setWorksFromTOList(getRevisionTO().getTOWorks());
		wp.setWorkFromWorkTO(getRevisionTO().getWork(revisionTO.getTOWorks().size()));
	}
	
	public void loadCommentTO(ArrayList<CommentTO> comments) {
		super.setCommentsTO(comments);
		cp.setCommentTO(comments);
		cp.refreshCommentPanel();
	}
	
	@SuppressWarnings("deprecation")
	private VerticalPanel getMasterDescriptionPanel() {
		VerticalPanel verticalMasterWorkDescription = new VerticalPanel();
		verticalMasterWorkDescription.setHeight("200px");
		verticalMasterWorkDescription.setWidth((getScreenWidth()-400) + "px");
		
		Hyperlink hprlnkWork = new Hyperlink("Home", false, "home");
		Hyperlink editLink = new Hyperlink("Edit", false, "preview");
		editLink.addClickHandler(new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				WorkWritePage w = new WorkWritePage(null);
				rp.setFullDescription(getRevisionTO().getFullDescriptionText());
				rp.setShortDescription(getRevisionTO().getShortDescriptionText());
				w.loadRevisionTO(rp.getRevisionTO());
				w.loadCommentTO(cp.getCommentTO());
				w.onModuleLoad();
				System.out.println("Clicou Read");
			}
		});
		
		HorizontalPanel horizontalPanelLinks = new HorizontalPanel();
		horizontalPanelLinks.setSpacing(5);
		horizontalPanelLinks.add(hprlnkWork);
		horizontalPanelLinks.add(editLink);
		
		shortDescriptionLabel.setText(getRevisionTO().getShortDescriptionText());
		shortDescriptionLabel.getElement().getStyle().setFontSize(25, Unit.PX);
		
		AbsolutePanel shortDescriptionArea = new AbsolutePanel();
		shortDescriptionArea.setWidth((getScreenWidth()-400) + "px");
		shortDescriptionArea.setHeight("30px");
		shortDescriptionArea.add(shortDescriptionLabel);
		
		fullDescriptionLabel.setText(getRevisionTO().getFullDescriptionText());
		
		AbsolutePanel fullDescriptionArea = new AbsolutePanel();
		fullDescriptionArea.setWidth((getScreenWidth()-400) + "px");
		fullDescriptionArea.setHeight("120px");
		fullDescriptionArea.add(fullDescriptionLabel);
		
		verticalMasterWorkDescription.add(horizontalPanelLinks);
		verticalMasterWorkDescription.add(shortDescriptionArea);
		verticalMasterWorkDescription.add(fullDescriptionArea);
		
		return verticalMasterWorkDescription;
	}
}
