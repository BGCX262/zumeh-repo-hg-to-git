package com.es.zumeh.client.view.pages;

import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.DeckPanel;
import com.google.gwt.user.client.ui.FocusPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuBar;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

public class WorkNode extends Label  implements NodeClickListener, NodeClickNotifier{
	final private PopupPanel popupPanel = new PopupPanel(true);
	private DeckPanel deckPanel = new DeckPanel();
	//private VerticalPanel defaultPanel = new VerticalPanel();
	//private SimplePanel imagePanel = new SimplePanel();
	//private SimplePanel sponserPanel = new SimplePanel();
	
	public WorkNode(FocusPanel panel) {
		  super();
		  sinkEvents(Event.ONCONTEXTMENU);
		  createPopupMenu();
		}
		 
		public void onBrowserEvent(Event event) {
		  //event.cancelBubble(true);//This will stop the event from being propagated
		  //event.preventDefault();
		  switch (DOM.eventGetType(event)) {
		    case Event.ONMOUSEUP:
		      if (DOM.eventGetButton(event) == Event.BUTTON_LEFT) {
				onClick(this, event);
		      }
		 
		      /*if (DOM.eventGetButton(event) == Event.BUTTON_RIGHT) {
		        //onRightClick(this, event);
		      }*/
		      break;
		    /*case Event.ONDBLCLICK:
		      break;*/
		 
		    case Event.ONCONTEXTMENU:
		    	System.out.println("skfkjhkjh");
		    	onRightClick(this, event);
		      break;
		    default:
		    	super.onBrowserEvent(event);
		    	break;
		  }//end switch
		}

	@Override
	public void addClickListener(NodeClickListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeClickListener(NodeClickListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(Widget sender, Event event) {
		// TODO Auto-generated method stub
		
	}

	private void createPopupMenu() {
		  MenuBar popupMenuBar = new MenuBar(true);
		  MenuItem removeNodeItem = new MenuItem("Remove Node", true, removeNodeCommand);
		  MenuItem editFullContentItem = new MenuItem("Edit Full Content", true, editFullContentCommand);
		  MenuItem editNodeDescriptionItem = new MenuItem("Edit Node Description", true, editNodeDescriptionCommand);
		 
		  popupPanel.setStyleName("popup");
		  removeNodeItem.addStyleName("popup-item");
		  editFullContentItem.addStyleName("popup-item");
		  editNodeDescriptionItem.addStyleName("popup-item");
		 
		  popupMenuBar.addItem(removeNodeItem);
		  popupMenuBar.addItem(editFullContentItem);
		  popupMenuBar.addItem(editNodeDescriptionItem);
		 
		  popupMenuBar.setVisible(true);
		  popupPanel.add(popupMenuBar);
		  popupPanel.setVisible(false);
		}
		
	@Override
		public void onRightClick(Widget sender, Event event) {
		  int x = DOM.eventGetClientX(event);
		  int y = DOM.eventGetClientY(event);
		  popupPanel.setPopupPosition(x, y);
		  popupPanel.show();
		  popupPanel.setVisible(true);
		}

	@Override
	public void onClick(Widget sender) {
		// TODO
	}
	
	Command removeNodeCommand = new Command() {
		  public void execute() {
		    //deckPanel.showWidget(0);
			System.out.println("Remove Node.");
		    popupPanel.hide();
		  }
		};
		 
		Command editFullContentCommand = new Command() {
		  public void execute() {
		    //deckPanel.showWidget(1);
			System.out.println("Edit Full Content.");
		    popupPanel.hide();
		  }
		};
		 
		Command editNodeDescriptionCommand = new Command() {
		  public void execute() {
		    //deckPanel.showWidget(2);
			System.out.println("Edit Node Description.");
		    popupPanel.hide();
		  }
		};
		
	public PopupPanel getPopupPanel() {
		return popupPanel;
	}
}
