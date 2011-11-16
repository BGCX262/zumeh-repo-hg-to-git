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

public class WorkReadOnlyPanel extends AbsolutePanel {
	private String BACKGROUND_COLOR = "#CCCCCC";
	final private int WIDTH = getScreenWidth()-400;
	final private int HEIGHT = getScreenHeight()-400;
	final private int WIDTH_FULL = getScreenWidth();
	final private int HEIGHT_FULL = getScreenHeight();
	private WorkReadOnlyNode workaround = null; // Gambiarra pra o find. =P
	
	final private DrawingArea workArea = new DrawingArea(WIDTH, HEIGHT);
	private WorkPage workPage;
	private WorkReadOnlyNode root;
	private boolean isFullSizePanel = false;
	
	public WorkReadOnlyPanel(WorkPage workPage) {
		setSize(WIDTH+"px", HEIGHT+"px");
		getElement().getStyle().setBorderColor("#000000");
		getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		getElement().getStyle().setBorderWidth(1, Unit.PX);
		add(workArea);
		this.workPage = workPage;
		root =  new WorkReadOnlyNode(null, workArea, this);
		root.setDescription("Any Idea?");
		System.out.println("RootId: " + root.getNodeId());
		
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
	
	public WorkReadOnlyNode getRootNode() {
		return root;
	}
	
	private void switchPanelSize() {
		if(isFullSizePanel) {
			setSize(WIDTH+"px", HEIGHT+"px");
			workArea.setPixelSize(WIDTH, HEIGHT);
			this.workPage.refreshAllComponents();
			isFullSizePanel = false;
		} else {
			setSize(WIDTH_FULL+"px", HEIGHT_FULL+"px");
			workArea.setPixelSize(WIDTH_FULL, HEIGHT_FULL);
			this.workPage.refreshToWorkPanel();
			isFullSizePanel = true;
		}
		//root.refreshPositions(root.getMaxColumn()+1);
		root.refreshAll();
	}
	
	public WorkTO getWorkTO() {
		WorkReadOnlyNode root = getRootNode();
		WorkTO workTO = new WorkTO();
		doWorkTO(root, workTO);
		return workTO;
	}
	
	public WorkReadOnlyNode getWorkById(int nodeId) {
		WorkReadOnlyNode root = getRootNode();
		System.out.println("Get Work-> root: " + root.getShortDescription() + " NodeId: " + root.getNodeId());
		this.workaround = null;
		doGetWorkById(root, nodeId);
		return this.workaround;
	}
	
	private WorkReadOnlyNode doGetWorkById(WorkReadOnlyNode node, int nodeId) {
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
		ArrayList<NodeTO> tmpWorkTO = workTO.getTONodes();
		NodeTO rootNodeTO = workTO.getNodeTOByNodeId(1);
		
		System.out.println("WorkTO: " + workTO);
		System.out.println("NodeTO: " + rootNodeTO);
		
		add(workArea);
		root =  new WorkReadOnlyNode(null, workArea, this);
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
	
	private void createNode(WorkReadOnlyNode parent, NodeTO leftChildTO, NodeTO rightChildTO) {
		if(leftChildTO != null) {
			WorkReadOnlyNode leftChild = new WorkReadOnlyNode(parent, workArea, this);
			leftChild.setShortDescription(leftChildTO.getShortDescription());
			leftChild.setFullText(leftChildTO.getFullText());
			leftChild.setStatus(leftChildTO.getNodeStatus()); 
			leftChild.setNodeId(leftChildTO.getNodeId());
			System.out.println("DEBUG1: leftChild: " + leftChild);
			System.out.println("Left: " + parent);
			parent.setChildNode1(leftChild);
		}
		
		if(rightChildTO != null) {
			WorkReadOnlyNode rightChild = new WorkReadOnlyNode(parent, workArea, this);
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
	
	private WorkTO doWorkTO(WorkReadOnlyNode node, WorkTO workTO) {
		
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
