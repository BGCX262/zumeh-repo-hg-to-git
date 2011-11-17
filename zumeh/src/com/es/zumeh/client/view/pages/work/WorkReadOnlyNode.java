package com.es.zumeh.client.view.pages.work;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Line;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Ellipse;

import com.es.zumeh.client.model.to.NodeTO;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseDownEvent;
import com.google.gwt.event.dom.client.MouseDownHandler;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This class is a vortex, if try to read this, will face the evil.
 * So, don't do that!!!
 * 
 * Keep distance.
 * And see that it works. =)
 * 
 */
public class WorkReadOnlyNode extends Ellipse {
	final static private int RADIUS_X = 80;
	final static private int RADIUS_Y = 30;
	final static private int COLUMN_SIZE = 220;
	final static private int LINE_SIZE = 40;
	final static private int ROOT_COLUMN_SIZE = 90;
	final static private int CARACTER_SIZE = 7;
	
	private int column = 0;
	private int childs = 0;
	private Long nodeId = 0L;
	private WorkReadOnlyNode nodeParent;
	private WorkReadOnlyNode nodeChild1;
	private WorkReadOnlyNode nodeChild2;
	private DrawingArea workArea;
	private Label description;
	static private AbsolutePanel absolutePanel;
	
	private String descriptionText;
	private String fullText;
	private String nodeStatus;

	public WorkReadOnlyNode(WorkReadOnlyNode nodeParent, DrawingArea workArea, AbsolutePanel absolutePanel) {
		super(10, 10, RADIUS_X, RADIUS_Y);
		configNode(nodeParent, workArea);
		this.nodeParent = nodeParent;
		this.workArea = workArea;
		WorkReadOnlyNode.absolutePanel = absolutePanel;
		
		setFillOpacity(0.8);
		
		sinkEvents(Event.ONCLICK);
		
		this.description = new Label();
		this.description.getElement().getStyle().setFontSize(15, Unit.PX);
		absolutePanel.add(description, getX(), getY());
		refreshPositions(1+getMaxColumn());
		
		this.fullText = new String();
		
		setNodeId();
		System.out.println("Node Created: " + getNodeId());
	}
	
	@Override
	public void onBrowserEvent(Event event) {
		//event.cancelBubble(true);//This will stop the event from being
		
		/* This stop browser context menu. */
		event.preventDefault();
		
		switch (DOM.eventGetType(event)) {
		case Event.ONCLICK:
			createFullText();
			break;
		default:
			break;
		}
		super.onBrowserEvent(event);
	}
	
	public void setDescription(String name) {
		this.descriptionText = name;
		description.setText(name);
		refreshDescriptionPositio();
	}
	
	private void refreshDescriptionPositio() {
		absolutePanel.remove(this.description);
		this.description.setText(this.descriptionText);
		int posx = getX()-((this.description.getText().length()*CARACTER_SIZE)/2);
		int posy = getY()-CARACTER_SIZE;
		absolutePanel.add(this.description, posx, posy);
		absolutePanel.getElement().getStyle().setPosition(Position.RELATIVE);
	}
	
	public int getColumn() {
		return this.column;
	}
	
	public void addChildNode(String description) {
		WorkReadOnlyNode newWorkNode = new WorkReadOnlyNode(this, this.workArea, absolutePanel);
		newWorkNode.setDescription(description);
		//addChild(newWorkNode); FIXME Uncomment this.
		createConnection(newWorkNode);
	}
	
	public void setChildNode1(WorkReadOnlyNode newChild) {
		this.nodeChild1 = newChild;
	}
	
	public void setChildNode2(WorkReadOnlyNode newChild) {
		this.nodeChild2 = newChild;
	}
	
	public void removeChildNode1() {
		this.workArea.remove(this.nodeChild1);
		this.nodeChild1 = null;
	}
	
	public void removeChildNode2() {
		this.workArea.remove(this.nodeChild2);
		this.nodeChild2 = null;
	}
	
