package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.model.to.WorkTO;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;

import org.vaadin.gwtgraphics.client.DrawingArea;

public class WorkPage extends AbsolutePanel {
	private String BACKGROUND_COLOR = "#CCCCCC";
	final private int WIDTH = getScreenWidth()-400;
	final private int HEIGHT = getScreenHeight()-400;
	final private int WIDTH_FULL = getScreenWidth();
	final private int HEIGHT_FULL = getScreenHeight();
	
	final private DrawingArea workArea = new DrawingArea(WIDTH, HEIGHT);
	
	private WorkNode root;
	
	public WorkPage() {
		setSize(WIDTH+"px", HEIGHT+"px");
		getElement().getStyle().setBackgroundColor(BACKGROUND_COLOR);
		add(workArea);
		root =  new WorkNode(null, workArea, this);
		root.setDescription("Any Idea?");
		
		sinkEvents(Event.ONDBLCLICK);
	}
	
	public DrawingArea getDrawingArea() {
		return workArea;
	}
	
	@Override
	public void onBrowserEvent(Event event) {
		event.preventDefault();
		
		switch (DOM.eventGetType(event)) {
		case Event.ONDBLCLICK:
			switchPanelSize();
			break;
		default:
			break;
		}
		super.onBrowserEvent(event);
	}
	
	public WorkNode	getRootNode() {
		return root;
	}
	
	private void switchPanelSize() {
		if(getOffsetWidth() == WIDTH_FULL && getOffsetHeight() == HEIGHT_FULL) {
			setSize(WIDTH+"px", HEIGHT+"px");
			workArea.setPixelSize(WIDTH, HEIGHT);
		} else {
			setSize(WIDTH_FULL+"px", HEIGHT_FULL+"px");
			workArea.setPixelSize(WIDTH_FULL, HEIGHT_FULL);
		}
		root.refreshPositions(root.getMaxColumn()+1);
	}
	
	public WorkTO getWorkTO() {
		WorkNode root = getRootNode();
		WorkTO workTO = new WorkTO();
		doWorkTO(root, workTO);
		return workTO;
	}
	
	private WorkTO doWorkTO(WorkNode node, WorkTO workTO) {
		
		if(node.getNodeChild1() != null) {
			doWorkTO(node.getNodeChild1(), workTO);
		}
		
		workTO.addNodeTO(node.getNodeTO());
		
		if(node.getNodeChild2() != null) {
			doWorkTO(node.getNodeChild2(), workTO);
		}
		
		return workTO;
	}
	
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
