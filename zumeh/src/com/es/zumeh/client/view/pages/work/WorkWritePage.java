package com.es.zumeh.client.view.pages.work;

import java.util.ArrayList;

import com.es.zumeh.client.control.ClientSessionManager;
import com.es.zumeh.client.model.to.CommentTO;
import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.model.to.WorkTO;
import com.es.zumeh.client.view.pages.CommentPanel;
import com.es.zumeh.client.view.pages.RevisionWritePanel;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WorkWritePage extends WorkPage {
	
	private VerticalPanel verticalMasterWorkDescription;
	final private TextBox shortDescriptionArea = new TextBox();
	final private TextArea fullDescriptionBox = new TextArea();
	private WorkWritePanel workerWritePanel;
	private ClientSessionManager clientSessionManager;
	private CommentPanel commentPanel;
	private RevisionWritePanel revisionWritePanel;

	
	public WorkWritePage(ClientSessionManager clientSessionManger,
			RevisionTO revisionTO) {
		this.setClientSessionManager(clientSessionManger);
		super.setRevisionTO(revisionTO);
		workerWritePanel = new WorkWritePanel(this);
		commentPanel = new CommentPanel(this, clientSessionManger);
		revisionWritePanel = new RevisionWritePanel(workerWritePanel,
				commentPanel, getZumehServiceAsync(), clientSessionManger, this);
		verticalMasterWorkDescription = getMasterDescriptionPanel();
	}
	
	@Override
	public void onModuleLoad() {
		refreshAllComponents();
	}
	
	public void refreshToWorkPanel() {
		rootPanel.clear();
		rootPanel.add(workerWritePanel, 0 , 0);
	}
	
	public void refreshAllComponents() {
		rootPanel.clear();
		verticalWorkPanel.add(revisionWritePanel);
		verticalWorkPanel.add(workerWritePanel);
		
		rootPanel.add(verticalMasterWorkDescription, 0, 0);
		rootPanel.add(verticalWorkPanel, 0, 200);
		rootPanel.add(commentPanel, getScreenWidth()-390, 0);
	}
	
	public void loadRevisionTO() {
		fullDescriptionBox.setText(super.getRevisionTO().getFullDescriptionText());
		shortDescriptionArea.setText(super.getRevisionTO().getShortDescriptionText());
		revisionWritePanel.setWorksFromTOList(super.getRevisionTO().getWorks()); //Aqui
		int worksSize = super.getRevisionTO().getWorks().size();
		WorkTO workTO = super.getRevisionTO().getWork(worksSize);
		if (workTO != null) {
			workerWritePanel.setWorkFromWorkTO(workTO);
		}
		loadCommentTO(super.getRevisionTO().getComments());
		
	}
	
	public void loadCommentTO(ArrayList<CommentTO> comments) {
		super.setCommentsTO(comments);
		commentPanel.setCommentTO(comments);
		commentPanel.refreshCommentPanel();
	}
	
	private VerticalPanel getMasterDescriptionPanel() {
		loadRevisionTO();
		VerticalPanel verticalMasterWorkDescription = new VerticalPanel();
		
		HorizontalPanel horizontalPanelLinks = new HorizontalPanel();
		loadHomeAnchor(horizontalPanelLinks);
		loadDeleteWorkAnchor(horizontalPanelLinks);
		
		verticalMasterWorkDescription.setHeight("200px");
		verticalMasterWorkDescription.setWidth((getScreenWidth()-400) + "px");
		
		horizontalPanelLinks.setSpacing(5);
		
		shortDescriptionArea.setWidth((getScreenWidth()-400) + "px");
		shortDescriptionArea.setHeight("30px");
		//shortDescriptionArea.setText(getRevisionTO().getShortDescriptionText());
		shortDescriptionArea.getElement().getStyle().setFontSize(25, Unit.PX);
		
		fullDescriptionBox.setWidth((getScreenWidth()-400) + "px");
		fullDescriptionBox.setHeight("120px");
		//fullDescriptionBox.setText(getRevisionTO().getFullDescriptionText());
		
		verticalMasterWorkDescription.add(horizontalPanelLinks);
		verticalMasterWorkDescription.add(shortDescriptionArea);
		verticalMasterWorkDescription.add(fullDescriptionBox);
		
		return verticalMasterWorkDescription;
	}

	private void loadDeleteWorkAnchor(HorizontalPanel horizontalPanelLinks) {
		Anchor deleteAnchor = new Anchor("Delete work");
		deleteAnchor.addClickHandler(createDeleteAnchorClickHandler());
		horizontalPanelLinks.add(deleteAnchor);
		
	}

	private ClickHandler createDeleteAnchorClickHandler() {
		return new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				zumehService.deleteRevision(getRevisionTO(),
						createDeleteRevisionCallback());
			}

			private AsyncCallback<Boolean> createDeleteRevisionCallback() {
				return new AsyncCallback<Boolean>() {

					@Override
					public void onFailure(Throwable caught) {
						// TODO Add log
						
					}

					@Override
					public void onSuccess(Boolean result) {
						Window.alert("Work deleted with success!");
						//TODO ADD LOG
						loadProfilePage(clientSessionManager);
					}
				};
			}
		};
	}

	private void loadHomeAnchor(HorizontalPanel horizontalPanelLinks) {
		Anchor homeAnchorPage = new Anchor("Home");
		homeAnchorPage.addClickHandler(createHomePageAncorClickHandler());
		horizontalPanelLinks.add(homeAnchorPage);
	}

	private ClickHandler createHomePageAncorClickHandler() {
		return new ClickHandler() {
			@Override
			public void onClick(ClickEvent event) {
				loadProfilePage(clientSessionManager);
			}
		};
	}

	public ClientSessionManager getClientSessionManager() {
		return clientSessionManager;
	}

	public void setClientSessionManager(ClientSessionManager clientSessionManager) {
		this.clientSessionManager = clientSessionManager;
	}

	public String getTextFullDescription() {
		return fullDescriptionBox.getText();
	}
	
	public String getTextShortDescription() {
		return shortDescriptionArea.getText();
	}
}