	public void removeNode() {
		//removeFromParent();
		//workArea.remove(this);
		if(this.nodeParent.getNodeChild1() != null) {
			if(this.nodeParent.getNodeChild1().equals(this)) {
				this.nodeParent.removeChildNode1();	
			}
		}
		if(this.nodeParent.getNodeChild2() != null) {
			if(this.nodeParent.getNodeChild2().equals(this)) {
				this.nodeParent.removeChildNode2();
			}
		}
		absolutePanel.remove(description);
		refreshPositions(1+getMaxColumn());
	}
	
	private void addChild(WorkReadOnlyNode node) {
		if(this.nodeChild1 == null) {
			this.nodeChild1 = node;
			this.childs++;
		} else if (this.nodeChild2 == null) {
			this.nodeChild2 = node;
			this.childs++;
		}
	}
	
	public int getChildNumber() {
		return this.childs;
	}
	
	public WorkReadOnlyNode getNodeParent() {
		return this.nodeParent;
	}
	
	public void refreshAll() {
		refreshPositions(getMaxColumn()+1);
	}
	
	public void refreshPositions(int column) {
		WorkReadOnlyNode node = getRoot();
		clearConnections();
		node.doRefresh(column);
	}
	
	public WorkReadOnlyNode getNodeChild1() {
		return this.nodeChild1;
	}
	
	public WorkReadOnlyNode getNodeChild2() {
		return this.nodeChild2;
	}
	
	public void setAutoY(int column) {
		if(this.nodeParent == null) {
			super.setY(workArea.getHeight()/2);
		} else {
			if(this.nodeParent.getNodeChild1() != null) {
				if(this.nodeParent.getNodeChild1().equals(this)) {
					super.setY(this.nodeParent.getY()-(LINE_SIZE*((int)(column-this.getColumn()))));
				}
			}
			if(this.nodeParent.getNodeChild2() != null) {
				if(this.nodeParent.getNodeChild2().equals(this)) {
					super.setY(this.nodeParent.getY()+(LINE_SIZE*((int)(column-this.getColumn()))));
				}
			}
		}
	}
	
	public int getMaxColumn() {
		WorkReadOnlyNode node = getRoot();
		return doPath(node);
	}
	
	/* 
	 * private methods. 
	 */
	private int doPath(WorkReadOnlyNode node) {
		if(node == null) {
			return 0;
		}
		
		int col1 = doPath(node.getNodeChild1());
		int col2 = doPath(node.getNodeChild2());
		
		return (1 + Math.max(col1, col2));
	}
	
	private void setNodeId() {
		WorkReadOnlyNode node = getRoot();
		this.nodeId = doWorkSize(node);
	}
	
	public void setNodeId(Long nodeId) {
		this.nodeId = nodeId;
	}
	
	private Long doWorkSize(WorkReadOnlyNode node) {
		if(node == null) {
			return 0L;
		}
		
		Long size_left = doWorkSize(node.getNodeChild1());
		Long size_right = doWorkSize(node.getNodeChild2());
		
		return (size_left + size_right + 1);
	}
	
