package com.es.zumeh.server.persistence;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import com.es.zumeh.client.model.to.NodeTO;
import com.es.zumeh.server.model.DAOManager;
import com.es.zumeh.server.model.persistence.Work;

public class WorkDAOTest {
	
	DAOManager daoManager;
	Work work;
	
	@Before
	public void setUp() {
		daoManager = new DAOManager();
	}
	
	@Test
	public void test_add_node() {
		addWork();
		//Assert.assertEquals(true, result);
	}

	private void addWork() {
		createWork();
		
		daoManager.addWork(work);
	}

	private void createWork() {
		work = new Work();
		ArrayList<NodeTO> nodeTOList = new ArrayList<NodeTO>();
		NodeTO no = new NodeTO();
		no.setFullNodeText("Coisas aqui no full");
		no.setId(2L);
		no.setNodeStatus("Status");
		no.setShortNodeDescription("short");
		nodeTOList.add(no);
		work.setNodeTOList(nodeTOList );
	}

}
