package com.es.zumeh.client.view.pages;

import com.es.zumeh.client.model.to.RevisionTO;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class WorkPage implements EntryPoint {
	final VerticalPanel verticalWorkPanel = new VerticalPanel();
	
	protected RevisionTO getRevisionTO() {
		RevisionTO revisionTO = new RevisionTO();
		return revisionTO;
	}
	
	abstract void refreshToWorkPanel();
	abstract void refreshAllComponents();
	
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
