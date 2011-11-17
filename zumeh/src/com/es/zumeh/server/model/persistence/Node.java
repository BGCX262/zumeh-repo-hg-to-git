package com.es.zumeh.server.model.persistence;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity  
@Table(name = "NODES")  
@SequenceGenerator(name = "node_seq", sequenceName = "node_seq")  
public class Node implements Serializable {

	private static final long serialVersionUID = 4481764544654378806L;
	
	private Long id;
	private Long leftChildNodeId;
    private Long rightChildNodeId;
	private String shortNodeDescription;
	private String fullNodeText;
	private String nodeStatus;


	@Id  
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "node_seq")  
    @Column(name = "node_id")  
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(nullable=false, length = 200)
	public String getShortNodeDescription() {
		return shortNodeDescription;
	}

	public void setShortNodeDescription(String shortDescription) {
		this.shortNodeDescription = shortDescription;
	}

	@Lob
	public String getFullNodeText() {
		return fullNodeText;
	}
	
	public void setFullNodeText(String fullText) {
		this.fullNodeText = fullText;
	}

	@Column(nullable=false, length = 100)
	public String getNodeStatus() {
		return nodeStatus;
	}

	public void setNodeStatus(String nodeStatus) {
		this.nodeStatus = nodeStatus;
	}

	@Column(nullable=true)
	public Long getRightChildNodeId() {
		return rightChildNodeId;
	}

	public void setRightChildNodeId(Long rightChildNodeId) {
		this.rightChildNodeId = rightChildNodeId;
	}

	@Column(nullable=true)
	public Long getLeftChildNodeId() {
		return leftChildNodeId;
	}

	public void setLeftChildNodeId(Long leftChildNodeId) {
		this.leftChildNodeId = leftChildNodeId;
	}

}