	private void createFullText() {
		// deckPanel.showWidget(1);
		
		// ================== Dialog Box ========================
		final DialogBox fullTextBox = new DialogBox();
		fullTextBox.setText("Adding new node...");
		fullTextBox.setAnimationEnabled(true);
		
		final Button closeButton = new Button("Close");
		closeButton.getElement().setId("closeButtonFullTextRO");
		
		final VerticalPanel dialogVPanel = new VerticalPanel();
		//dialogVPanel.setPixelSize(200, 400);
		
		dialogVPanel.addStyleName("dialogVPanel");
		dialogVPanel.add(new HTML("<b>Content:</b>"));
		
		final TextArea textAreaFullText = new TextArea();
		textAreaFullText.setPixelSize(300, 300);
		textAreaFullText.setReadOnly(false);
		textAreaFullText.setFocus(true);
		textAreaFullText.setEnabled(false);
		textAreaFullText.setText(this.fullText);
		dialogVPanel.add(textAreaFullText);
		dialogVPanel.add(closeButton);
		
		fullTextBox.setWidget(dialogVPanel);
		fullTextBox.center();
		fullTextBox.setVisible(true);
		
		closeButton.setFocus(true);
		
		closeButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				fullTextBox.hide();
			}
		});
		// ================= End of Dialog Box ===================
	}


	
	private void configNode(WorkReadOnlyNode parent, DrawingArea workArea) {
		setX(parent);
		setY(parent, workArea);
		//setAutoY(getMaxColumn()+1);
		
		workArea.add(this);
		
		if(parent != null) {
			parent.addChild(this); // FIXME Comment this.
			this.column = parent.getColumn()+1;
		} else {
			this.column = 1;
		}
	}
	
	private void setY(WorkReadOnlyNode parent, DrawingArea workArea) {
		if(parent == null) {
			super.setY(workArea.getHeight()/2);
		} else {
			if(parent.getChildNumber() == 0) {
				super.setY(parent.getY() - LINE_SIZE);
			} else {
				super.setY(parent.getY() + LINE_SIZE);
			}
		}
	}
	
	private void setX(WorkReadOnlyNode parent) {
		if(parent == null) {
			super.setX(ROOT_COLUMN_SIZE);
		} else {
			super.setX(parent.getX() + COLUMN_SIZE);
		} 
	}
	
	private void doRefresh(int column) {
		setAutoY(column);
		if(nodeChild1 != null) {
			nodeChild1.doRefresh(column);
			createConnection(nodeChild1);
		}
		refreshDescriptionPositio();
		
		if(nodeChild2 != null) {
			nodeChild2.doRefresh(column);
			createConnection(nodeChild2);
		} 
	}
	
	private void createConnection(WorkReadOnlyNode child) {
		if(child != null ) {
			int parentX = this.getX() + this.getRadiusX();
			int parentY = this.getY();
			
			int childX = child.getX() - child.getRadiusX();
			int childY = child.getY();
			Line l = new Line(parentX, parentY, childX, childY);
			this.workArea.add(l);
		}
	}
	
	private void clearConnections() {
		int objQtd = workArea.getVectorObjectCount();
		VectorObject[] vo = new VectorObject[objQtd];
		
		for (int i = 0; i < objQtd; i++) {
			vo[i] = workArea.getVectorObject(i);
		}
		
		for (VectorObject tmpVo : vo) {
			if(tmpVo instanceof Line) {
				workArea.remove(tmpVo);
			}
				
		}
	}
	
	private WorkReadOnlyNode getRoot() {
		if(this.nodeParent == null){
			return this;
		} else {
			return this.nodeParent.getRoot();
		}
	}
	
	public void setShortDescription(String shortDescription) {
		this.descriptionText = shortDescription;
	}
	
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}
	
	public String getFullText() {
		return this.fullText;
	}
	
	public String getShortDescription() {
		return this.descriptionText;
	}
	
	public void setStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
		setFillColor(nodeStatus);
	}
	
	public String getStatus() {
		return this.nodeStatus;
	}
	
	public NodeTO getNodeTO() {
			NodeTO tmpNodeTO = new NodeTO();
			tmpNodeTO.setFullNodeText(this.fullText);
			tmpNodeTO.setShortNodeDescription(this.descriptionText);
			tmpNodeTO.setNodeStatus(this.nodeStatus);
			
			if(getNodeChild1() != null) {
				tmpNodeTO.setLeftChildNodeId(getNodeChild1().getNodeId());
			}
			if(getNodeChild2() != null) {
				tmpNodeTO.setRightChildNodeId(getNodeChild2().getNodeId());
			}
			tmpNodeTO.setId(getNodeId());
			
			return tmpNodeTO;
	}
	
	public Long getNodeId() {
		return nodeId;
	}
	
	@Override
	public String toString() {
		return "Short Description: " + this.descriptionText + " Node Id: "
				+ this.nodeId;
	}
}
