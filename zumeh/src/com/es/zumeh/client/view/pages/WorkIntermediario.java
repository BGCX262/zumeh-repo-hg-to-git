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
		
		workPanel.add(rp);
		workPanel.add(wp);
		
		
		RootPanel.get().add(workPanel, 10, 200);
	}

}
