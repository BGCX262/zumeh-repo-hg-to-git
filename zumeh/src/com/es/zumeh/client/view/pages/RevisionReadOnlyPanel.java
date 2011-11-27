package com.es.zumeh.client.view.pages;

import java.util.LinkedList;

import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.model.to.WorkTO;
import com.es.zumeh.client.view.pages.work.WorkReadOnlyPanel;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class RevisionReadOnlyPanel extends AbsolutePanel {
	final private String BACKGROUND_COLOR = "#00CCFF";
	final private int WIDTH = getScreenWidth()-400;
	final private int HEIGHT = getScreenHeight()-400;
	final private String BUTTON_WIDTH = getButtonWidth();
	final private String GO_HEAD_REVISION_TEXT = "HEAD";
	final private String LEFT_REVISION_TEXT = "<< Go Left Revision";
	final private String RIGHT_REVISION_TEXT = "Go Right Revision >>";
	
	private int actualRevision = 0;
	
	private LinkedList<WorkTO> workRevisions = new LinkedList<WorkTO>();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private WorkReadOnlyPanel root;
	private String fullTextDescription;
	private String shortTextDescription;
	private CommentPanel commentPanel;
	
	public RevisionReadOnlyPanel(WorkReadOnlyPanel root, CommentPanel commentPanel) {
		setHeight(40+"px");
		setWidth(WIDTH+"px");
		this.root = root;
		this.commentPanel = commentPanel;
		
		hPanel.setHeight(40+"px");
		hPanel.setWidth(WIDTH+"px");
		
		hPanel.add(createButton(GO_HEAD_REVISION_TEXT, goHeadHandler));
		hPanel.add(createButton(LEFT_REVISION_TEXT, goLeftRevision));
		hPanel.add(createButton(RIGHT_REVISION_TEXT, goRightRevision));
		
		add(hPanel, 0, 0);
	}
	
	private String getButtonWidth() {
		if(WIDTH%3 == 0) {
			return (WIDTH/3)+"px";
		} else if((WIDTH+1)%3 == 0) {
			return ((WIDTH+1)/3)+"px";
		} else if((WIDTH+2)%3 == 0) {
			return ((WIDTH+2)/3)+"px";
		} else if((WIDTH+3)%3 == 0) {
			return ((WIDTH+3)/3)+"px";
		} else {
			return ((WIDTH/3)-1)+"px";
		}
	}
	
	private Button createButton(String btnContent, ClickHandler btnHandler) {
		final Button tmpRevisionBtn = new Button(btnContent);
		tmpRevisionBtn.setWidth(BUTTON_WIDTH);
		tmpRevisionBtn.setHeight("40px");
		tmpRevisionBtn.addClickHandler(btnHandler);
		return tmpRevisionBtn;
	}
	
	private CommentPanel getCommentPanel() {
		return this.commentPanel;
	}
	
	private void goHeadRevision() {
		actualRevision = workRevisions.size()-1;
	}
	
	private void goLeftRevision() {
		if(actualRevision > 0) {
			actualRevision--;
		}
	}
	
	private void goRightRevision() {
		if(actualRevision < workRevisions.size()-1) {
			actualRevision++;
		}
	}
	
	private int getActualRevision() {
		return actualRevision;
	}
	
	public RevisionTO getRevisionTO() {
		RevisionTO tmpRevisionTO = new RevisionTO();
		tmpRevisionTO.setFullDescriptionText(this.fullTextDescription);
		tmpRevisionTO.setShortDescriptionText(this.shortTextDescription);
		tmpRevisionTO.setWorks(workRevisions);
		return tmpRevisionTO;
	}
	
	// *********************** Handlers ***************************
	
	ClickHandler goRightRevision = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			System.out.println("go Right Handler");
			System.out.println("**************************");
			goRightRevision();
			root.clear();
			root.getDrawingArea().clear();
			System.out.println("Actual Revision: " + getActualRevision());
			root.setWorkFromWorkTO(workRevisions.get(getActualRevision()));
			System.out.println("**************************");
		}
	};
	
	ClickHandler goLeftRevision = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			System.out.println("go Left Handler");
			System.out.println("**************************");
			goLeftRevision();
			root.clear();
			root.getDrawingArea().clear();
			System.out.println("Actual Revision: " + getActualRevision());
			root.setWorkFromWorkTO(workRevisions.get(getActualRevision()));
			System.out.println("**************************");
			
		}
	};
	
	ClickHandler goHeadHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			System.out.println("go HEAD Handler");
			System.out.println("**************************");
			goHeadRevision();
			root.clear();
			root.getDrawingArea().clear();
			System.out.println("Actual Revision: " + getActualRevision());
			root.setWorkFromWorkTO(workRevisions.get(getActualRevision()));
			System.out.println("**************************");
		}
	};
	
	/*
	 *  Natives
	 */
	public static native int getScreenWidth() /*-{ 
		return $wnd.screen.width;
	}-*/;

	public static native int getScreenHeight() /*-{ 
		return $wnd.screen.height;
	}-*/;

	public void setWorksFromTOList(LinkedList<WorkTO> toWorks) {
		this.workRevisions = toWorks;
	}

	public void setFullDescription(String fullDescriptionText) {
		this.fullTextDescription = fullDescriptionText;
	}

	public void setShortDescription(String shortDescriptionText) {
		this.shortTextDescription = shortDescriptionText;
	}
}
