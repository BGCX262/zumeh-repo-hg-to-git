package com.es.zumeh.server.persistence;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import com.es.zumeh.client.model.to.WorkTO;
import com.es.zumeh.server.model.DAOManager;
import com.es.zumeh.server.model.persistence.Revision;

public class RevisionDAOTest {
	
	DAOManager daoManager;
	Revision revision;
	
	@Before
	public void setUp() {
		daoManager = new DAOManager();
	}
	
	@Test
	public void test_add_node() {
		addRevision();
	}
	
	@Test
	public void test_get_revision() {
		Revision[] allObjects = daoManager.getRevisionDAO().getAllObjects();
		for (Revision revision : allObjects) {
			System.out.println(revision.getFullDescriptionText());
			System.out.println("\n"+revision.getShortDescriptionText());
			System.out.println("\n"+revision.getRevisionId());
			for (WorkTO workTO : revision.getTOWorks()) {
				System.out.println("\n WORKER TO ID = "+workTO.getWorkId());
			}
		}
	}

	private void addRevision() {
		createRevision();
		daoManager.addRevision(revision);
	}

	private void createRevision() {
		revision = new Revision();
		revision.setFullDescriptionText("Descri��o da revision");
		revision.setShortDescriptionText("Short description");
		LinkedList<WorkTO> workTOList = new LinkedList<WorkTO>();
		WorkTO testeWork = new WorkTO();
		testeWork.setWorkId(1L);
		workTOList.add(testeWork);
		revision.setTOWorks(workTOList);
		
	}
	
	

}
