package com.es.zumeh.client.model.work;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Revision implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 39455801379819490L;
	
	private Node root;
	private int nodeIdentifier;
	
	public Revision() {
		nodeIdentifier = 0;
	}
	
	public void addNode(String description, String fullText, int parentIdentifier) {
		if(root == null || parentIdentifier < 0) {
			root = new Node(description, this.nodeIdentifier);
		} else {
			Node node = root.find(parentIdentifier);
			if(node == null) {
				throw new RuntimeException("Node not found.");
			} else {
				node.addChildNode(new Node(description, fullText, this.nodeIdentifier));
			}
		}
		
		this.nodeIdentifier++;
	}
	
	public Node removeNode(int identifier) {
		Node node = root.findParent(identifier);
		if(node == null) {
			throw new RuntimeException("Node not found.");
		} else {
			return node.removeChildNode(identifier);
		}
	}
	
	// Is this really necessary?
	public Node getNode(int identifier) {
		return root.find(identifier);
	}
	
	// Is this really necessary?
	public Node getRootNode() {
		return this.root;
	}
	
	public void editNodeDescription(int identifier, String newDescription) {
		Node node = root.find(identifier);
		
		if(node == null) {
			throw new RuntimeException("Node not found.");
		} else {
			node.setDescription(newDescription);
		}
	}
	
	public void editNodeText(int identifier, String newText) {
		Node node = root.find(identifier);
		
		if(node == null) {
			throw new RuntimeException("Node not found.");
		} else {
			node.setFullText(newText);
		}
	}
	
	public int getNextNodeIdentifier() {
		return nodeIdentifier;
	}
	
	public int getLenghtNodes() {
		int len = 0;
		for (int i = 0; i < nodeIdentifier; i++) {
			if(root.find(i) != null){
				len++;
			}
		}
		return len;
	}
	
	public List<Integer> getNodeIdentifiers() {
		List<Integer> ids = new ArrayList<Integer>();
		
		for (int i = 0; i < nodeIdentifier; i++) {
			if(root.find(i) != null)
				ids.add(i);
		}
		return ids;
	}
	
	@Override
	public String toString() {
		String out = new String();
		
		for (int i : getNodeIdentifiers()) {
			out += root.find(i) + "\n";
		}
		return out;
	}
	
	public static void main(String[] args) {
		Revision r = new Revision();
		r.addNode("description1", "fullText1", -1);
		r.addNode("description2", "fullText2", 0);
		r.addNode("description3", "fullText3", 1);
		r.addNode("description4", "fullText4", 1);
		System.out.println(r.getNode(2).getNodeStatus());
		r.getNode(2).setNodeStatus(NodeStatus.RED);
		System.out.println(r.getNode(2).getNodeStatus());
		System.out.println(r);
		r.removeNode(1);
		System.out.println(r);
	}
}