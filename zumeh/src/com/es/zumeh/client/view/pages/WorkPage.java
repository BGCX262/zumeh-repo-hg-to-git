package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.control.ClientSessionManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;
import com.google.gwt.user.client.ui.AbsolutePanel;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import org.vaadin.gwtgraphics.client.DrawingArea;

public class WorkPage extends AbsolutePanel implements EntryPoint {
	private String BACKGROUND_COLOR = "#CCCCCC";
	final private int WIDTH = getScreenWidth() - 400;
	final private int HEIGHT = getScreenHeight() - 400;
	final private int WIDTH_FULL = getScreenWidth();
	final private int HEIGHT_FULL = getScreenHeight();
	final DrawingArea workArea = new DrawingArea(WIDTH, HEIGHT);

	final VerticalPanel workPanel = new VerticalPanel();
	private WorkNode root;

	private ClientSessionManager clientSessionManger;

	public WorkPage(ClientSessionManager clientSessionManger) {
		this.clientSessionManger = clientSessionManger; //FIXME AJEITAR
	}

	public WorkPage() {
		setSize(WIDTH + "px", HEIGHT + "px");
		getElement().getStyle().setBackgroundColor(BACKGROUND_COLOR);
		workPanel.add(workArea);
		add(workPanel);

		root = new WorkNode(null, workArea, this);
		root.setDescription("Any Idea?");

		sinkEvents(Event.ONDBLCLICK);
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

	private void switchPanelSize() {
		if (getOffsetWidth() == WIDTH_FULL && getOffsetHeight() == HEIGHT_FULL) {
			setSize(WIDTH + "px", HEIGHT + "px");
			workArea.setPixelSize(WIDTH, HEIGHT);
		} else {
			setSize(WIDTH_FULL + "px", HEIGHT_FULL + "px");
			workArea.setPixelSize(WIDTH_FULL, HEIGHT_FULL);
		}
		root.refreshPositions(root.getMaxColumn() + 1);
	}

	/*
	 * Natives
	 */
	public static native int getScreenWidth() /*-{
		return $wnd.screen.width;
	}-*/;

	public static native int getScreenHeight() /*-{
		return $wnd.screen.height;
	}-*/;

	@Override
	public void onModuleLoad() {
		WorkPage wp = new WorkPage();
		RootPanel.get().add(wp, 10, 10);
	}
}
