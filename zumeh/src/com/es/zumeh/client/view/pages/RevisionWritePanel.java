package com.es.zumeh.client.view.pages;

import java.util.LinkedList;

import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.model.to.WorkTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class RevisionWritePanel extends AbsolutePanel {
	final private String BACKGROUND_COLOR = "#00CCFF";
	final private int WIDTH = getScreenWidth()-400;
	final private int HEIGHT = getScreenHeight()-400;
	final private String BUTTON_WIDTH = getButtonWidth();
	final private String GO_HEAD_REVISION_TEXT = "HEAD";
	final private String CREATE_REVISION_TEXT = "Create New Revision";
	final private String DELETE_REVISION_TEXT = "Delete Revision";
	final private String LEFT_REVISION_TEXT = "<< Go Left Revision";
	final private String RIGHT_REVISION_TEXT = "Go Right Revision >>";
	final private String SAVE_REVISION_TEXT = "Save";
	
	private int actualRevision = 0;
	
	private LinkedList<WorkTO> workRevisions = new LinkedList<WorkTO>();
	private HorizontalPanel hPanel = new HorizontalPanel();
	private WorkWritePanel root;
	private String fullTextDescription;
	private String shortTextDescription;
	private CommentPanel commentPanel;
	
	public RevisionWritePanel(WorkWritePanel root, CommentPanel commentPanel) {
		setHeight(40+"px");
		setWidth(WIDTH+"px");
		this.root = root;
		this.commentPanel = commentPanel;
		
		
		//hPanel.setSpacing(1);
		hPanel.setHeight(40+"px");
		hPanel.setWidth(WIDTH+"px");
		
		hPanel.add(createButton(GO_HEAD_REVISION_TEXT, goHeadHandler));
		hPanel.add(createButton(CREATE_REVISION_TEXT, createRevisionHandler));
		hPanel.add(createButton(DELETE_REVISION_TEXT, deleteHandler));
		hPanel.add(createButton(LEFT_REVISION_TEXT, goLeftRevision));
		hPanel.add(createButton(RIGHT_REVISION_TEXT, goRightRevision));
		hPanel.add(createButton(SAVE_REVISION_TEXT, saveHandler));
		
		add(hPanel, 0, 0);
		
		WorkTO tmpWorkTO = root.getWorkTO();
		tmpWorkTO.setWorkId(workRevisions.size() + 1);
		workRevisions.add(tmpWorkTO);
		goHeadRevision();
	}
	
	public void setShortDescription(String shortTextDescription) {
		this.shortTextDescription = shortTextDescription;
	}
	
	public void setFullDescription(String fullTextDescription) {
		this.fullTextDescription = fullTextDescription;
	}
	
	private String getButtonWidth() {
		if(WIDTH%6 == 0) {
			return (WIDTH/6)+"px";
		} else if((WIDTH+1)%6 == 0) {
			return ((WIDTH+1)/6)+"px";
		} else if((WIDTH+2)%6 == 0) {
			return ((WIDTH+2)/6)+"px";
		} else if((WIDTH+3)%6 == 0) {
			return ((WIDTH+3)/6)+"px";
		} else if((WIDTH+4)%6 == 0) {
			return ((WIDTH+4)/6)+"px";
		} else if((WIDTH+5)%6 == 0) {
			return ((WIDTH+5)/6)+"px";
		} else if((WIDTH+6)%6 == 0) {
			return ((WIDTH+6)/6)+"px";
		} else {
			return ((WIDTH/6)-1)+"px";
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
		tmpRevisionTO.setWorkTOList(workRevisions);
		tmpRevisionTO.setFullDescriptionText(this.fullTextDescription);
		tmpRevisionTO.setShortDescriptionText(this.shortTextDescription);
		return tmpRevisionTO;
	}
	
	// *********************** Handlers ***************************
	ClickHandler createRevisionHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			System.out.println("====================");
			WorkTO tmpWorkTO = root.getWorkTO();
			tmpWorkTO.setWorkId(workRevisions.size() + 1);
			System.out.println(tmpWorkTO);
			workRevisions.add(tmpWorkTO);
			goHeadRevision();
			System.out.println("====================");
		}
	};
	
	ClickHandler goRightRevision = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			System.out.println("go Right Handler");
			System.out.println("**************************");
			goRightRevision();
			root.clear();
			root.getDrawingArea().clear();
			
			//System.out.println(workRevisions.get(1));
			
			//root.setWorkFromWorkTO(workRevisions.get(1));
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
	
	ClickHandler saveHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			System.out.println("Save Handler");
			System.out.println("-------------------------");
			System.out.println("Save Handler: " + root.getWorkById(3));
			System.out.println("-------------------------");
		}
	};
	
	ClickHandler deleteHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			System.out.println("Delete Handler");
			System.out.println("+++++++++++++++++++++++++");
			
			workRevisions.get(getActualRevision());
			workRevisions.remove(getActualRevision());
			
			goHeadRevision();
			root.clear();
			root.getDrawingArea().clear();
			System.out.println("Actual Revision: " + getActualRevision());
			root.setWorkFromWorkTO(workRevisions.get(getActualRevision()));
			
			System.out.println("+++++++++++++++++++++++++");
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
}
