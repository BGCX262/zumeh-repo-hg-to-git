package com.es.zumeh.client.model.work;

import java.io.Serializable;
import java.util.LinkedList;

public class Work implements Serializable {
	
	private static final long serialVersionUID = 2875050877977995483L;
	
	private LinkedList<Revision> revisions = new LinkedList<Revision>();
	private WorkStatus status;
	private String name;
	private String description;
	private String field;
	
	public WorkStatus getStatus() {
		return this.status;
	}
	
	public void setStatus(WorkStatus status) {
		this.status = status;
	}
	
	public String getWorkName() {
		return this.name;
	}
	
	public String getWorkDescription() {
		return this.description;
	}
	
	public String getWorkField() {
		return this.field;
	}
	
	public void setWorkName(String name) {
		this.name = name;
	}
	
	public void setWorkDescription(String description) {
		this.description = description;
	}
	
	public void setWorkField(String field) {
		this.field = field;
	}
	
	public void createNewRevision() {
		Revision newHead = getHeadRevision();
		this.revisions.addLast(newHead);
	}
	
	public Revision getRevision(int pos) {
		return this.revisions.get(pos);
	}
	
	public Revision getHeadRevision() {
		return this.revisions.getLast();
	}
	
	public Revision removeRevision(int pos) {
		return this.revisions.remove(pos);
	}
	
	public Revision removeHeadRevision() {
		return this.revisions.removeLast();
	}
	
	public int lenghtRevisions() {
		return this.revisions.size();
	}
	
	@Override
	public String toString() {
		return getWorkName();
	}
}