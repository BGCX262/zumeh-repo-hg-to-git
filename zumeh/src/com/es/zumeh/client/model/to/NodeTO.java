package com.es.zumeh.client.model.to;

import java.io.Serializable;

public class NodeTO implements Serializable {
	
	private static final long serialVersionUID = 4982682828203530566L;
	
	private Long id; //XXX Pode ser que use depois
	private Long leftChildNodeId;
    private Long rightChildNodeId;
	private String shortNodeDescription;
	private String fullNodeText;
	private String nodeStatus;
	

	
	public String getShortNodeDescription() {
		return shortNodeDescription;
	}

	public void setShortNodeDescription(String shortDescription) {
		this.shortNodeDescription = shortDescription;
	}

	public String getFullNodeText() {
		return fullNodeText;
	}
	
	public void setFullNodeText(String fullText) {
		this.fullNodeText = fullText;
	}

	public String getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	public Long getRightChildNodeId() {
		return rightChildNodeId;
	}

	public void setRightChildNodeId(Long rightChildNodeId) {
		this.rightChildNodeId = rightChildNodeId;
	}

	public Long getLeftChildNodeId() {
		return leftChildNodeId;
	}

	public void setLeftChildNodeId(Long leftChildNodeId) {
		this.leftChildNodeId = leftChildNodeId;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
