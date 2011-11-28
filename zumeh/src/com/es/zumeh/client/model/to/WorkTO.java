package com.es.zumeh.client.model.to;

import java.io.Serializable;
import java.util.LinkedList;

public class WorkTO implements Serializable {
	
	private static final long serialVersionUID = 5271216718431571860L;
	private Long workId = 0L;
	private LinkedList<NodeTO> nodeTOList = new LinkedList<NodeTO>();
	
	public void addNodeTO(NodeTO newNodeTO) {
		this.nodeTOList.add(newNodeTO);
	}
	
	public LinkedList<NodeTO> getNodes() {
		return this.nodeTOList;
	}
	
	public NodeTO getNodeTOByNodeId(Long i) {
		for(NodeTO tmpNodeTO : nodeTOList) {
			if(tmpNodeTO.getId().equals(i)) {
				return tmpNodeTO;
			}
		}
		return null;
	}
	
	public void setWorkId(Long workId) {
		this.workId = workId;
	}
	
	public Long getWorkId() {
		return this.workId;
	}
	
	@Override
	public String toString() {
		return "Node size: "+nodeTOList.size() + 
				" Identification: " + this.workId;
	}
}
