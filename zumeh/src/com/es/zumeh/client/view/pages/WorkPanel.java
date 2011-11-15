package com.es.zumeh.client.view.pages;

import java.util.ArrayList;

import com.es.zumeh.client.model.to.NodeTO;
import com.es.zumeh.client.model.to.WorkTO;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;

import org.vaadin.gwtgraphics.client.DrawingArea;

public class WorkPanel extends AbsolutePanel {
	private String BACKGROUND_COLOR = "#CCCCCC";
	final private int WIDTH = getScreenWidth()-400;
	final private int HEIGHT = getScreenHeight()-400;
	final private int WIDTH_FULL = getScreenWidth();
	final private int HEIGHT_FULL = getScreenHeight();
	private WorkNode workaround = null; // Gambiarra pra o find. =P
	
	final private DrawingArea workArea = new DrawingArea(WIDTH, HEIGHT);
	
	private WorkNode root;
	
	public WorkPanel() {
		setSize(WIDTH+"px", HEIGHT+"px");
		//getElement().getStyle().setBackgroundColor(BACKGROUND_COLOR);
		getElement().getStyle().setBorderColor("#000000");
		getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		getElement().getStyle().setBorderWidth(1, Unit.PX);
		add(workArea);
		root =  new WorkNode(null, workArea, this);
		root.setDescription("Any Idea?");
		System.out.println("RootId: " + root.getNodeId());
		
		sinkEvents(Event.ONDBLCLICK);
		//setWorkFromWorkTO(null, 1);
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
	
	public WorkNode getWorkById(int nodeId) {
		WorkNode root = getRootNode();
		System.out.println("Get Work-> root: " + root.getShortDescription() + " NodeId: " + root.getNodeId());
		this.workaround = null;
		doGetWorkById(root, nodeId);
		return this.workaround;
	}
	
	private WorkNode doGetWorkById(WorkNode node, int nodeId) {
		System.out.println("Find Node: " + node);
		if(node.getNodeId() == nodeId) {
			System.out.println("Caiu: " + node);
			workaround = node;
		}
		
		if(node.getNodeChild1() != null) {
			doGetWorkById(node.getNodeChild1(), nodeId);
		}
		
		if(node.getNodeChild2() != null) {
			doGetWorkById(node.getNodeChild2(), nodeId);
		}
		
		return null;
	}
	
	public void setWorkFromWorkTO(WorkTO workTO) {
		// TODO
		ArrayList<NodeTO> tmpWorkTO = workTO.getTONodes();
		
		
		NodeTO rootNodeTO = workTO.getNodeTOByNodeId(1);
		
		System.out.println("WorkTO: " + workTO);
		System.out.println("NodeTO: " + rootNodeTO);
		
		add(workArea);
		root =  new WorkNode(null, workArea, this);
		root.setShortDescription(rootNodeTO.getShortDescription());
		root.setFullText(rootNodeTO.getFullText());
		root.setStatus(rootNodeTO.getNodeStatus()); 
		root.setNodeId(rootNodeTO.getNodeId());
		
		for (int i = 1; i < tmpWorkTO.size(); i++) {
			int leftChildId = workTO.getNodeTOByNodeId(i).getLeftChildNodeId();
			int rightChildId = workTO.getNodeTOByNodeId(i).getRightChildNodeId();
			
			NodeTO leftChild = workTO.getNodeTOByNodeId(leftChildId);
			NodeTO rightChild = workTO.getNodeTOByNodeId(rightChildId);
			
			System.out.println("parentId: " + i + " leftChildId: " + leftChildId + " rightChildId: " + rightChildId);
			System.out.println("NodeTOLeft: " + leftChild);
			System.out.println("NodeTORight: " + rightChild);
			createNode(getWorkById(i), leftChild, rightChild);
		}
		
		root.refreshPositions(root.getMaxColumn()+1);
	}
	
	private void createNode(WorkNode parent, NodeTO leftChildTO, NodeTO rightChildTO) {
		if(leftChildTO != null) {
			WorkNode leftChild = new WorkNode(parent, workArea, this);
			leftChild.setShortDescription(leftChildTO.getShortDescription());
			leftChild.setFullText(leftChildTO.getFullText());
			leftChild.setStatus(leftChildTO.getNodeStatus()); 
			leftChild.setNodeId(leftChildTO.getNodeId());
			System.out.println("DEBUG1: leftChild: " + leftChild);
			System.out.println("Left: " + parent);
			parent.setChildNode1(leftChild);
		}
		
		if(rightChildTO != null) {
			WorkNode rightChild = new WorkNode(parent, workArea, this);
			rightChild.setShortDescription(rightChildTO.getShortDescription());
			rightChild.setFullText(rightChildTO.getFullText());
			rightChild.setStatus(rightChildTO.getNodeStatus());
			rightChild.setNodeId(rightChildTO.getNodeId());
			System.out.println("DEBUG1: rightChild: " + rightChild);
			System.out.println("Right: " + parent);
			parent.setChildNode2(rightChild);
		}
		
		if(parent != null) {
			//System.out.println("DEBUG1: parent: " + parent);
			parent.refreshPositions(parent.getMaxColumn()+1);
		}
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
