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
	private WorkReadOnlyPanel workerReadOnlyPage = new WorkReadOnlyPanel(this);
	private ClientSessionManager clientSessionManger;
	private CommentPanel commentPanel;
	private RevisionReadOnlyPanel revisionReadeOnlyPage = new RevisionReadOnlyPanel(workerReadOnlyPage, commentPanel);
	final Label fullDescriptionLabel = new Label();
	final Label shortDescriptionLabel = new Label();
	private RevisionTO revisionTO;
	
	
	public WorkReadOnlyPage(ClientSessionManager clientSessionManger, RevisionTO revision) {
		this.setClientSessionManger(clientSessionManger);
		this.revisionTO = revision;
		commentPanel = new CommentPanel(this, clientSessionManger);
		verticalMasterWorkDescription = getMasterDescriptionPanel();
	}
	
	@Override
	public void onModuleLoad() {
		refreshAllComponents();
	}
	
	public void refreshToWorkPanel() {
		RootPanel.get().clear();
		RootPanel.get().add(workerReadOnlyPage, 0 , 0);
	}
	
	public void refreshAllComponents() {
		RootPanel.get().clear();
		verticalWorkPanel.add(revisionReadeOnlyPage);
		verticalWorkPanel.add(workerReadOnlyPage);
		
		RootPanel.get().add(verticalMasterWorkDescription, 0, 0);
		RootPanel.get().add(verticalWorkPanel, 0, 200);
		RootPanel.get().add(commentPanel, getScreenWidth()-390, 0);
	}
	
	public void loadRevisionTO(RevisionTO revisionTO) {
		super.setRevisionTO(revisionTO);
		System.out.println("Revision Final: " + super.getRevisionTO().getShortDescriptionText());
		fullDescriptionLabel.setText(getRevisionTO().getFullDescriptionText());
		shortDescriptionLabel.setText(getRevisionTO().getShortDescriptionText());
		revisionReadeOnlyPage.setWorksFromTOList(getRevisionTO().getWorks());
		workerReadOnlyPage.setWorkFromWorkTO(getRevisionTO().getWork(revisionTO.getWorks().size()));
	}
	
	public void loadCommentTO(ArrayList<CommentTO> comments) {
		super.setCommentsTO(comments);
		commentPanel.setCommentTO(comments);
		commentPanel.refreshCommentPanel();
	}
	
	@SuppressWarnings("deprecation")
	private VerticalPanel getMasterDescriptionPanel() {
		VerticalPanel verticalMasterWorkDescription = new VerticalPanel();
		verticalMasterWorkDescription.setHeight("200px");
		verticalMasterWorkDescription.setWidth((getScreenWidth()-400) + "px");
		
		Hyperlink hprlnkWork = new Hyperlink("Home", false, "home");
		Hyperlink editLink = new Hyperlink("Edit", false, "preview");
		editLink.addClickHandler(new ClickHandler() { //FIXME ADD ANCHOR
			@Override
			public void onClick(ClickEvent event) {
				RootPanel.get().clear();
				WorkWritePage workWritePage = new WorkWritePage(getClientSessionManger(), getRevisionTO());
				revisionReadeOnlyPage.setFullDescription(getRevisionTO().getFullDescriptionText());
				revisionReadeOnlyPage.setShortDescription(getRevisionTO().getShortDescriptionText());
				//workWritePage.loadRevisionTO(revisionReadeOnlyPage.getRevisionTO());
				workWritePage.loadCommentTO(commentPanel.getCommentTO());
				workWritePage.onModuleLoad();
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

	public ClientSessionManager getClientSessionManger() {
		return clientSessionManger;
	}

	public void setClientSessionManger(ClientSessionManager clientSessionManger) {
		this.clientSessionManger = clientSessionManger;
	}
}
