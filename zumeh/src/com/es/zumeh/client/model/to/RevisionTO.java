package com.es.zumeh.client.model.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class RevisionTO implements Serializable {
	
	private static final long serialVersionUID = 8424271182195040573L;
	private LinkedList<WorkTO> workTOList = new LinkedList<WorkTO>();
	private int revisionId = 0;
	
	public RevisionTO(){}
	
	public void addWork(WorkTO workTO) {
		this.workTOList.add(workTO);
	}
	
	public WorkTO getWork(int workIdentification) {
		for (WorkTO tmpWorkTO : workTOList) {
			if(tmpWorkTO.getWorkId() == workIdentification) {
				return tmpWorkTO;
			}
		}
		
		return null;
	}
	
	public void setWorkTOList(LinkedList<WorkTO> workTOList) {
		this.workTOList = workTOList;
	}
	
	public LinkedList<WorkTO> getTOWorks() {
		return this.workTOList;
	}
	
	public void setRevisionId(int revisionId) {
		this.revisionId = revisionId;
	}
	
	public int getRevisionId() {
		return this.revisionId;
	}
	
	@Override
	public String toString() {
		return "Size: " + this.workTOList +
				" RevisionId: " + this.revisionId;
	}
}
