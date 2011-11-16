package com.es.zumeh.client.view.pages;

import org.vaadin.gwtgraphics.client.DrawingArea;
import org.vaadin.gwtgraphics.client.Line;
import org.vaadin.gwtgraphics.client.VectorObject;
import org.vaadin.gwtgraphics.client.shape.Ellipse;

import com.es.zumeh.client.model.to.NodeTO;
import com.google.gwt.dom.client.Style.Position;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.TextArea;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;

/**
 * This class is a vortex, if try to read this, will face the evil.
 * So, don't do that!!!
 * 
 * Keep distance.
 * And see that it works. =)
 * 
 */
public class WorkWriteNode extends Ellipse {
	final static private int RADIUS_X = 80;
	final static private int RADIUS_Y = 30;
	final static private int COLUMN_SIZE = 220;
	final static private int LINE_SIZE = 40;
	final static private int ROOT_COLUMN_SIZE = 90;
	final static private String GREEN = "green";
	final static private String RED = "red";
	final static private String YELLOW = "yellow";
	final static private int MAX_DESCRIPTION_SIZE = 17;
	final static private int CARACTER_SIZE = 7;
	
	private int column = 0;
	private int childs = 0;
	private int nodeId = 0;
	private WorkWriteNode nodeParent;
	private WorkWriteNode nodeChild1;
	private WorkWriteNode nodeChild2;
	private DrawingArea workArea;
	private Label description;
	//private NodeTO nodeTO;
	private String nodeStatus = GREEN;
	static private AbsolutePanel absolutePanel;
	
	final private PopupPanel popupPanel = new PopupPanel(true);
	
	private String descriptionText;
	private String fullText;

	public WorkWriteNode(WorkWriteNode nodeParent, DrawingArea workArea, AbsolutePanel absolutePanel) {
		super(10, 10, RADIUS_X, RADIUS_Y);
		configNode(nodeParent, workArea);
		this.nodeParent = nodeParent;
		this.workArea = workArea;
		WorkWriteNode.absolutePanel = absolutePanel;
		
		setFillColor(GREEN);
		setFillOpacity(0.8);
		
		sinkEvents(Event.ONCONTEXTMENU | Event.ONCLICK);
		
		createPopupMenu();
		
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
		case Event.ONCONTEXTMENU:
			showMenu(event);
			break;
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
		WorkWriteNode newWorkNode = new WorkWriteNode(this, this.workArea, absolutePanel);
		newWorkNode.setDescription(description);
		//addChild(newWorkNode); FIXME Uncomment this.
		createConnection(newWorkNode);
	}
	
	public void setChildNode1(WorkWriteNode newChild) {
		this.nodeChild1 = newChild;
	}
	
