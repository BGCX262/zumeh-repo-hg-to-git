package com.es.zumeh.server.persistence;

import org.junit.Before;
import org.junit.Test;

import com.es.zumeh.server.model.DAOManager;
import com.es.zumeh.server.model.persistence.Comment;

public class CommentDAOTest {
	
	DAOManager daoManager;
	Comment comment;
	
	@Before
	public void setUp() {
		daoManager = new DAOManager();
	}
	
	@Test
	public void test_add_node() {
		addComment();
		//Assert.assertEquals(true, result);
	}

	private void addComment() {
		createComment();
		
		daoManager.addComment(comment);
	}

	private void createComment() {
		comment = new Comment();
		comment.setCommentText("Texto do comentario");
		comment.setOwer("Tiago Leite");
		comment.setRevisionId(2L);
	}

}
