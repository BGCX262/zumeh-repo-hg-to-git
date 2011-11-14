package com.es.zumeh.client.model.to;

import java.io.Serializable;
import java.util.ArrayList;

public class WorkTO implements Serializable {
	
	private static final long serialVersionUID = 5271216718431571860L;
	private int workId = 0;
	private ArrayList<NodeTO> nodeTOList = new ArrayList<NodeTO>();
	
	public void addNodeTO(NodeTO newNodeTO) {
		this.nodeTOList.add(newNodeTO);
	}
	
	public ArrayList<NodeTO> getTONodes() {
		return this.nodeTOList;
	}
	
	public NodeTO getNodeTOByNodeId(int nodeId) {
		for(NodeTO tmpNodeTO : nodeTOList) {
			if(tmpNodeTO.getNodeId() == nodeId) {
				return tmpNodeTO;
			}
		}
		return null;
	}
	
	public void setWorkId(int workId) {
		this.workId = workId;
	}
	
	public int getWorkId() {
		return this.workId;
	}
	
	@Override
	public String toString() {
		return "Node size: "+nodeTOList.size() + 
				" Identification: " + this.workId;
	}
}
