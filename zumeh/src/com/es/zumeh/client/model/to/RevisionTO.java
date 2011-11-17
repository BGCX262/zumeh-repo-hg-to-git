package com.es.zumeh.client.model.to;

import java.io.Serializable;
import java.util.LinkedList;

public class RevisionTO implements Serializable {
	
	private static final long serialVersionUID = 8424271182195040573L;
	
	private LinkedList<WorkTO> workTOList = new LinkedList<WorkTO>();
	private Long revisionId = 0L;
	private String fullDescriptionRevisionText;
	private String shortDescriptionRevisionText;
	
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
	
	public void setRevisionId(Long revisionId) {
		this.revisionId = revisionId;
	}
	
	public Long getRevisionId() {
		return this.revisionId;
	}
	
	public String getFullDescriptionText() {
		if(this.fullDescriptionRevisionText == null) {
			return "Full Description Text Default";
		}
		return this.fullDescriptionRevisionText;
	}
	
	public void setFullDescriptionText(String fullDescriptionText) {
		this.fullDescriptionRevisionText = fullDescriptionText;
	}
	
	public String getShortDescriptionText() {
		if(this.shortDescriptionRevisionText == null) {
			return "Short Description Text Default";
		}
		return this.shortDescriptionRevisionText;
	}
	
	public void setShortDescriptionText(String shortDescriptionText) {
		this.shortDescriptionRevisionText = shortDescriptionText;
	}
	
	@Override
	public String toString() {
		return "Size: " + this.workTOList +
				" RevisionId: " + this.revisionId;
	}
}
