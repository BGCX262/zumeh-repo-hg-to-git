package com.es.zumeh.client.view.pages;

import com.allen_sauer.gwt.dnd.client.PickupDragController;
import com.es.zumeh.client.facade.ZumehService;
import com.es.zumeh.client.facade.ZumehServiceAsync;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style.BorderStyle;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.MenuItem;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.orange.links.client.DiagramController;
import com.orange.links.client.connection.Connection;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.Timer;


public class WorkPage implements EntryPoint {
	private RootPanel rootPanel = RootPanel.get();
	private DiagramController controller = new DiagramController(getScreenWidth()-400, getScreenHeight()-400);
	private final ZumehServiceAsync zumehService = GWT.create(ZumehService.class);
	private PickupDragController dragController = new PickupDragController(controller.getView(), true);
	
	@Override
	public void onModuleLoad() {
    	controller.showGrid(true);
    	
    	RootPanel.get().add(controller.getView());
    	
    	Widget v1 = addVertex(200, 100, "#0000ff", "hello");
    	Widget v2 = addVertex(100, 200, "#ff0000", "word");
    	Widget v3 = addVertex(200, 300, "#00ff00", "fuuuuu");
    	
    	
    	connectVertex(v1, v2);
    	connectVertex(v3, v2);
    	
    	controller.registerDragController(dragController);
    	addMenu();
    	
	}
	
	private void addMenu() {
			final MenuItem menuItem = new MenuItem("Create Node", new Command() {
			
			@Override
			public void execute() {
				
				final DialogBox dialogBox = new DialogBox();
				dialogBox.setText("Adding new node...");
				dialogBox.setAnimationEnabled(true);
				
				final Button closeButton = new Button("Ok");
				closeButton.getElement().setId("okButton");
				
				final VerticalPanel dialogVPanel = new VerticalPanel();
				dialogVPanel.addStyleName("dialogVPanel");
				dialogVPanel.add(new HTML("<b>Description:</b>"));
				
				final TextBox emailTextBox = new TextBox();
				emailTextBox.setPixelSize(100, 15);
				emailTextBox.setMaxLength(15);
				emailTextBox.setEnabled(true);
				emailTextBox.setFocus(true);
				
				dialogVPanel.add(emailTextBox);
				dialogVPanel.add(closeButton);
				dialogBox.setWidget(dialogVPanel);
				
				dialogBox.center();
				dialogBox.setVisible(true);
				
				closeButton.setFocus(true);
				
				closeButton.addClickHandler(new ClickHandler() {
					public void onClick(ClickEvent event) {
						dialogBox.hide();
						controller.deleteWidget(dialogBox);
						System.out.println();
						addVertex(controller.getMousePoint().getLeft(),
								controller.getMousePoint().getTop(), "#00ff00",
								emailTextBox.getText());
					}
				});
				
				controller.getContextMenu().hide();
				controller.addWidgetAtMousePoint(dialogBox);
			}
		});
		controller.getContextMenu().addItem(menuItem);
	}
	
	private void connectVertex(Widget v1, Widget v2) {
		Connection c1 = controller.drawStraightArrowConnection(v2, v1);
		c1.drawHighlight();
		Label decorationLabel = new Label();
    	controller.addDecoration(decorationLabel, c1);
	}
	
	private Widget addVertex(int left, int top, String color, String name) {
		Widget vertex = new Label(name);
		vertex.getElement().getStyle().setPadding(10, Unit.PX);
		vertex.getElement().getStyle().setBorderStyle(BorderStyle.SOLID);
		vertex.getElement().getStyle().setBorderColor("#bbbbbb");
		vertex.getElement().getStyle().setBorderWidth(1, Unit.PX);
		vertex.getElement().getStyle().setBackgroundColor(color);
		vertex.setPixelSize(100, 20);
		controller.addWidget(vertex, left, top);
		dragController.makeDraggable(vertex);
		return vertex;
	}
	
	public static native int getScreenWidth() /*-{ 
		return $wnd.screen.width;
	}-*/;

	public static native int getScreenHeight() /*-{ 
		return $wnd.screen.height;
	}-*/;
	
	private void clear() {
		controller.clearDiagram();
	}
	
	private void timer() {
		Timer timer = new Timer() {
            @Override
            public void run() {
            	System.out.println(controller.getMousePoint());
            }
    	};
    	timer.scheduleRepeating(50);
	}
}
