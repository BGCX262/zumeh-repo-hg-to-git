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

public class RevisionPanel extends AbsolutePanel {
	final private String BACKGROUND_COLOR = "#00CCFF";
	final private int WIDTH = getScreenWidth()-400;
	final private int HEIGHT = getScreenHeight()-400;
	final private int RADIUS = 20;
	final private int POS_Y = 25;
	final private int NODE_SPACES = WIDTH/6;
	final private int POS_X_INIT = NODE_SPACES/2;
	
	private LinkedList<WorkTO> workRevisions = new LinkedList<WorkTO>();
	
	final DrawingArea revisionArea = new DrawingArea(WIDTH, HEIGHT);
	
	//private Circle goLeft;
	private Circle goRight;
	private Circle actualRevision;
	private WorkPage root;
	
	public RevisionPanel(WorkPage root) {
		setHeight(50+"px");
		setWidth(WIDTH+"px");
		getElement().getStyle().setBackgroundColor(BACKGROUND_COLOR);
		this.root = root;
		// int x, int y, int radius;
		
		// Go left
		final Circle goLeft = new Circle(POS_X_INIT, POS_Y, RADIUS);
		goLeft.setFillColor("green");
		goLeft.addClickHandler(goLeftHandler);
		revisionArea.add(goLeft);
		
		final Circle node1 = new Circle(goLeft.getX()+RADIUS+RADIUS+NODE_SPACES, POS_Y, RADIUS);
		node1.setFillColor("green");
		node1.addClickHandler(goNode1Handler);
		revisionArea.add(node1);
		
		final Circle node2 = new Circle(node1.getX()+RADIUS+RADIUS+NODE_SPACES, POS_Y, RADIUS);
		node2.setFillColor("green");
		node2.addClickHandler(goNode2Handler);
		revisionArea.add(node2);
		
		final Circle node3 = new Circle(node2.getX()+RADIUS+RADIUS+NODE_SPACES, POS_Y, RADIUS);
		node3.setFillColor("green");
		node3.addClickHandler(goNode3Handler);
		revisionArea.add(node3);
		
		final Circle goRight = new Circle(node3.getX()+RADIUS+RADIUS+NODE_SPACES, POS_Y, RADIUS);
		goRight.setFillColor("green");
		goRight.addClickHandler(goRightHandler);
		revisionArea.add(goRight);
		
		
		add(revisionArea, 0, 0);
	}
	
	public RevisionTO getRevisionTO() {
		RevisionTO tmpRevisionTO = new RevisionTO();
		tmpRevisionTO.setWorkTOList(workRevisions);
		
		return tmpRevisionTO;
	}
	
	// *********************** Handlers ***************************
	ClickHandler goLeftHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			System.out.println("====================");
			WorkTO tmpWorkTO = root.getWorkTO();
			tmpWorkTO.setWorkId(workRevisions.size() + 1);
			System.out.println(tmpWorkTO);
			workRevisions.add(tmpWorkTO);
			System.out.println("====================");
		}
	};
	
	ClickHandler goRightHandler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			System.out.println("go Right Handler");
			root.clear();
			//root.add(workRevisions.get(0));
		}
	};
	
	ClickHandler goNode1Handler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			System.out.println("go Node1 Handler");
			
			
		}
	};
	
	ClickHandler goNode2Handler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			System.out.println("go Node2 Handler");
		}
	};
	
	ClickHandler goNode3Handler = new ClickHandler() {
		
		@Override
		public void onClick(ClickEvent event) {
			// TODO Auto-generated method stub
			System.out.println("go Node3 Handler");
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
