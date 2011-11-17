package com.es.zumeh.server.persistence;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.es.zumeh.server.model.DAOManager;
import com.es.zumeh.server.model.persistence.Node;

public class NodeDAOTest {
	
	DAOManager daoManager;
	Node node;
	
	@Before
	public void setUp() {
		daoManager = new DAOManager();
	}
	
	@Test
	public void test_add_node() {
		addNode();
		//Assert.assertEquals(true, result);
	}

	private void addNode() {
		createNode();
		
		daoManager.addNode(node);
	}

	private void createNode() {
		node = new Node();
		node.setFullNodeText("t");
		node.setLeftChildNodeId(2L);
		node.setNodeStatus("s");
		node.setRightChildNodeId(3L);
		node.setShortNodeDescription("ss");
	}
	
	@Test
	public void test_verif() {
		createNode();
		boolean t = daoManager.verifyNode(node);
		Assert.assertEquals(true, t);
	}

}
