package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.control.ClientSessionManager;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WorkIntermediario implements EntryPoint{
	final VerticalPanel workPanel = new VerticalPanel();
	
	public WorkIntermediario(ClientSessionManager clientSessionManger) {
		
	}
	
	@Override
	public void onModuleLoad() {
		// TODO Auto-generated method stub
		WorkPage wp = new WorkPage();
		RevisionPanel rp = new RevisionPanel(wp);
		CommentPanel cp = new CommentPanel();
		
		workPanel.add(rp);
		workPanel.add(wp);
		
		
		RootPanel.get().add(workPanel, 10, 200);
		RootPanel.get().add(cp, getScreenWidth()-390, 0);
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
