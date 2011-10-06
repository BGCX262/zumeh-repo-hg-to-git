package com.es.zumeh.client.model.work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Node implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2723475653089939362L;
	private String description;
	private String text;
	private NodeStatus status;
	private int id;
	private List<Node> childNodes = new ArrayList<Node>();
	
	public Node(String description, int identifier) {
		setDescription(description);
		setIdentifier(identifier);
		status = NodeStatus.GREEN;
	}
	
	public Node(String description, String fullText, int identifier) {
		this(description,identifier);
		setFullText(fullText);
	}
	
	public void setDescription(String description) {		
		this.description = description;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setFullText(String text) {
		if(!isValid(description)) {
			throw new InvalidContentException("Text can not be empty or null");
		}
		
		this.text = text;
	}
	
	public String getFullText() {
		return this.text;
	}
	
	public void setNodeStatus(NodeStatus status) {
		this.status = status;
	}
	
	public NodeStatus getNodeStatus() {
		return this.status;
	}
	
	public void setIdentifier(int id) {
		this.id = id;
	}
	
	public int getIdentifier() {
		return this.id;
	}
	
	public void addChildNode(Node child) {
		this.childNodes.add(child);
	}
	
	public boolean removeChildNode(Node child) {
		return this.childNodes.remove(child);
	}
	
	public Node removeChildNode(int identification) {
		int pos = getNodePosition(identification);
		if(pos >= 0) {
			return this.childNodes.remove(pos);
		}
		
		return null;
	}
	
	public void removeAllChildNodes() {
		this.childNodes.clear();
	}
	
	public List<Node> getAllChildNodes() {
		return this.childNodes;
	}
	
	public boolean hasChild() {
		return !this.childNodes.isEmpty();
	}
	
	@Override
	public String toString() {
		Iterator<Node> itNode = childNodes.iterator();
		String out = "{";
		while(itNode.hasNext()) {
			Node node = itNode.next();
			out += node.getIdentifier() + ",";
		}
		if(out.length() > 1) {
			out = out.substring(0, out.length()-1);
		}
		return this.id +" => "+ out + "}";
	}
	
	private boolean isValid(String content) {
		if(content == null || content.trim().isEmpty()) {
			return false;
		}
		
		return true;
	}
	
	private int getNodePosition(int identification) {
		Iterator<Node> itNodes = this.childNodes.iterator();
		int pos = 0;
		
		while(itNodes.hasNext()) {
			Node node = itNodes.next();
			if(node.getIdentifier() == identification ) {
				return pos;
			}
			pos++;
		}
		
		pos = -1;
		return pos;
	}
	
	public Node find(int identifier) {
		if (this.id == identifier) {
			return this;
		} else {
			Iterator<Node> itChilds = childNodes.iterator();
			while(itChilds.hasNext()) {
				Node node = itChilds.next();
				Node tmpNode = node.find(identifier);
				if(tmpNode != null) {
					return tmpNode;
				}
			}
			return null;
		}
	}
	
	public Node findParent(int identifier) {
		if (isInChilds(identifier)) {
			return this;
		} else {
			Iterator<Node> itChilds = childNodes.iterator();
			while(itChilds.hasNext()) {
				Node node = itChilds.next();
				Node tmpNode = node.findParent(identifier);
				if(tmpNode != null) {
					return tmpNode;
				}
			}
			return null;
		}
	}
	private boolean isInChilds(int identifier) {
		Iterator<Node> itChilds = childNodes.iterator();
		while(itChilds.hasNext()) {
			Node node = itChilds.next();
			if(node.getIdentifier() == identifier) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Node node1 = new Node("description 1", 1);
		Node node2 = new Node("description 2", 2);
		Node node3 = new Node("description 3", 3);
		Node node4 = new Node("description 4", 4);
		
		System.out.println(node1);
		System.out.println(node1.hasChild());
		
		node1.addChildNode(node2);
		
		node2.addChildNode(node3);
		node2.addChildNode(node4);
		
		System.out.println(node1);
		System.out.println(node2);
		
		//node2.removeChildNode(node3);
		System.out.println(node2);
		System.out.println(node1.hasChild());
		System.out.println("Find: " + node1.findParent(2));
	}
}