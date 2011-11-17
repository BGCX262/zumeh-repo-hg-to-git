package com.es.zumeh.client.view.pages.work;

import java.util.ArrayList;

import com.es.zumeh.client.model.to.CommentTO;
import com.es.zumeh.client.model.to.RevisionTO;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.VerticalPanel;

public abstract class WorkPage implements EntryPoint {
	final VerticalPanel verticalWorkPanel = new VerticalPanel();
	private RevisionTO revisionTO = new RevisionTO();
	private ArrayList<CommentTO> commentsTO = new ArrayList<CommentTO>();
	
	public RevisionTO getRevisionTO() {
		return this.revisionTO;
	}
	
	public void setRevisionTO(RevisionTO revisionTO) {
		this.revisionTO = revisionTO;
	}
	
	public ArrayList<CommentTO> getCommentsTO() {
		return this.commentsTO;
	}
	
	public void setCommentsTO(ArrayList<CommentTO> commentsTO) {
		this.commentsTO = commentsTO;
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
