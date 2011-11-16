package com.es.zumeh.client.model.to;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class RevisionTO implements Serializable {
	
	private static final long serialVersionUID = 8424271182195040573L;
	private LinkedList<WorkTO> workTOList = new LinkedList<WorkTO>();
	private ArrayList<CommentTO> commentsTOList = new ArrayList<CommentTO>();
	private int revisionId = 0;
	private String fullDescriptionText;
	private String shortDescriptionText;
	
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
	
	public void setCommentsTO(ArrayList<CommentTO> commentsTOList) {
		this.commentsTOList = commentsTOList;
	}
	
	public ArrayList<CommentTO> getCommentsTO() {
		return this.commentsTOList;
	}
	
	public String getFullDescriptionText() {
		if(this.fullDescriptionText == null) {
			return "Full Description Text Default";
		}
		return this.fullDescriptionText;
	}
	
	public void setFullDescriptionText(String fullDescriptionText) {
		this.fullDescriptionText = fullDescriptionText;
	}
	
	public String getShortDescriptionText() {
		if(this.shortDescriptionText == null) {
			return "Short Description Text Default";
		}
		return this.shortDescriptionText;
	}
	
	public void setShortDescriptionText(String shortDescriptionText) {
		this.shortDescriptionText = shortDescriptionText;
	}
	
	@Override
	public String toString() {
		return "Size: " + this.workTOList +
				" RevisionId: " + this.revisionId;
	}
}
