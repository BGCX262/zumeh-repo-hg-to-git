package com.es.zumeh.client.model.to;

import java.io.Serializable;

public class NodeTO implements Serializable {
	
	private static final long serialVersionUID = 4982682828203530566L;
	private int nodeId;
	private int leftChildNodeId;
	private int rightChildNodeId;
	private String shortDescription;
	private String fullText;
	private String nodeStatus;
	
	public NodeTO() {}
	
	public void setNodeId(int nodeId) {
		this.nodeId = nodeId;
	}
	
	public void setLeftChildNodeId(int leftChildNodeId) {
		this.leftChildNodeId = leftChildNodeId;
	}
	
	public void setRightChildNodeId(int rightChildNodeId) {
		this.rightChildNodeId = rightChildNodeId;
	}
	
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	
	public void setFullText(String fullText) {
		this.fullText = fullText;
	}
	
	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}
	
	public int getNodeId() {
		return this.nodeId;
	}
	
	public int getLeftChildNodeId() {
		return this.leftChildNodeId;
	}
	
	public int getRightChildNodeId() {
		return this.rightChildNodeId;
	}
	
	public String getShortDescription() {
		return this.shortDescription;
	}
	
	public String getFullText() {
		return this.fullText;
	}
	
	public String getNodeStatus() {
		return this.nodeStatus;
	}
	
	@Override
	public String toString() {
		return "Description: " + this.shortDescription +
				" NodeId: " + this.nodeId;
	}
}