	public void setChildNode2(WorkWriteNode newChild) {
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
	
	private void addChild(WorkWriteNode node) {
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
	
	public WorkWriteNode getNodeParent() {
		return this.nodeParent;
	}
	
	public void refreshAll() {
		refreshPositions(getMaxColumn()+1);
	}
	
	public void refreshPositions(int column) {
		WorkWriteNode node = getRoot();
		clearConnections();
		node.doRefresh(column);
	}
	
	public WorkWriteNode getNodeChild1() {
		return this.nodeChild1;
	}
	
	public WorkWriteNode getNodeChild2() {
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
		WorkWriteNode node = getRoot();
		return doPath(node);
	}
	
	/* 
	 * private methods. 
	 */
	private int doPath(WorkWriteNode node) {
		if(node == null) {
			return 0;
		}
		
		int col1 = doPath(node.getNodeChild1());
		int col2 = doPath(node.getNodeChild2());
		
		return (1 + Math.max(col1, col2));
	}
	
	private void setNodeId() {
		WorkWriteNode node = getRoot();
		this.nodeId = doWorkSize(node);
	}
	
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
	private int doWorkSize(WorkWriteNode node) {
		if(node == null) {
			return 0;
		}
		
		int size_left = doWorkSize(node.getNodeChild1());
		int size_right = doWorkSize(node.getNodeChild2());
		
		return (size_left + size_right + 1);
	}
	
	private void showMenu(Event event) {
		int x = DOM.eventGetClientX(event);
		int y = DOM.eventGetClientY(event);
		popupPanel.setPopupPosition(x, y);
		popupPanel.show();
		popupPanel.setVisible(true);
	}
	
	private void createFullText() {
		// deckPanel.showWidget(1);
		
		// ================== Dialog Box ========================
		final DialogBox fullTextBox = new DialogBox();
		fullTextBox.setText("Adding new sdfsdfksdjhfnode...");
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
	
	private void configNode(WorkWriteNode parent, DrawingArea workArea) {
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
	
	private void setY(WorkWriteNode parent, DrawingArea workArea) {
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
	
	private void setX(WorkWriteNode parent) {
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
	
	private void createConnection(WorkWriteNode child) {
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
	
	private WorkWriteNode getRoot() {
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
			tmpNodeTO.setFullText(this.fullText);
			tmpNodeTO.setShortDescription(this.descriptionText);
			tmpNodeTO.setNodeStatus(this.nodeStatus);
			
			if(getNodeChild1() != null) {
				tmpNodeTO.setLeftChildNodeId(getNodeChild1().getNodeId());
			}
			if(getNodeChild2() != null) {
				tmpNodeTO.setRightChildNodeId(getNodeChild2().getNodeId());
			}
			tmpNodeTO.setNodeId(getNodeId());
			
			return tmpNodeTO;
	}
	
	public int getNodeId() {
		return nodeId;
	}
	
	private void createPopupMenu() {
		// REMEBER This should be broken into methods.
		/* Node Status */
		MenuBar popupSubMenuStatusBar = new MenuBar(true);
		MenuItem greenStatusItem = new MenuItem("Green", true, editNodeStatusGreenCommand);
		MenuItem yellowStatusItem = new MenuItem("Yellow", true, editNodeStatusYellowCommand);
		MenuItem redStatusItem = new MenuItem("Red", true, editNodeStatusRedCommand);
		popupSubMenuStatusBar.addItem(greenStatusItem);
		popupSubMenuStatusBar.addItem(yellowStatusItem);
		popupSubMenuStatusBar.addItem(redStatusItem);
		
		/* Main Context Menu */
		MenuBar popupMenuBar = new MenuBar(true);
		MenuItem createNodeItem = new MenuItem("Create New Child Node", true, createNodeCommand);
		MenuItem removeNodeItem = new MenuItem("Remove Node", true, removeNodeCommand);
		MenuItem editFullContentItem = new MenuItem("Edit Full Content", true, editFullContentCommand);
		MenuItem editNodeDescriptionItem = new MenuItem("Edit Node Description", true, editNodeDescriptionCommand);
		MenuItem editNodeStatusItem = new MenuItem("Edit Node Status", true, popupSubMenuStatusBar);
		
		/* Style */
		popupPanel.setStyleName("popup");
		removeNodeItem.addStyleName("popup-item");
		editFullContentItem.addStyleName("popup-item");
		editNodeDescriptionItem.addStyleName("popup-item");
		editNodeStatusItem.addStyleName("popup-item");
		greenStatusItem.addStyleName("popup-item");
		yellowStatusItem.addStyleName("popup-item");
		redStatusItem.addStyleName("popup-item");
		createNodeItem.addStyleName("popup-item");
		
		/* Add itens */
		popupMenuBar.addItem(createNodeItem);
		popupMenuBar.addItem(removeNodeItem);
		popupMenuBar.addItem(editNodeDescriptionItem);
		popupMenuBar.addItem(editFullContentItem);
		popupMenuBar.addItem(editNodeStatusItem);
		
		popupMenuBar.setVisible(true);
		popupPanel.add(popupMenuBar);
		popupPanel.setVisible(false);
		}
	
	/* Context Menu commands. */
	Command removeNodeCommand = new Command() {
		public void execute() {
			// deckPanel.showWidget(0);
			popupPanel.hide();
			
			removeNode();
		}
	};

	Command editFullContentCommand = new Command() {
		public void execute() {
			// deckPanel.showWidget(1);
			popupPanel.hide();
			
			// ================== Dialog Box ========================
			final DialogBox dialogBox = new DialogBox();
			dialogBox.setText("Editing Node Description...");
			dialogBox.setAnimationEnabled(true);
			
			final Button closeButton = new Button("Ok");
			closeButton.getElement().setId("okButtonEditFullContent");
			
			final Button cancelButton = new Button("Cancel");
			cancelButton.getElement().setId("cancelButtonEditFullContent");
			
			final VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.addStyleName("dialogVPanel");
			dialogVPanel.add(new HTML("<b>Description:</b>"));
			
			final HorizontalPanel d = new HorizontalPanel();
			
			final TextArea textBox = new TextArea();
			textBox.setPixelSize(300, 300);
			textBox.setEnabled(true);
			textBox.setFocus(true);
			
			dialogVPanel.add(textBox);
			dialogBox.setWidget(dialogVPanel);
			
			d.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
			d.add(closeButton);
			d.add(cancelButton);
			dialogVPanel.add(d);
			
			dialogBox.center();
			dialogBox.setVisible(true);
			
			closeButton.setFocus(true);
			
			cancelButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
				}
			});
			
			closeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
					fullText = textBox.getText();
				}
			});
			// ================= End of Dialog Box ===================
		}
	};

	Command editNodeDescriptionCommand = new Command() {
		public void execute() {
			// deckPanel.showWidget(2);
			popupPanel.hide();
			// ================== Dialog Box ========================
			final DialogBox dialogBox = new DialogBox();
			dialogBox.setText("Editing Node Description...");
			dialogBox.setAnimationEnabled(true);
			
			final Button closeButton = new Button("Ok");
			closeButton.getElement().setId("okButton");
			
			final Button cancelButton = new Button("Cancel");
			cancelButton.getElement().setId("cancelButtonEditFullContent");
			
			final VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.addStyleName("dialogVPanel");
			dialogVPanel.add(new HTML("<b>Description:</b>"));
			
			final TextBox textBox = new TextBox();
			textBox.setPixelSize(150, 15);
			textBox.setMaxLength(MAX_DESCRIPTION_SIZE);
			textBox.setEnabled(true);
			textBox.setFocus(true);
			
			dialogVPanel.add(textBox);
			dialogBox.setWidget(dialogVPanel);
			//dialogVPanel.add(closeButton);
			
			final HorizontalPanel d = new HorizontalPanel();
			d.setHorizontalAlignment(HorizontalPanel.ALIGN_LEFT);
			d.add(closeButton);
			d.add(cancelButton);
			dialogVPanel.add(d);
			
			dialogBox.center();
			dialogBox.setVisible(true);
			
			closeButton.setFocus(true);
			
			closeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
					setDescription(textBox.getText());
				}
			});
			
			cancelButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
				}
			});
			// ================= End of Dialog Box ===================
		}
	};
	
	Command editNodeStatusGreenCommand = new Command() {
		public void execute() {
			// deckPanel.showWidget(3);
			setStatus(GREEN);
			//setFillColor(GREEN);
			//nodeStatus = GREEN;
			popupPanel.hide();
		}
	};
	
	Command editNodeStatusYellowCommand = new Command() {
		public void execute() {
			// deckPanel.showWidget(4);
			setStatus(YELLOW);
			//setFillColor(YELLOW);
			//nodeStatus = YELLOW;
			popupPanel.hide();
		}
	};
	
	Command editNodeStatusRedCommand = new Command() {
		public void execute() {
			// deckPanel.showWidget(5);
			//setFillColor(RED);
			setStatus(RED);
			//nodeStatus = RED;
			popupPanel.hide();
		}
	};
	
	Command createNodeCommand = new Command() {
		public void execute() {
			// deckPanel.showWidget(6);
			popupPanel.hide();
			
			// ================== Dialog Box ========================
			final DialogBox dialogBox = new DialogBox();
			dialogBox.setText("Adding new node...");
			dialogBox.setAnimationEnabled(true);
			
			final Button closeButton = new Button("Ok");
			closeButton.getElement().setId("okButton");
			
			final Button cancelButton = new Button("Cancel");
			cancelButton.getElement().setId("cancelButtonCreateNode");
			
			final VerticalPanel dialogVPanel = new VerticalPanel();
			dialogVPanel.addStyleName("dialogVPanel");
			dialogVPanel.add(new HTML("<b>Description:</b>"));
			
			final TextBox textBox = new TextBox();
			textBox.setPixelSize(150, 15);
			textBox.setMaxLength(MAX_DESCRIPTION_SIZE);
			textBox.setEnabled(true);
			textBox.setFocus(true);
			
			final HorizontalPanel d = new HorizontalPanel();
			
			dialogVPanel.add(textBox);
			dialogBox.setWidget(dialogVPanel);
			d.add(closeButton);
			d.add(cancelButton);
			dialogVPanel.add(d);
			
			dialogBox.center();
			dialogBox.setVisible(true);
			
			closeButton.setFocus(true);
			
			closeButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
					addChildNode(textBox.getText());
				}
			});
			
			cancelButton.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					dialogBox.hide();
				}
			});
			// ================= End of Dialog Box ===================
		}
	};
	
	@Override
	public String toString() {
		return "Short Description: " + this.descriptionText + " Node Id: "
				+ this.nodeId;
	}
}
