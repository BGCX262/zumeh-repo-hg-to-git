package com.es.zumeh.client.view.pages;

import java.util.LinkedList;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.shape.Circle;

import com.es.zumeh.client.model.to.NodeTO;
import com.es.zumeh.client.model.to.RevisionTO;
import com.es.zumeh.client.model.to.WorkTO;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;

public class RevisionPanel extends AbsolutePanel {
	final private String BACKGROUND_COLOR = "#00CCFF";
	final private int WIDTH = getScreenWidth()-400;
	final private int HEIGHT = getScreenHeight()-400;
	final private String GO_HEAD_REVISION_TEXT = "HEAD";
	final private String CREATE_REVISION_TEXT = "Create New Revision";
	final private String DELETE_REVISION_TEXT = "Delete Revision";
	final private String LEFT_REVISION_TEXT = "<< Go Left Revision";
	final private String RIGHT_REVISION_TEXT = "Go Right Revision >>";
	final private String SAVE_REVISION_TEXT = "Save";
	
	private int actualRevision = 0;
	
	private LinkedList<WorkTO> workRevisions = new LinkedList<WorkTO>();
	private HorizontalPanel hPanel = new HorizontalPanel();
	
	//final DrawingArea revisionArea = new DrawingArea(WIDTH, HEIGHT);
	
	//private Circle goLeft;
	//private Circle goRight;
	//private Circle actualRevision;
	private WorkPage root;
	
	public RevisionPanel(WorkPage root) {
		setHeight(40+"px");
		setWidth(WIDTH+"px");
		getElement().getStyle().setBackgroundColor(BACKGROUND_COLOR);
		this.root = root;
		
		hPanel.setSpacing(6);
		
		final Button goHeadRevisionBtn = new Button(GO_HEAD_REVISION_TEXT);
		goHeadRevisionBtn.addClickHandler(goHeadHandler);
		hPanel.add(goHeadRevisionBtn);
		
		final Button createRevisionBtn = new Button(CREATE_REVISION_TEXT);
		createRevisionBtn.addClickHandler(createRevisionHandler);
		hPanel.add(createRevisionBtn);
		
		final Button deleteRevisionBtn = new Button(DELETE_REVISION_TEXT);
		deleteRevisionBtn.addClickHandler(deleteHandler);
		hPanel.add(deleteRevisionBtn);
		
		final Button goLeftRevisionBtn = new Button(LEFT_REVISION_TEXT);
		goLeftRevisionBtn.addClickHandler(goLeftRevision);
		hPanel.add(goLeftRevisionBtn);
		
		final Button goRightRevisionBtn = new Button(RIGHT_REVISION_TEXT);
		goRightRevisionBtn.addClickHandler(goRightRevision);
		hPanel.add(goRightRevisionBtn);
		
		final Button saveRevisionBtn = new Button(SAVE_REVISION_TEXT);
		saveRevisionBtn.addClickHandler(saveHandler);
		hPanel.add(saveRevisionBtn);
		
		add(hPanel, 0, 0);
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
		
		return tmpRevisionTO;
	}
	
	// *********************** Handlers ***************************
	ClickHandler createRevisionHandler = new ClickHandler() {
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
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
			// TODO Auto-generated method stub
			System.out.println("Save Handler");
			System.out.println("-------------------------");
			System.out.println("Save Handler: " + root.getWorkById(3));
			System.out.println("-------------------------");
		}
	};
	
	ClickHandler deleteHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
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
}
